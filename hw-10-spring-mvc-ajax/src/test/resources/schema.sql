CREATE TABLE author (
    author_id       SERIAL,
    first_name      VARCHAR(50)     NOT NULL,
    last_name       VARCHAR(50)     NOT NULL,
    CONSTRAINT PK_Author PRIMARY KEY (author_id)
);

CREATE TABLE genre (
    genre_id        SERIAL,
    title           VARCHAR(100)    NOT NULL,
    CONSTRAINT PK_Genre PRIMARY KEY (genre_id)
);

CREATE TABLE book (
    book_id         SERIAL,
    title           VARCHAR(100)    NOT NULL,
    author_id       INT             REFERENCES author(author_id),
    genre_id        INT             REFERENCES genre(genre_id),
    CONSTRAINT PK_Book PRIMARY KEY (book_id)
);

CREATE TABLE review (
    review_id       SERIAL,
    message         VARCHAR(300)    NOT NULL,
    book_id         INT             REFERENCES book(book_id),
    CONSTRAINT PK_Review PRIMARY KEY (review_id)
);

