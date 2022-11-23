package com.challenge.rentalcar.application.port.primary;

import com.challenge.rentalcar.domain.dto.RentalRequestDTO;

public interface PricePrimaryPort {

  Double getRentalPrice(final String carModel, final RentalRequestDTO rentalRequest);

  Double getSurcharge(final String carModel, final String effectiveReturnDate);

}
