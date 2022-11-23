package com.challenge.rentalcar.domain.dto;

public class RentalRequestDTO {

  private String startDate;

  private String plannedReturnDate;

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public String getPlannedReturnDate() {
    return plannedReturnDate;
  }

  public void setPlannedReturnDate(String plannedReturnDate) {
    this.plannedReturnDate = plannedReturnDate;
  }
}
