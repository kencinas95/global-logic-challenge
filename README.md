# global-logic-challenge
Global Logic technical challenge

### How was it built?
This application was built by migrating an old implementation of a JBoss Application to a modern Springboot Application.

The changes are:
- Use Springboot's JPA instead of plain Hibernate
- Use Springboot's event system instead of the JavaEE implementation
- Use Springboot's dependecy management instead of the JavaEE implementation (replace @Scope, @RequestScoped, etc for @Controller, @Service, etc)
- Remove all .xml configuration files and use instead the .yml application configuration files, @Bean configurations and the Springboot's autoconfigurer
- Build a .jar instead a .war (or .ear) package
- Use Mockito and Springboot Test for testing instead of the old way and Arquillian
- Use Thymeleaf in behalf of JSF for templates and SSR  

### How to run it?
> Note: this application was made using a mongodb instance, so a dockerization was needed
Using Docker:
```sh
cd kitchen-sink-migration
docker-compose -f docker-compose.yml up -d
```

### How to run the tests?
```sh
cd kitchen-sink-migration
mvn clean test
```

### What to see?
- (SwaggerUI) http://localhost:8080/swagger-ui.html
- (Index Page) http://localhost:8080/
