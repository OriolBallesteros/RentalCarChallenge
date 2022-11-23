package com.challenge.rentalcar.domain.service;

public interface CarSurchargeService {

  Double getSurcharge(final String carModel, final String effectiveReturnDate);

}
