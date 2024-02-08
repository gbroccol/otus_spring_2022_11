-- liquibase formatted sql
-- changeset gbroccol:1 runOnChange:true splitStatements=true rollbackSplitStatements:false end-delimiter=";"

INSERT INTO genre (title)
VALUES ('роман'),
        ('сказка'),
        ('рассказ'),
        ('фантастика'),
        ('сатира');