package com.challenge.rentalcar.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "client")
public class ClientEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "loyalty_points")
  private Integer loyaltyPoints;

  public ClientEntity() { }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public Integer getLoyaltyPoints() {
    return loyaltyPoints;
  }

  public void setLoyaltyPoints(final Integer loyaltyPoints) {
    this.loyaltyPoints = loyaltyPoints;
  }
}
