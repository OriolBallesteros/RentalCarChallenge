package com.challenge.rentalcar.infrastructure.mapper;

import com.challenge.rentalcar.domain.entity.AvailabilityEntity;
import com.challenge.rentalcar.domain.vo.Availability;
import java.time.LocalDate;
import org.springframework.stereotype.Component;

@Component
public class AvailabilityMapper {

  public Availability mapAvailabilityEntity(final AvailabilityEntity entity) {
    if (entity == null) {
      return null;
    }

    final Availability availability = new Availability();
    if (entity.getStartRentalDate() != null) {
      availability.setStartRentalDate(entity.getStartRentalDate());
    }
    if (entity.getPlannedReturnDate() != null) {
      availability.setPlannedReturnDate(entity.getPlannedReturnDate());
    }

    availability.setAvailable(getIsAvailable(availability));

    return availability;
  }

  private Boolean getIsAvailable(final Availability availability) {
    final LocalDate now = LocalDate.now();

    return (now.isAfter(availability.getStartRentalDate())
        & now.isBefore(availability.getPlannedReturnDate()));
  }
}
