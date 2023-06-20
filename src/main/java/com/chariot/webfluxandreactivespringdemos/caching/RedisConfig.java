package com.chariot.webfluxandreactivespringdemos.caching;

import com.chariot.webfluxandreactivespringdemos.caching.entities.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.util.UUID;

/**
 * From https://github.com/spring-projects/spring-data-examples
 */
@Slf4j
@EnableCaching
@Configuration
@RequiredArgsConstructor
public class RedisConfig {

  @Bean
  public LettuceConnectionFactory redisConnectionFactory() {
    return new LettuceConnectionFactory();
  }

  /**
   * Source: https://medium.com/javarevisited/caching-with-spring-boot-3-lettuce-and-redis-sentinel-5f6fab7e58f8
   */
  @Bean("reactiveRedisTemplate")
  public ReactiveRedisTemplate<UUID, Customer> reactiveJsonCustomerCacheEntryRedisTemplate() {
    var serializer = new Jackson2JsonRedisSerializer<>(Customer.class);

    RedisSerializationContext.RedisSerializationContextBuilder<UUID, Customer> builder
        = RedisSerializationContext.newSerializationContext(
            new GenericToStringSerializer<>(UUID.class));

    var serializationContext = builder.value(serializer).build();
    return new ReactiveRedisTemplate<>(redisConnectionFactory(), serializationContext);
  }
}


