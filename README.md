# Hacking with Spring Boot

Using JDK 20+ and Maven 3.9.x, the Spring Boot starters to show off a few features for a series of articles.

This project will be added to over time as additional demos are created.

## Demonstrations

### Using Spring Shell

Running the `./run-with-spring-shell.sh` script will launch the Spring Boot application and then begin a Spring Shell session. Commands are defined in the `...shellui` package. The commands let the user interact with a Redis server.

### Using JShell with Spring Boot

Running the `./jshell.sh` script builds the project and then launches JShell, booting the Spring container and exposing it as `context` in the shell itself. Any Spring component can then be accessed via `getBean` on the `context` object and called.

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
 
