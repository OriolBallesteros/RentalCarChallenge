package com.challenge.rentalcar.domain.dto;

import com.challenge.rentalcar.domain.vo.Car;
import java.util.List;

public class CarsDTO {

  private List<Car> carList;

  public CarsDTO() { }

  public CarsDTO(final List<Car> carList) {
    this.carList = carList;
  }

  public List<Car> getCarList() {
    return carList;
  }

  public void setCarList(final List<Car> carList) {
    this.carList = carList;
  }
}
