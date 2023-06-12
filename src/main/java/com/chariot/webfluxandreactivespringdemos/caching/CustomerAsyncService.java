package com.chariot.webfluxandreactivespringdemos.caching;

import io.lettuce.core.api.reactive.RedisStringReactiveCommands;
import org.springframework.beans.factory.annotation.Qualifier;
import reactor.core.publisher.Mono;

public class CustomerAsyncService {

  private final RedisStringReactiveCommands<String, String> redisStringPrimaryCommands;
  private final RedisStringReactiveCommands<String, String> redisStringSecondaryCommands;

  public CustomerAsyncService(
      @Qualifier("redis-primary-commands") RedisStringReactiveCommands<String, String> redisStringPrimaryCommands,
      @Qualifier("redis-secondary-commands") RedisStringReactiveCommands<String, String> redisStringSecondaryCommands) {
    this.redisStringPrimaryCommands = redisStringPrimaryCommands;
    this.redisStringSecondaryCommands = redisStringSecondaryCommands;
  }

  public Mono<Void> writeCustomer(CustomerCacheEntry customer) {
    return this.redisStringPrimaryCommands.set(customer.getCustomerId(), customer.getName())
        .then();
  }

  public Mono<CustomerCacheEntry> getCustomer(String id) {
    return this.redisStringSecondaryCommands.get(id)
        .map(response -> CustomerCacheEntry.builder().customerId(id).name(response).build());
  }
}
