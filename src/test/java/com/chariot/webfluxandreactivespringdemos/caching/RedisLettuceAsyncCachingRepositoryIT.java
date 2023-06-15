package com.chariot.webfluxandreactivespringdemos.caching;

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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Import(CustomerAsyncService.class)
public class RedisLettuceAsyncCachingRepositoryIT implements WithAssertions {

  @Autowired
  private CustomerAsyncService customerService;

  @Test
  public void testLettuceConnectionToRedis() {
    try (var redis = new GenericContainer<>(DockerImageName.parse("redis:7.0.11-alpine"))) {
      redis
          .withReuse(false)
          .withAccessToHost(true)
          .withExposedPorts(6379)
          .start();
      CustomerCacheEntry cacheEntry = new CustomerCacheEntry(1, "Customer");


      long handle = customerService.writeCustomer(cacheEntry).then()
          .doOnSuccess(result -> {
            var c2 = customerService.getCustomer(1).then().doOnSuccess(
                c -> {
                  assertThat("Customer").isEqualTo(c.getName());
                }
            )
            assertThat("Customer").isEqualTo(c2.getName());
      });
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
