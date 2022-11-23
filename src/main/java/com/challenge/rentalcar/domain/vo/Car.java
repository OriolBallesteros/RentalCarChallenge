package com.challenge.rentalcar.domain.vo;

public class Car {

  private CarModel model;

  private CarType type;

  private Availability availability;

  public Car() { }

  public Car(final CarModel model,
      final CarType type,
      final Availability availability) {
    this.model = model;
    this.type = type;
    this.availability = availability;
  }

  public CarModel getModel() {
    return model;
  }

  public void setModel(final CarModel model) {
    this.model = model;
  }

  public CarType getType() {
    return type;
  }

  public void setType(final CarType type) {
    this.type = type;
  }

  public Availability getAvailability() {
    return availability;
  }

  public void setAvailability(final Availability availability) {
    this.availability = availability;
  }
}
