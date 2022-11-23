package com.challenge.rentalcar.application.service;

import com.challenge.rentalcar.application.port.secondary.CarRepository;
import com.challenge.rentalcar.application.util.Utils;
import com.challenge.rentalcar.domain.entity.CarEntity;
import com.challenge.rentalcar.domain.exception.DateOutOfBoundException;
import com.challenge.rentalcar.domain.exception.NotFoundCarException;
import com.challenge.rentalcar.domain.exception.NotFoundModelException;
import com.challenge.rentalcar.domain.service.CarSurchargeService;
import com.challenge.rentalcar.domain.service.SurchargeCalculator;
import com.challenge.rentalcar.domain.vo.Car;
import com.challenge.rentalcar.domain.vo.CarModel;
import com.challenge.rentalcar.infrastructure.mapper.CarMapper;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetSurchargeUseCase implements CarSurchargeService {

  private final CarRepository carRepo;

  private final CarMapper carMapper;

  private final SurchargeCalculator surchargeCalculator;

  @Autowired
  public GetSurchargeUseCase(final CarRepository carRepository, final CarMapper carMapper,
      final SurchargeCalculator surchargeCalculator) {
    this.carRepo = carRepository;
    this.carMapper = carMapper;
    this.surchargeCalculator = surchargeCalculator;
  }

  @Override
  public Double getSurcharge(final String carModel, final String effectiveReturnDate) {
    final CarModel model = CarModel.getByModel(carModel.toUpperCase());
    if (model == null) {
      throw new NotFoundModelException();
    }

    final CarEntity carEntity = carRepo.findByModel(model.toString())
        .orElseThrow(NotFoundCarException::new);

    final Car car = carMapper.mapCarEntity(carEntity);

    return surchargeCalculator.getSurcharge(car.getType(),
        extraDays(car.getAvailability().getPlannedReturnDate(), effectiveReturnDate));
  }

  private Integer extraDays(final LocalDate plannedReturnDate, final String returnDate) {
    final LocalDate effectiveReturnDate = Utils.mapStringToLocalDate(returnDate);
    if (plannedReturnDate.isAfter(effectiveReturnDate)) {
      throw new DateOutOfBoundException("No surcharge is due to be applied.");
    }

    return (int) ChronoUnit.DAYS.between(plannedReturnDate, effectiveReturnDate);
  }

}
