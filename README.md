# Sample Spring Boot application

Using JDK 20+ and Maven 3.9.x, the Spring Boot starters.

## API Choices

* Spring Boot BOM
* [Spring boot maven plugin](https://docs.spring.io/spring-boot/docs/current/maven-plugin/reference/htmlsingle/)
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
* Build executable war to run with `java -jar` using `mvn package`
* Build docker image with `mvn spring-boot:build-image`

## Tasks (not complete)

* Do we need both the parent AND Spring BOM?
 
