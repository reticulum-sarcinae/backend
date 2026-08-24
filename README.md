# backend

## Module structure

- `backend`: REST API, application configuration, API models, and security.
- `core`: domain logic and business rules.
- `domain:usecase`: interfaces of the core that the API layer can call.
- `domain:api`: shared domain models.
- `adapter`: connections to external services, including persistence.
- `domain:port`: interfaces (ports) implemented by adapters.

## Architecture decisions

- Spring Boot is used as the application framework for the REST API, configuration, security, and runtime wiring.
- MariaDB is the relational database used for persistence.
- Flyway manages database schema migrations so database changes are versioned with the application.

## Local setup

The project uses Java 25 and Gradle. Docker is needed for the local MariaDB instance and for tests that use Testcontainers.

- Start the local database: `docker compose up mariadb -d`
- Start the be and database: `docker compose up sarcina -d`
- Build everything and run all unit tests: `./gradlew build`
- Run the backend locally: `./gradlew :backend:bootRun`

## Database migrations

Flyway migrations live in `adapter/persistence/src/main/resources/db/migration`.

- Flyway runs automatically and validates migrations on startup.
- Use versioned migration files named like: `VYYYYMMDD.AA__script_description`, where:
  * `YYYYMMDD` is the date,
  * `AA` is the number of the migration script within the day
- All schema changes need to be done via Flyway migrations.
