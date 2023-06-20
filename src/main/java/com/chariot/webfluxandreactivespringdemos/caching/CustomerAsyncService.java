package com.chariot.webfluxandreactivespringdemos.caching;

import com.chariot.webfluxandreactivespringdemos.caching.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class CustomerAsyncService {

  private final ReactiveRedisTemplate<UUID, Customer> redisTemplate;

  @Autowired
  public CustomerAsyncService(
      @Qualifier("reactiveRedisTemplate") ReactiveRedisTemplate<UUID, Customer> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }


  public Mono<Long> writeCustomer(Customer customer) {
    // fork it, man
    return redisTemplate.opsForList().rightPush(customer.getId(), customer);
  }

  public Mono<Customer> getCustomer(UUID id) {
    return Mono.from(redisTemplate.opsForList().range(id, 0, -1));
  }
}
