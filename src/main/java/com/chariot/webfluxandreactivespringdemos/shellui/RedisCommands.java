package com.chariot.webfluxandreactivespringdemos.shellui;

import com.chariot.webfluxandreactivespringdemos.caching.CustomerAsyncService;
import com.chariot.webfluxandreactivespringdemos.caching.CustomerCacheEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class RedisCommands {

    private CustomerAsyncService service;

    @Autowired
    public void setService(CustomerAsyncService service) {
        this.service = service;
    }

    @ShellMethod(key = "add-redis-customer")
    public String addValueToStream(
            @ShellOption(defaultValue = "hello") String arg
    ) throws Exception {
        var result = this.service.writeCustomer(new CustomerCacheEntry(arg));
        // TODO - add a value to a reactive stream via the service
        return String.format("customer added \s", result.toString());
    }

    @ShellMethod(key = "get-redis-customers")
    public String getRedisCustomers(
    ) throws Exception {
        return "nuts!";

    }

}
