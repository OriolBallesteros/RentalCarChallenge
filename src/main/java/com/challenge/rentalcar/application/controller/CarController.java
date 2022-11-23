package com.challenge.rentalcar.application.controller;

import com.challenge.rentalcar.application.port.primary.CarPrimaryPort;
import com.challenge.rentalcar.domain.dto.CarsDTO;
import com.challenge.rentalcar.domain.service.CarAvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car")
public class CarController implements CarPrimaryPort {

  private final CarAvailabilityService carService;

  @Autowired
  public CarController(final CarAvailabilityService carService) {
    this.carService = carService;
  }

  @Override
  @GetMapping(value = "/inventory")
  public CarsDTO getInventory() {
    return carService.getInventory();
  }

}
