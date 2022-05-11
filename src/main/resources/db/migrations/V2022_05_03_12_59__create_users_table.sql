create table if not exists users
(
    userId text not null PRIMARY KEY,
    email text  not null,
    password text not null,
    enabled  boolean
);
