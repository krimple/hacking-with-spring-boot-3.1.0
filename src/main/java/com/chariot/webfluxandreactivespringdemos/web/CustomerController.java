package com.chariot.webfluxandreactivespringdemos.web;

import com.chariot.webfluxandreactivespringdemos.caching.CustomerAsyncService;
import com.chariot.webfluxandreactivespringdemos.caching.entities.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/customers")
public class CustomerController {

  public CustomerController(CustomerAsyncService customerService) {
    this.customerService = customerService;
  }

  private final CustomerAsyncService customerService;

  public Mono<Customer> getCustomerById(@PathVariable String id) {
    return customerService.getCustomer(UUID.fromString(id));
  }

  @ResponseStatus(HttpStatus.OK)
  public void addCustomer() {
    // customerService.writeCustomer()
  }
}
