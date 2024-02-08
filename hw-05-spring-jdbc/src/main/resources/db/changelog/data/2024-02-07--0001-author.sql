-- liquibase formatted sql
-- changeset gbroccol:1 runOnChange:true splitStatements=true rollbackSplitStatements:false end-delimiter=";"

INSERT INTO author (first_name, last_name)
VALUES ('Михаил', 'Булгаков'),
        ('Михаил', 'Шолохов'),
        ('Фёдор', 'Достоевский'),
        ('Лев', 'Толстой'),
        ('Александр', 'Пушкин');