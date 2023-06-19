package com.chariot.webfluxandreactivespringdemos.caching;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.UUID;

@RedisHash("Customer")
public class CustomerCacheEntry implements Serializable {
  @Id
  private UUID id;
  private String name;

  public CustomerCacheEntry(UUID id, String name) {
    this.id = id;
    this.name = name;
  }

  public CustomerCacheEntry(String name) {
    this.id = UUID.randomUUID();
    this.name = name;
  }

  public CustomerCacheEntry() {

  }

  public void setId(UUID id) {
    this.id = id;
  }

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "CustomerCacheEntry{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }
}
