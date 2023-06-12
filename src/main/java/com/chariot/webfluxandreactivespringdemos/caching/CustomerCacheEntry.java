package com.chariot.webfluxandreactivespringdemos.caching;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Builder
@Getter
@RedisHash
public class CustomerCacheEntry {
  @Id
  private String customerId;
  private String name;
}
