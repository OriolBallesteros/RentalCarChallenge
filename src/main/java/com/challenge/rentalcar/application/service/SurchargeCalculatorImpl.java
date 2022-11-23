package com.challenge.rentalcar.application.service;

import static com.challenge.rentalcar.domain.vo.PricingConstants.PREMIUM_CHARGE;
import static com.challenge.rentalcar.domain.vo.PricingConstants.SMALL_CHARGE;
import static com.challenge.rentalcar.domain.vo.PricingConstants.SUV_CHARGE;

import com.challenge.rentalcar.domain.service.SurchargeCalculator;
import com.challenge.rentalcar.domain.vo.CarType;
import org.springframework.stereotype.Component;

@Component
public class SurchargeCalculatorImpl implements SurchargeCalculator {

  @Override
  public Double getSurcharge(final CarType type, final Integer surpassedDays) {
    switch (type) {
      case PREMIUM:
        return getPremiumSurcharge(surpassedDays);
      case SUV:
        return getSUVSurcharge(surpassedDays);
      case SMALL:
        return getSmallSurcharge(surpassedDays);
      default:
        return null;
    }
  }

  private Double getPremiumSurcharge(final Integer surpassedDays) {
    return (PREMIUM_CHARGE * surpassedDays);
  }

  private Double getSUVSurcharge(final Integer surpassedDays) {
    return (SUV_CHARGE * surpassedDays);
  }

  private Double getSmallSurcharge(final Integer surpassedDays) {
    return (SMALL_CHARGE * surpassedDays);
  }
}
