package com.challenge.rentalcar.application.service;

import com.challenge.rentalcar.application.port.secondary.CarRepository;
import com.challenge.rentalcar.application.util.Utils;
import com.challenge.rentalcar.domain.entity.CarEntity;
import com.challenge.rentalcar.domain.exception.DateOutOfBoundException;
import com.challenge.rentalcar.domain.exception.NotFoundCarException;
import com.challenge.rentalcar.domain.exception.NotFoundModelException;
import com.challenge.rentalcar.domain.service.CarPricingService;
import com.challenge.rentalcar.domain.service.PriceCalculator;
import com.challenge.rentalcar.domain.vo.CarModel;
import com.challenge.rentalcar.domain.vo.CarType;
import com.challenge.rentalcar.infrastructure.mapper.CarMapper;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetRentalPriceUseCase implements CarPricingService {

  private final CarRepository carRepo;

  private final CarMapper carMapper;

  private final PriceCalculator priceCalculator;

  @Autowired
  public GetRentalPriceUseCase(final CarRepository carRepository, final CarMapper carMapper,
      final PriceCalculator priceCalculator) {
    this.carRepo = carRepository;
    this.carMapper = carMapper;
    this.priceCalculator = priceCalculator;
  }

  @Override
  public Double getRentalPrice(final String carModel, final String startDate, final String plannedReturnDate) {
    final CarModel model = CarModel.getByModel(carModel.toUpperCase());
    if (model == null) {
      throw new NotFoundModelException();
    }

    final CarEntity carEntity = carRepo.findByModel(model.toString())
        .orElseThrow(NotFoundCarException::new);

    final CarType type = carMapper.mapCarEntity(carEntity).getType();

    return priceCalculator.getPrice(type, numberRentalDays(startDate, plannedReturnDate).intValue());
  }

  private Long numberRentalDays(final String startDate, final String plannedReturnDate) {
    final LocalDate initDate = Utils.mapStringToLocalDate(startDate);
    final LocalDate lastDate = Utils.mapStringToLocalDate(plannedReturnDate);

    if (initDate.isAfter(lastDate)) {
      throw new DateOutOfBoundException();
    }

    return ChronoUnit.DAYS.between(initDate, lastDate);
  }

}
