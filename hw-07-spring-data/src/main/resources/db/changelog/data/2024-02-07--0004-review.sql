-- liquibase formatted sql
-- changeset gbroccol:1 runOnChange:true splitStatements=true rollbackSplitStatements:false end-delimiter=";"

INSERT INTO review (message, book_id)
VALUES ('Классная книга', 1);