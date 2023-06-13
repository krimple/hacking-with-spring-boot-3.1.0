# Sample Spring Boot application

Using JDK 20+ and Maven 3.9.x

## API Choices

* Spring Boot BOM 3.1.0
* Testcontainers
* JUnit Jupiter
* Spring boot logging starter -> SLF4J (use log4j2 if so inclined for performance and adaptability, see [this article](https://github.com/NutterzUK/spring-boot-logging-examples/blob/main/log4j2-example-all-default/pom.xml#L25-L38).
* Spring redis starter
  * Lettuce
  * Jedis
* Spring WebFlux starter
 
## Build

Standard Maven build. use Maven 3.9.x.

* Run all unit tests with `mvn test`
* Run all integration / spring tests with `mvn verify`
* Build and run with `mvn spring-boot:run`
* Build docker iamge with `mvn spring-boot:build-image`
