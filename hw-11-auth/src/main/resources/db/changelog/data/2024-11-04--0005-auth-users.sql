-- liquibase formatted sql
-- changeset gbroccol:1 runOnChange:true splitStatements=true rollbackSplitStatements:false end-delimiter=";"

INSERT INTO auth.users (username, password, email)
VALUES ('user', '$2a$12$n55ITTEjO0buB4MEi7pH7eFLNx1nP32/YT3MvS0xUECMIwjBc5SWG', 'user@mail.com'),
       ('admin', '$2a$12$delR9fxE02ZABNjAJDoumuz0l4UyYBlB07MLNBrBFCKFiRIRIxZm6', 'admin@mail.com');
