package com.chariot.webfluxandreactivespringdemos.caching;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class CustomerAsyncService {

  private final ReactiveRedisTemplate<UUID, CustomerCacheEntry> redisTemplate;

  @Autowired
  public CustomerAsyncService(
      @Qualifier("reactiveRedisTemplate") ReactiveRedisTemplate<UUID, CustomerCacheEntry> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }


  public Mono<Long> writeCustomer(CustomerCacheEntry customer) {
    // fork it, man
    return redisTemplate.opsForList().rightPush(customer.getId(), customer);
  }

  public Mono<CustomerCacheEntry> getCustomer(UUID id) {
    return Mono.from(redisTemplate.opsForList().range(id, 0, -1));
  }
}
