package com.challenge.rentalcar.application.service;

import com.challenge.rentalcar.application.port.secondary.CarRepository;
import com.challenge.rentalcar.domain.dto.CarsDTO;
import com.challenge.rentalcar.domain.entity.CarEntity;
import com.challenge.rentalcar.domain.service.CarAvailabilityService;
import com.challenge.rentalcar.domain.vo.Car;
import com.challenge.rentalcar.infrastructure.mapper.CarMapper;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetCarInventoryUseCase implements CarAvailabilityService {

  private final CarRepository carRepo;

  private final CarMapper carMapper;

  @Autowired
  public GetCarInventoryUseCase(final CarRepository carRepo, final CarMapper carMapper) {
    this.carRepo = carRepo;
    this.carMapper = carMapper;
  }

  @Override
  public CarsDTO getInventory() {
    final List<CarEntity> bar = StreamSupport.stream(carRepo.findAll().spliterator(), false)
        .collect(Collectors.toList());

    final List<Car> carList = bar.stream()
        .map(carMapper::mapCarEntity)
        .collect(Collectors.toList());

    final CarsDTO dto = new CarsDTO();
    dto.setCarList(carList);
    return dto;
  }
}
