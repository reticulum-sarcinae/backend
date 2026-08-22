CREATE TABLE IF NOT EXISTS hello_world
(
    id      uuid primary key default uuid(),
    message text
);

INSERT INTO hello_world(message)
VALUES ('Hello World!');