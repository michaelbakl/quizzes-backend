create table if not exists users
(
    userId text not null,
    email text  not null PRIMARY KEY,
    password text not null,
    enabled  boolean
);
