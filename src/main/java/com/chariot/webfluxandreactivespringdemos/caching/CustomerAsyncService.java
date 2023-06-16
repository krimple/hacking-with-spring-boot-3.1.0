package com.chariot.webfluxandreactivespringdemos.caching;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.ReactiveRedisCallback;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class CustomerAsyncService {

  private ReactiveRedisTemplate redisTemplate;

  @Autowired
  public CustomerAsyncService(@Qualifier("reactiveRedisTemplate") ReactiveRedisTemplate redisTemplate) {
    this.redisTemplate = redisTemplate;
  }


  public Mono writeCustomer(CustomerCacheEntry customer) throws Exception {
    return redisTemplate.opsForList().rightPush(customer.getId(), customer);
  }

  /**
   * @param id
   * @return
   */
  public Mono<CustomerCacheEntry> getCustomer(UUID id) {
    return Mono.from(redisTemplate.opsForList().range("CustomerCacheEntry", 0, -1));
  }
}
