--liquibase formatted sql
--changeset gbroccol:2024-02-07-001-author

INSERT INTO author (first_name, last_name)
VALUES ('Михаил', 'Булгаков'),
        ('Михаил', 'Шолохов'),
        ('Фёдор', 'Достоевский'),
        ('Лев', 'Толстой'),
        ('Александр', 'Пушкин');