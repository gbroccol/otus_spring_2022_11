INSERT INTO author (first_name, last_name)
VALUES ('Михаил', 'Булгаков'),
        ('Михаил', 'Шолохов'),
        ('Фёдор', 'Достоевский'),
        ('Лев', 'Толстой'),
        ('Александр', 'Пушкин');

INSERT INTO genre (title)
VALUES ('роман'),
        ('сказка'),
        ('рассказ'),
        ('фантастика'),
        ('сатира');

INSERT INTO book (title, author_id, genre_id)
VALUES ('Мастер и Маргарита', 1, 4),
        ('Судьба человека', 2, 3),
        ('Идиот', 3, 1);
