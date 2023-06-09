package com.chariot.webfluxandreactivespringdemos.caching;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Import(CustomerService.class)
public class CustomerCachingRepositoryTests {

  @Container
  @ServiceConnection
  static GenericContainer<?> redis =
        new GenericContainer<>(
          DockerImageName
              .parse("redis:7.0.11-alpine"))
              .withExposedPorts(6379);

  @Autowired
  private CustomerService customerService;

  @Test
  void findCustomerById() {
    CustomerCacheEntry cacheEntry = new CustomerCacheEntry("1", "Customer");
    customerService.createCustomer(cacheEntry);
    CustomerCacheEntry customerFromCache = customerService.getCustomer(cacheEntry.getCustomerId());
    assertEquals("1", customerFromCache.getCustomerId());
    assertEquals("Customer", customerFromCache.getName());

    /*
    Product product = new Product("1", "Test Product", 10.0);
    productService.createProduct(product);
    Product productFromDb = productService.getProduct("1");
    assertEquals("1", productFromDb.getId());
    assertEquals("Test Product", productFromDb.getName());
    assertEquals(10.0, productFromDb.getPrice());
     */
  }
}
