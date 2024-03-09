--liquibase formatted sql
--changeset gbroccol:2024-02-07-001-genre

CREATE TABLE genre (
    genre_id        SERIAL,
    title           VARCHAR(100)    NOT NULL,
    CONSTRAINT PK_Genre PRIMARY KEY (genre_id)
);
