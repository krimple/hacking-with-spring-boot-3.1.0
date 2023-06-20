package com.chariot.webfluxandreactivespringdemos.caching.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.UUID;

@RedisHash("Customer")
public class Customer implements Serializable {
  @Id
  private UUID id;
  private String name;

  public Customer(UUID id, String name) {
    this.id = id;
    this.name = name;
  }

  public Customer(String name) {
    this.id = UUID.randomUUID();
    this.name = name;
  }

  public Customer() {

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
