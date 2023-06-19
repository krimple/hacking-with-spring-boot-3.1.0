package com.chariot.webfluxandreactivespringdemos.shellui;

import com.chariot.webfluxandreactivespringdemos.caching.CustomerAsyncService;
import com.chariot.webfluxandreactivespringdemos.caching.CustomerCacheEntry;
import com.chariot.webfluxandreactivespringdemos.caching.CustomerCacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.time.Duration;
import java.util.UUID;

@ShellComponent
public class RedisCommands {

    private CustomerAsyncService service;

    private CustomerCacheRepository repository;

    @Autowired
    public void setService(CustomerAsyncService service) {
        this.service = service;
    }

    @Autowired
    public void setRepository(CustomerCacheRepository repository) {
        this.repository = repository;
    }

    /**
     * Uses the Spring RedisTemplate to create a new CustomerCacheEntry object in the
     * Redis Cache.
     *
     * @param arg The name of the new customer
     * @return the new customer's UUID
     */
    @ShellMethod(key = "add-redis-customer")
    public String addValueToStream(
            @ShellOption() String arg
    ) {
        UUID newKey = UUID.randomUUID();
        Long numRows = this.service.writeCustomer(new CustomerCacheEntry(newKey, arg)).block(Duration.ofSeconds(10));
        if (numRows != null) {
            return String.format("customer added - UUID: %s, name: %s, list now has %d entries\r",
                newKey.toString(), arg, numRows);
        } else {
            return "Customer write not completed in 10 seconds.";
        }
    }

    /**
     * Uses the Spring Data ReactiveRedisTemplate to fetch the persisted CustomerCacheEntry
     * by UUID.
     * @param uuid the primary key of the existing customer
     * @return The string result of the fetch of the customer as a toString
     */
    @ShellMethod(key = "get-redis-customer")
    public String getRedisCustomer(
        @ShellOption() String uuid ) {
        var result = this.service.getCustomer(UUID.fromString(uuid)).block(Duration.ofSeconds(10));
        if (result != null) {
            return String.format("query UUID: %s, result: %s\r", uuid, result.toString());
        } else {
            return "No results.";
        }
    }

    /**
     * Shows how to create a customer using the Spring Data Redis crud repository
     *   n.b. the entity is stored by its `@RedisConfig` annotation, which in the
     *   case of CustomerCacheEntry is `Customer`
     * @param name the name of the new customer
     * @return the created customer
     */
    @ShellMethod(key = "add-customer-crud")
    public String addCustomerSpringDataCrud(@ShellOption String name ) {
        var insertedCustomer = repository.save(new CustomerCacheEntry(name));
        return insertedCustomer.toString();

    }

    /**
     * Shows how to use the Spring Data CRUD repository to fetch a customer by UUID
     * n.b. the entity is stored as `Customer`
     * @param uuid the customer id
     * @return the fetched customer
     */
    @ShellMethod(key = "get-customer-crud")
    public String getCustomerSpringDataCrud(@ShellOption String uuid ) {
        var fetchedCustomer = repository.findById(UUID.fromString(uuid));
        return fetchedCustomer.toString();
    }
}
