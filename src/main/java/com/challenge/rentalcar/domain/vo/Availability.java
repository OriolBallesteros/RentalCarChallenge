package com.challenge.rentalcar.domain.vo;

import java.time.LocalDate;

public class Availability {

  private Boolean isAvailable;

  private LocalDate startRentalDate;

  private LocalDate plannedReturnDate;

  public Availability() { }

  public Availability(final Boolean isAvailable,
      final LocalDate startRentalDate,
      final LocalDate plannedReturnDate) {
    this.isAvailable = isAvailable;
    this.startRentalDate = startRentalDate;
    this.plannedReturnDate = plannedReturnDate;
  }

  public Boolean getAvailable() {
    return isAvailable;
  }

  public void setAvailable(final Boolean available) {
    isAvailable = available;
  }

  public LocalDate getStartRentalDate() {
    return startRentalDate;
  }

  public void setStartRentalDate(final LocalDate startRentalDate) {
    this.startRentalDate = startRentalDate;
  }

  public LocalDate getPlannedReturnDate() {
    return plannedReturnDate;
  }

  public void setPlannedReturnDate(final LocalDate plannedReturnDate) {
    this.plannedReturnDate = plannedReturnDate;
  }
}
