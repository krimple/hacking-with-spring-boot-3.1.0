package com.chariot.webfluxandreactivespringdemos.caching;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Builder
@Getter
@RedisHash("Customer")
public class CustomerCacheEntry implements Serializable {
  @Id
  private int id;
  private String name;
}
