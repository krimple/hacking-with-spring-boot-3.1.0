package com.chariot.webfluxandreactivespringdemos.caching;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;

import java.util.Collections;
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
  public ReactiveRedisTemplate<UUID, CustomerCacheEntry> reactiveJsonCustomerCacheEntryRedisTemplate() {
    var serializer = new Jackson2JsonRedisSerializer<>(CustomerCacheEntry.class);

    RedisSerializationContext.RedisSerializationContextBuilder<UUID, CustomerCacheEntry> builder
        = RedisSerializationContext.newSerializationContext(new StringRedisSerializer());

    var serializationContext = builder.value(serializer).build();
    return new ReactiveRedisTemplate<>(redisConnectionFactory(), serializationContext);
  }
}


