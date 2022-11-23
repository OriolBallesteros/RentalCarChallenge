package com.challenge.rentalcar.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.challenge.rentalcar.application.port.secondary.CarRepository;
import com.challenge.rentalcar.domain.entity.CarEntity;
import com.challenge.rentalcar.domain.exception.DateOutOfBoundException;
import com.challenge.rentalcar.domain.exception.NotFoundCarException;
import com.challenge.rentalcar.domain.exception.NotFoundModelException;
import com.challenge.rentalcar.domain.service.PriceCalculator;
import com.challenge.rentalcar.domain.vo.Car;
import com.challenge.rentalcar.domain.vo.CarModel;
import com.challenge.rentalcar.domain.vo.CarType;
import com.challenge.rentalcar.infrastructure.mapper.CarMapper;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetRentalPriceUseCaseTest {

  @Mock
  private CarRepository carRepo;

  @Mock
  private CarMapper carMapper;

  @Mock
  private PriceCalculator priceCalculator;

  @InjectMocks
  private GetRentalPriceUseCase useCase;

  @Test
  @DisplayName("getRentalPrice method, when it is called, should make expected internal calls")
  void getRentalPrice_whenCalled_shouldMakeExpectedCalls() {
    final CarModel model = CarModel.BMW_7;
    final String startDate = "2022-12-10";
    final String plannedReturnDate = "2022-12-15";
    final CarEntity entity = getCarEntity("premium", model.getModel());
    final Car car = getCar(CarType.PREMIUM, model);
    final Double price = 600.0;
    mockCarRepoFindByModel(entity);
    mockCarMapperMapCarEntity(car);
    mockPriceCalculatorGetPrice(price);

    useCase.getRentalPrice(model.getModel(), startDate, plannedReturnDate);

    verify(carRepo, only()).findByModel(eq(model.name()));
    verify(carMapper, only()).mapCarEntity(eq(entity));
    verify(priceCalculator, only()).getPrice(eq(CarType.PREMIUM), eq(5));
  }

  @Test
  @DisplayName("getRentalPrice method, when model invalid, should return expected response")
  void getRentalPrice_whenModelNotFound_shouldReturnExpectedResponse() {
    final String model = "nonValid";
    final String startDate = "2022-12-10";
    final String plannedReturnDate = "2022-12-15";

    assertThrows(NotFoundModelException.class, () ->
        useCase.getRentalPrice(model, startDate, plannedReturnDate));
  }

  @Test
  @DisplayName("getRentalPrice method, when car not found, should return expected response")
  void getRentalPrice_whenCarNotFound_shouldReturnExpectedResponse() {
    final CarModel model = CarModel.NISSAN_JUKE;
    final String startDate = "2022-12-10";
    final String plannedReturnDate = "2022-12-15";
    mockCarRepoFindByModelException();

    assertThrows(NotFoundCarException.class, () ->
        useCase.getRentalPrice(model.getModel(), startDate, plannedReturnDate));
  }

  @Test
  @DisplayName("getRentalPrice method, when start date is after return date, should return expected response")
  void getRentalPrice_whenDatesNotLogic_shouldReturnExpectedResponse() {
    final CarModel model = CarModel.BMW_7;
    final String startDate = "2022-12-31";
    final String plannedReturnDate = "2022-12-01";
    final CarEntity entity = getCarEntity("premium", model.getModel());
    final Car car = getCar(CarType.PREMIUM, model);
    mockCarRepoFindByModel(entity);
    mockCarMapperMapCarEntity(car);

    assertThrows(DateOutOfBoundException.class, () ->
        useCase.getRentalPrice(model.getModel(), startDate, plannedReturnDate));
  }

  @Test
  @DisplayName("getRentalPrice method, when everything OK, should return expected value")
  void getRentalPrice_whenOk_shouldReturnExpectedResult() {
    final CarModel model = CarModel.BMW_7;
    final String startDate = "2022-12-10";
    final String plannedReturnDate = "2022-12-12";
    final CarEntity entity = getCarEntity("premium", model.getModel());
    final Car car = getCar(CarType.PREMIUM, model);
    final Double price = 600.0;
    mockCarRepoFindByModel(entity);
    mockCarMapperMapCarEntity(car);
    mockPriceCalculatorGetPrice(price);

    final Double result = useCase.getRentalPrice(model.getModel(), startDate, plannedReturnDate);

    assertNotNull(result);
    assertEquals(price, result);
  }

  private void mockCarRepoFindByModel(final CarEntity result) {
    when(carRepo.findByModel(anyString())).thenReturn(Optional.of(result));
  }

  private void mockCarRepoFindByModelException() {
    when(carRepo.findByModel(anyString())).thenThrow(NotFoundCarException.class);
  }

  private void mockCarMapperMapCarEntity(final Car result) {
    when(carMapper.mapCarEntity(any())).thenReturn(result);
  }

  private void mockPriceCalculatorGetPrice(final Double result) {
    when(priceCalculator.getPrice(any(), anyInt())).thenReturn(result);
  }

  private CarEntity getCarEntity(final String type, final String model) {
    final CarEntity entity = new CarEntity();
    entity.setType(type);
    entity.setModel(model);
    return entity;
  }

  private Car getCar(final CarType type, final CarModel model) {
    final Car car = new Car();
    car.setType(type);
    car.setModel(model);
    return car;
  }
}