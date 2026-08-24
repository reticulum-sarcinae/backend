# backend

## Module structure

- `backend`: REST API, application configuration, API models, and security.
- `core`: domain logic and business rules.
- `domain:usecase`: interfaces of the core that the API layer can call.
- `domain:api`: shared domain models.
- `adapter`: connections to external services, including persistence.
- `domain:port`: interfaces (ports) the adapters.
