# Read Me First

This is an example for a multi-module Maven project to develop multiple Spring Boot applications on a shared code base.

The approach shown in this repository is targeted towards bigger services with a reasonable amount of business logic.
For microservices providing only a simple, CRUD-like API this approach would be over-engineered.

## Component Versions

The following versions of software components are used in this project:

| Component   | Version |
|-------------|---------|
| Java        | 17      |
| Spring Boot | 2.7.x   |
| H2          | 2.x     |
| Liquibase   | 4.x     |

## Architecture

This project uses a hexagonal architecture (also known as Ports and Adapters).

This architectural pattern is reflected in the structure of the Maven project.
The application core and each adapter live in a separate module representing architectural dependencies as Maven
dependencies.
This approach prevent accidental breaking of the architectural concept.

These modules are then packaged into a Sprint Boot application which lives in a separate module as well.
The Spring Boot application itself has dependencies on the application core and the adapters.
Its sole purpose is to aggregate and configure the other modules and to provide an entry point for the application

### Modules

The ultimate goal is to create modules that are completely self-contained.
In combination with the hexagonal architectural pattern this allows you to remove a module with having

- compile errors due to missing dependencies (except when removing the application core module, of course)
- dead configuration properties

The only information that "leaks" out of a module is its profile name which is referenced in the `application.yml` file.

#### Application Modules

The Demo Application defines the following modules:

- [Spring Boot Application](./todo-app)
- [Application Core](./todo-core)
- [Database Adapter](./todo-db)
- [REST API Adapter](./todo-api)
- [Kafka Adapter](./todo-kafka)

### Naming Schemes

In order to make it easily distinguishable what kind of architectural element a class is the following naming scheme is
applied:

| Element                                        | Scheme                   |
|------------------------------------------------|--------------------------|
| Spring Configuration                           | `{Name}Configuration`    |
| Domain Model                                   | `{Name}`                 |
| Use Case (Incoming port)                       | `{Name}UseCase`          |
| Adapter <br> (Implementation of outgoing port) | `{Name}Adapter`          |
| Service                                        | `{Name}Service`          |
| REST API                                       | `{Name}Resource`         |
| DTO                                            | `{Name}Dto`              |
| Database Entity                                | `{Name}Entity`           |
| Database Repository                            | `{Name}Repository`       |
| JPA Repository                                 | `{Name}EntityRepository` |

### Configuration

The basic configuration of features is delegated to each sub-module.
The Database adapter contains the configuration of the database connection,
the REST API adapter contains the configuration of the REST controllers, etc.

Configuration data is provided through two ways:

- Spring profiles
- Spring configuration classes

#### Spring configuration classes

A configuration class is provided through Spring Boot's autoconfiguration feature.
Therefor the configuration class is referenced in the `META-INF/spring.factories` file which causes Spring Boot to
auto-load this class on startup.

It is recommended that each module uses its own package and that this package does not overlap with the package name of
other modules. This prevents unwanted side effects that can occur when Spring's `@ComponentScan` picks up components
from
another module due to same package names.

| Module      | Base package            |
|-------------|-------------------------|
| `todo-api`  | `com.example.todo.api`  |
| `todo-app`  | `com.example.todo.app`  |
| `todo-core` | `com.example.todo.core` |
| `todo-db`   | `com.example.todo.db`   |

#### Spring Profiles

Each module has a configuration for a Spring profile named after the module (profile `db` for `todo-db`, etc.).
These profiles are loaded through the `spring.profiles.include` property in the main `application.yml` file.

In addition to the Spring profiles defined by each module there are three more Spring profiles representing different
stages in a cloud environment:

- `dev`
- `qs`
- `prod`
- `local` (to be used for local development)

These stage profiles are typically activated through the `spring.profiles.active` property specified as environment
variable or command line argument for the Spring Boot application.

The stage profiles are orthogonal to the module profiles resulting in a configuration matrix:

| stage\module | core | api | db  |
|--------------|------|-----|-----|
| local        |      |     |     |
| dev          |      |     |     |
| qs           |      |     |     |
| prod         |      |     |     |

In order to provide stage specific configuration for a module you can make use of Spring's multi-document feature for
configuration files.

```yaml
todo-api:
  some-property: 'foo'

---
spring.config.activate.on-profile: dev
todo-api:
  some-property: 'foo on dev'
```

The three dashes (`---`) indicate the beginning of a new document. This document is only loaded if the condition defined
by the `spring.config.activate.on-profile` property is fulfilled.

#### Configuration Activation Chain

The above-mentioned approach results in the following configuration file loading chain:

- Stage profile activated through `spring.profiles.active`
- `application.yml` file is loaded
- Additional profiles specified in `spring.profile.include` and `spring.profiles.group` are activated
- Profile specific `application-{profile}.yml` files are loaded
- Sub-documents matched by `spring.config.activate.on-profile` are loaded

#### Custom Configuration Properties

When defining custom configuration properties they should be prefixed with the name of the module so that they are
clearly separated from configuration properties provided by Spring or other external libraries.

```yaml
spring:
  # Spring properties

logging:
  # Logging properties

todo-core:
  # Module properties
```

#### Accessing Configuration Properties

The preferred way to read property values defined in the configuration files is through Spring Boot's
[Type-safe Configuration Properties](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.external-config.typesafe-configuration-properties)
mechanism.
