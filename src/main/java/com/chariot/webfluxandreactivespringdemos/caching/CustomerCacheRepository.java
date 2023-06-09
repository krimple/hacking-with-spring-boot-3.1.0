package com.chariot.webfluxandreactivespringdemos.caching;

import org.springframework.data.repository.CrudRepository;

public interface CustomerCacheRepository extends CrudRepository<CustomerCacheEntry, String> {
}
