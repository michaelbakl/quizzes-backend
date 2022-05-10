create table if not exists userRoles
(
    email text,
    role     text,
    PRIMARY KEY (email, role),
    FOREIGN KEY (email) REFERENCES users (email)
);