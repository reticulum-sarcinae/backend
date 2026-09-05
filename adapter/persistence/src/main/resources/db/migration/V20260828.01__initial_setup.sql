CREATE TABLE IF NOT EXISTS event
(
    id         uuid primary key default uuid(),
    name       text,
    start_time timestamp,
    end_time   timestamp
);


CREATE TABLE IF NOT EXISTS event_participant
(
    id       uuid primary key default uuid(),
    event_id uuid references event (id),
    name     text
);

CREATE UNIQUE INDEX IF NOT EXISTS event_participant_event_id_name_uidx ON event_participant (event_id, name);
