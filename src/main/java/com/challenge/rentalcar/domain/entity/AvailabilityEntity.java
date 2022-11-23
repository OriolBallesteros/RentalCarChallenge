package com.challenge.rentalcar.domain.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "availability")
public class AvailabilityEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "start_date")
  private LocalDate startRentalDate;

  @Column(name = "planned_return_date")
  private LocalDate plannedReturnDate;

  @OneToOne(mappedBy = "availability")
  private CarEntity car;

  public AvailabilityEntity() { }

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

  public CarEntity getCar() {
    return car;
  }

  public void setCar(final CarEntity car) {
    this.car = car;
  }
}
