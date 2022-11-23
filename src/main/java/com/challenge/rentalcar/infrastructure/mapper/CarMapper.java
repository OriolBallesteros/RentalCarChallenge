package com.challenge.rentalcar.infrastructure.mapper;

import com.challenge.rentalcar.domain.entity.CarEntity;
import com.challenge.rentalcar.domain.vo.Car;
import com.challenge.rentalcar.domain.vo.CarModel;
import com.challenge.rentalcar.domain.vo.CarType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {

  private AvailabilityMapper availabilityMapper;

  @Autowired
  public CarMapper(final AvailabilityMapper availabilityMapper) {
    this.availabilityMapper = availabilityMapper;
  }

  public Car mapCarEntity(final CarEntity entity) {
    if (entity == null) {
      return null;
    }

    final Car car = new Car();
    if (entity.getModel() != null) {
      car.setModel(CarModel.valueOf(entity.getModel()));
    }
    if (entity.getType() != null) {
      car.setType(CarType.valueOf(entity.getType().toUpperCase()));
    }
    if (entity.getAvailability() != null) {
      car.setAvailability(availabilityMapper.mapAvailabilityEntity(entity.getAvailability()));
    }

    return car;
  }

}
