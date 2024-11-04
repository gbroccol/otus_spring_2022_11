--liquibase formatted sql
--changeset gbroccol:2024-11-04-005-auth-users

CREATE SCHEMA auth;

CREATE TABLE auth.users (
    user_id        SERIAL,
    username       VARCHAR(100)    NOT NULL,
    password       VARCHAR(100)    NOT NULL,
    email          VARCHAR(100)    NOT NULL,
    CONSTRAINT PK_Users PRIMARY KEY (user_id)
);
