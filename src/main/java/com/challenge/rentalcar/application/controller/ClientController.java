package com.challenge.rentalcar.application.controller;

import com.challenge.rentalcar.application.port.primary.ClientPrimaryPort;
import com.challenge.rentalcar.domain.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientController implements ClientPrimaryPort {

  private final ClientService clientService;

  @Autowired
  public ClientController(final ClientService clientService) {
    this.clientService = clientService;
  }

  @Override
  @GetMapping(value = "/points")
  public Integer getClientTotalLoyaltyPoints(@RequestBody final String name) {
    return clientService.getClientLoyaltyPoints(name);
  }

}
