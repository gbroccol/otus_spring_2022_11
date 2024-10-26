--liquibase formatted sql
--changeset gbroccol:2024-02-07-001-author

CREATE TABLE author (
    author_id       SERIAL,
    first_name      VARCHAR(50)     NOT NULL,
    last_name       VARCHAR(50)     NOT NULL,
    CONSTRAINT PK_Author PRIMARY KEY (author_id)
);
