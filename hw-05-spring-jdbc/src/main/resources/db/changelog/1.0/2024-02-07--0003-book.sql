--liquibase formatted sql
--changeset gbroccol:2024-02-07-001-book

CREATE TABLE book (
    book_id         SERIAL,
    title           VARCHAR(100)    NOT NULL,
    author_id       INT             REFERENCES author(author_id),
    genre_id        INT             REFERENCES genre(genre_id),
    CONSTRAINT PK_Book PRIMARY KEY (book_id)
);
