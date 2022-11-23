package com.challenge.rentalcar.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "car")
public class CarEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "model")
  private String model;

  @Column(name = "type")
  private String type;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "availability_id", referencedColumnName = "id")
  private AvailabilityEntity availability;

  public CarEntity() { }

  public String getModel() {
    return model;
  }

  public void setModel(final String model) {
    this.model = model;
  }

  public String getType() {
    return type;
  }

  public void setType(final String type) {
    this.type = type;
  }

  public AvailabilityEntity getAvailability() {
    return availability;
  }

  public void setAvailability(final AvailabilityEntity availability) {
    this.availability = availability;
  }
}
