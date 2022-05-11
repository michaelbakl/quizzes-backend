create table if not exists playersInRoom (
    roomId text,
    playerId text,
    primary key (roomId, playerId),
    foreign key (roomId) references room(roomId) on delete cascade on update cascade,
    foreign key (playerId) references users(userid)
);