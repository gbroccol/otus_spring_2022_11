-- liquibase formatted sql
-- changeset gbroccol:1 runOnChange:true splitStatements=true rollbackSplitStatements:false end-delimiter=";"

INSERT INTO book (title, author_id, genre_id)
VALUES ('Мастер и Маргарита', 1, 4),
       ('Судьба человека', 2, 3),
       ('Идиот', 3, 1);