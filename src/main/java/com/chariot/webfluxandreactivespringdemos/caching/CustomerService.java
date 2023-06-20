package com.chariot.webfluxandreactivespringdemos.caching;

import com.chariot.webfluxandreactivespringdemos.caching.entities.Customer;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerService {
  private final CustomerCacheRepository customerRepository;

  public CustomerService(CustomerCacheRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public Customer getCustomer(UUID id) {
    return this.customerRepository.findById(id).orElse(null);
  }

  public void createCustomer(Customer customer) {
    this.customerRepository.save(customer);
  }
}
