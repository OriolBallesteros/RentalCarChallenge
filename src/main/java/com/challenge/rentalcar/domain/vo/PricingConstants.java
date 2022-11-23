package com.challenge.rentalcar.domain.vo;

public class PricingConstants {

  /** Pricing. */
  private static final Double SMALL_PRICE = CarType.SMALL.getPrice();
  private static final Double SUV_PRICE = CarType.SUV.getPrice();
  private static final Double PREMIUM_PRICE = CarType.PREMIUM.getPrice();

  /** Number of days when discounts start to apply. */
  public static final Integer SMALL_ONLY_PERIOD = 7;
  public static final Integer SUV_FIRST_PERIOD = 7;
  public static final Integer SUV_SECOND_PERIOD = 30;

  /** Percentage and final discounted price for planned pricing. */
  private static final Double SMALL_PERCENTAGE_DISCOUNT = 0.6;
  private static final Double SUV_FIRST_PERCENTAGE_DISCOUNT = 0.8;
  private static final Double SUV_SECOND_PERCENTAGE_DISCOUNT = 0.5;
  public static final Double SMALL_DISCOUNT = (SMALL_PERCENTAGE_DISCOUNT * SMALL_PRICE);
  public static final Double SUV_FIRST_DISCOUNT = (SUV_FIRST_PERCENTAGE_DISCOUNT * SUV_PRICE);
  public static final Double SUV_SECOND_DISCOUNT = (SUV_SECOND_PERCENTAGE_DISCOUNT * SUV_PRICE);

  /** Percentage and final charge price for surcharges */
  private static final Double SMALL_PERCENTAGE_CHARGE = 0.3;
  private static final Double SUV_PERCENTAGE_CHARGE = 0.6;
  private static final Double PREMIUM_PERCENTAGE_CHARGE = 0.2;
  public static final Double SMALL_CHARGE = ((SMALL_PERCENTAGE_CHARGE * SMALL_PRICE) + SMALL_PRICE);
  public static final Double SUV_CHARGE = ((SUV_PERCENTAGE_CHARGE * SMALL_PRICE) + SUV_PRICE); // SUV percentage on Small price, as checked in instructions
  public static final Double PREMIUM_CHARGE = ((PREMIUM_PERCENTAGE_CHARGE * PREMIUM_PRICE) + PREMIUM_PRICE);

}
