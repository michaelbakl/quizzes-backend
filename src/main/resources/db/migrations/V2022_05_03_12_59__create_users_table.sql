create table if not exists users
(
    email text PRIMARY KEY,
    password text,
    enabled  boolean
);
