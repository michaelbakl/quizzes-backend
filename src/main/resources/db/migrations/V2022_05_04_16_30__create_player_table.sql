create table if not exists player (
    playerId text not null primary key,
    points int,
    foreign key (playerId) references users(email) on delete cascade on update cascade
);