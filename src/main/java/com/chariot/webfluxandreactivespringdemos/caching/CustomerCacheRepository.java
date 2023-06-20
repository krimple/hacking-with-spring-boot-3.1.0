package com.chariot.webfluxandreactivespringdemos.caching;

import com.chariot.webfluxandreactivespringdemos.caching.entities.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CustomerCacheRepository extends CrudRepository<Customer, UUID> {
}
