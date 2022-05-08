create table if not exists game
(
    roomId            text not null primary key,
    score             int,
    questionsAmount   int,
    currentQuestionId text,
    status            text,
    foreign key (roomId) references room(roomId) on delete cascade on update cascade
);
