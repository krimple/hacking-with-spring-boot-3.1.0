package com.chariot.webfluxandreactivespringdemos.caching;

import com.google.gson.Gson;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import redis.clients.jedis.Jedis;

import java.time.LocalDateTime;

@Testcontainers
public class RedisJedisSimpleDataIT implements WithAssertions {

  record MyMessage(String message, String date) { }

  public GenericContainer<?> redis = null;
  static Gson gson;

  @BeforeAll
  public static void setUp() {
    gson = new Gson();
  }

  @Test
  public void testRedisJedisCalls() {
    try (var redis = new GenericContainer<>(DockerImageName.parse("redis:7.0.11-alpine"))) {
      redis
          .withReuse(false)
          .withAccessToHost(true)
          .withExposedPorts(6379)
          .start();
      var demoMessage = new MyMessage("This is a message", LocalDateTime.now().toString());

      var jedis = new Jedis(redis.getHost(), redis.getMappedPort(6379));
      var data = gson.toJson(demoMessage);

      jedis.set("data", data);
      String response = jedis.get("data");
      var fetchedData = gson.fromJson(response, MyMessage.class);
      assertThat(fetchedData).isEqualTo(demoMessage);
      jedis.close();
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }
}
