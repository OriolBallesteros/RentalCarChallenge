package com.challenge.rentalcar.application.controller;

import com.challenge.rentalcar.application.port.primary.PricePrimaryPort;
import com.challenge.rentalcar.domain.dto.RentalRequestDTO;
import com.challenge.rentalcar.domain.service.CarPricingService;
import com.challenge.rentalcar.domain.service.CarSurchargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pricing")
public class PriceController implements PricePrimaryPort {

  private final CarPricingService carPricingService;

  private final CarSurchargeService carSurchargeService;

  @Autowired
  public PriceController(final CarPricingService carPricingService, final CarSurchargeService carSurchargeService) {
    this.carPricingService = carPricingService;
    this.carSurchargeService = carSurchargeService;
  }

  @Override
  @GetMapping(value = "/{carModel}")
  public Double getRentalPrice(@PathVariable final String carModel,
      @RequestBody final RentalRequestDTO rentalRequest) {
    return carPricingService.getRentalPrice(carModel, rentalRequest.getStartDate(),
        rentalRequest.getPlannedReturnDate());
  }

  @Override
  @GetMapping(value = "/surcharge/{carModel}")
  public Double getSurcharge(@PathVariable final String carModel, @RequestBody final String effectiveReturnDate) {
    return carSurchargeService.getSurcharge(carModel, effectiveReturnDate);
  }

}
