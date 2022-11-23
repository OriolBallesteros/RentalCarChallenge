package com.challenge.rentalcar.domain.vo;

public class Client {

  private String name;

  private Integer totalLoyaltyPoints;

  public Client() { }

  public Client(final String name, final Integer totalLoyaltyPoints) {
    this.name = name;
    this.totalLoyaltyPoints = totalLoyaltyPoints;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public Integer getTotalLoyaltyPoints() {
    return totalLoyaltyPoints;
  }

  public void setTotalLoyaltyPoints(final Integer totalLoyaltyPoints) {
    this.totalLoyaltyPoints = totalLoyaltyPoints;
  }
}
