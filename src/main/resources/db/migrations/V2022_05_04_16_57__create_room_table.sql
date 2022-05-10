create table if not exists room (
    roomId text not null primary key,
    roomName text,
    ownerId text
);