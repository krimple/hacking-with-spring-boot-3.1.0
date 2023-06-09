package com.chariot.webfluxandreactivespringdemos.caching;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash
public class CustomerCacheEntry {
  @Id
  private String customerId;
  private String name;

  public CustomerCacheEntry(String customerId, String name) {
    this.customerId = customerId;
    this.name = name;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
