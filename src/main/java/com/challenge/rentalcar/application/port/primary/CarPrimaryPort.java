package com.challenge.rentalcar.application.port.primary;

import com.challenge.rentalcar.domain.dto.CarsDTO;

public interface CarPrimaryPort {

  CarsDTO getInventory();

}
