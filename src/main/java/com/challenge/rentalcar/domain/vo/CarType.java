package com.challenge.rentalcar.domain.vo;

import java.util.Arrays;

public enum CarType {
  PREMIUM(300.0, 5),
  SUV(150.0, 3),
  SMALL(50.0, 1);

  private final Double price;

  private final Integer points;

  /** Enum that links type with its associated price & loyalty points. */
  CarType(final Double price, final Integer points) {
    this.price = price;
    this.points = points;
  }

  public Double getPrice() { return price; }

  public static CarType getByPrice(final Double price) {
    return Arrays.stream(CarType.values())
        .filter(type -> type.getPrice().equals(price))
        .findFirst()
        .orElse(null);
  }

  public Integer getPoints() { return points; }

  public static CarType getByPoints(final Integer givenPoints) {
    return Arrays.stream(CarType.values())
        .filter(type -> type.getPoints().equals(givenPoints))
        .findFirst()
        .orElse(null);
  }
}
