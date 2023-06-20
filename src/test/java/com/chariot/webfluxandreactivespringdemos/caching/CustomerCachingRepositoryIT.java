package com.chariot.webfluxandreactivespringdemos.caching;

import com.chariot.webfluxandreactivespringdemos.caching.entities.Customer;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Import(CustomerService.class)
public class CustomerCachingRepositoryIT implements WithAssertions {

  @Autowired
  private CustomerService customerService;

  @Test
  void findCustomerById() {
    try (var redis = new GenericContainer<>(DockerImageName.parse("redis:7.0.11-alpine"))) {
      redis
              .withReuse(false)
              .withAccessToHost(true)
              .withExposedPorts(6379)
              .start();
      UUID entryKey = UUID.randomUUID();
      Customer cacheEntry = new Customer(entryKey, "Customer");
      customerService.createCustomer(cacheEntry);
      Customer customerFromCache = customerService.getCustomer(cacheEntry.getId());
      assertThat(customerFromCache.getId()).isEqualTo(entryKey);
      assertThat(customerFromCache.getName()).isEqualTo("Customer");
    }
  }
}
