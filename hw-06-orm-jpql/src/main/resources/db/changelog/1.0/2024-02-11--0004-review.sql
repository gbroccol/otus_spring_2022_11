--liquibase formatted sql
--changeset gbroccol:2024-02-11-004-review

CREATE TABLE review (
    review_id       SERIAL,
    message         VARCHAR(300)    NOT NULL,
    book_id         INT             REFERENCES book(book_id),
    CONSTRAINT PK_Review PRIMARY KEY (review_id)
);
