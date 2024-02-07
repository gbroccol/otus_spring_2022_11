--liquibase formatted sql
--changeset gbroccol:2024-02-07-003-book

INSERT INTO book (title, author_id, genre_id)
VALUES ('Мастер и Маргарита', 1, 4),
       ('Судьба человека', 2, 3),
       ('Идиот', 3, 1);