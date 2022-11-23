package com.challenge.rentalcar.application.service;

import com.challenge.rentalcar.application.port.secondary.ClientRepository;
import com.challenge.rentalcar.domain.entity.ClientEntity;
import com.challenge.rentalcar.domain.exception.NotFoundClientException;
import com.challenge.rentalcar.domain.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetClientLoyaltyPointsUseCase implements ClientService {

  private final ClientRepository clientRepo;

  @Autowired
  public GetClientLoyaltyPointsUseCase(final ClientRepository clientRepo) {
    this.clientRepo = clientRepo;
  }

  @Override
  public Integer getClientLoyaltyPoints(final String name) {
    final ClientEntity client = clientRepo.findByName(name).orElseThrow(NotFoundClientException::new);

    return client.getLoyaltyPoints();
  }
}
