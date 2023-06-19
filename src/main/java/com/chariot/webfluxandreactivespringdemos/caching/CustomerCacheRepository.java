package com.chariot.webfluxandreactivespringdemos.caching;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CustomerCacheRepository extends CrudRepository<CustomerCacheEntry, UUID> {
}
