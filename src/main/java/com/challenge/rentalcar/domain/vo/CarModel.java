package com.challenge.rentalcar.domain.vo;

import java.util.Arrays;

public enum CarModel {
  BMW_7("BMW"),
  KIA_SORENTO("KIA"),
  NISSAN_JUKE("NISSAN"),
  SEAT_IBIZA("SEAT");

  private final String model;

  CarModel(final String model) {
    this.model = model;
  }

  public String getModel() { return model; }

  public static CarModel getByModel(final String givenModel) {
    return Arrays.stream(CarModel.values())
        .filter(model -> model.getModel().equals(givenModel))
        .findFirst()
        .orElse(null);
  }
}
