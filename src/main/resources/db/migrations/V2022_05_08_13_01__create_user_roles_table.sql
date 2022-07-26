create table if not exists userRoles
(
    userId text,
    role     text,
    PRIMARY KEY (userId, role),
    FOREIGN KEY (userId) REFERENCES users (userid)
);