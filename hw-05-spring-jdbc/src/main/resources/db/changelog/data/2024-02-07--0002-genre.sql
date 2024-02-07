--liquibase formatted sql
--changeset gbroccol:2024-02-07-002-genre

INSERT INTO genre (title)
VALUES ('роман'),
        ('сказка'),
        ('рассказ'),
        ('фантастика'),
        ('сатира');