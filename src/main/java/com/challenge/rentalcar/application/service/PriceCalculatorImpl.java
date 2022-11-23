package com.challenge.rentalcar.application.service;

import static com.challenge.rentalcar.domain.vo.CarType.PREMIUM;
import static com.challenge.rentalcar.domain.vo.CarType.SMALL;
import static com.challenge.rentalcar.domain.vo.CarType.SUV;
import static com.challenge.rentalcar.domain.vo.PricingConstants.SMALL_DISCOUNT;
import static com.challenge.rentalcar.domain.vo.PricingConstants.SMALL_ONLY_PERIOD;
import static com.challenge.rentalcar.domain.vo.PricingConstants.SUV_FIRST_DISCOUNT;
import static com.challenge.rentalcar.domain.vo.PricingConstants.SUV_FIRST_PERIOD;
import static com.challenge.rentalcar.domain.vo.PricingConstants.SUV_SECOND_DISCOUNT;
import static com.challenge.rentalcar.domain.vo.PricingConstants.SUV_SECOND_PERIOD;

import com.challenge.rentalcar.domain.service.PriceCalculator;
import com.challenge.rentalcar.domain.vo.CarType;
import org.springframework.stereotype.Component;

@Component
public class PriceCalculatorImpl implements PriceCalculator {

  @Override
  public Double getPrice(final CarType type, final Integer totalDays) {
    switch (type) {
      case PREMIUM:
        return getPremiumPrice(totalDays);
      case SUV:
        return getSUVPrice(totalDays);
      case SMALL:
      default:
        return getSmallPrice(totalDays);
    }
  }

  private Double getPremiumPrice(final Integer totalDays) {
    return (PREMIUM.getPrice() * totalDays);
  }

  private Double getSUVPrice(final Integer totalDays) {
    if (totalDays <= SUV_FIRST_PERIOD) {
      return (SUV.getPrice() * totalDays);
    }

    Double totalPrice = null;
    if (totalDays < SUV_SECOND_PERIOD) {
      totalPrice = (SUV_FIRST_PERIOD * SUV.getPrice());
      totalPrice += ((totalDays - SUV_FIRST_PERIOD) * (SUV_FIRST_DISCOUNT));
    }

    if (totalDays > SUV_SECOND_PERIOD) {
      totalPrice = (SUV_FIRST_PERIOD * SUV.getPrice());
      totalPrice += ((totalDays - SUV_FIRST_PERIOD) * (SUV_FIRST_DISCOUNT));
      totalPrice += ((totalDays - SUV_FIRST_PERIOD - SUV_SECOND_PERIOD)
          * (SUV_SECOND_DISCOUNT));
    }

    return totalPrice;
  }

  private Double getSmallPrice(final Integer totalDays) {
    if (totalDays <= SMALL_ONLY_PERIOD) {
      return (SMALL.getPrice() * totalDays);
    }

    Double totalPrice = null;
    totalPrice = (SMALL_ONLY_PERIOD * SMALL.getPrice());
    totalPrice += ((totalDays - SMALL_ONLY_PERIOD) * (SMALL_DISCOUNT));

    return totalPrice;
  }
}
