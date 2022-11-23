package com.challenge.rentalcar.domain.service;

import com.challenge.rentalcar.domain.vo.CarType;

public interface PriceCalculator {

  Double getPrice(final CarType type, final Integer plannedDays);

}
