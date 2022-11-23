package com.challenge.rentalcar.application.port.secondary;

import com.challenge.rentalcar.domain.entity.ClientEntity;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<ClientEntity, Long> {

  Optional<ClientEntity> findByName(final String name);

}
