package com.challenge.rentalcar.domain.service;

import com.challenge.rentalcar.domain.vo.CarType;

public interface SurchargeCalculator {

  Double getSurcharge(final CarType type, final Integer surpassedDays);

}
