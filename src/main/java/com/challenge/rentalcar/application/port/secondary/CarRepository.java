package com.challenge.rentalcar.application.port.secondary;

import com.challenge.rentalcar.domain.entity.CarEntity;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<CarEntity, Long> {

  Optional<CarEntity> findByModel(final String carModel);

}
