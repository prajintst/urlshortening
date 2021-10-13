# URL shortening microservice


### Introduction

It's a simple url shortening microservice which take urls and shorten it back using springboot
and PGSQL.



### Running Application

First configure the environment with proper value the required values are in `env` file
(can use IntelliJ plugins to autoconfig the env by renaming it to .env)

we are just adding basic auth for now. The username password can be configured from env.
```aidl
USER=user
PASSWORD=password
```
`BASE_URL` is for configuring the base url of the shortened url similar to  `https://bit.ly`

`SWAGGER_BASE_URLS` is for keeping the swagger server url for swagger ui to call the API.

After running the application we can open [http://localhost:8082](http://localhost:8082)
to see the swagger doc for the rest API. (port may change based on environment value)

JobRunner Dashboard will start at [http://localhost:8000/dashboard](http://localhost:8000/dashboard)

`DELAY_IN_MIN` can be used to config the crone delay.

There is no database caching is added now. 

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.5/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.5.5/maven-plugin/reference/html/#build-image)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.5.5/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.5.5/reference/htmlsingle/#using-boot-devtools)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.5.5/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Security](https://docs.spring.io/spring-boot/docs/2.5.5/reference/htmlsingle/#boot-features-security)
* [Flyway Migration](https://docs.spring.io/spring-boot/docs/2.5.5/reference/htmlsingle/#howto-execute-flyway-database-migrations-on-startup)

### Guides

The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)

