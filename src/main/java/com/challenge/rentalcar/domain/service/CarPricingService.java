package com.challenge.rentalcar.domain.service;

public interface CarPricingService {

  Double getRentalPrice(final String carModel, final String startDate, final String plannedReturnDate);

}
