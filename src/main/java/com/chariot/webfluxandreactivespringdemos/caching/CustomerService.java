package com.chariot.webfluxandreactivespringdemos.caching;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerService {
  private final CustomerCacheRepository customerRepository;

  public CustomerService(CustomerCacheRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public CustomerCacheEntry getCustomer(UUID id) {
    return this.customerRepository.findById(id).orElse(null);
  }

  public void createCustomer(CustomerCacheEntry customer) {
    this.customerRepository.save(customer);
  }
}
