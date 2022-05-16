create table if not exists questionsToGame (
    roomId text not null,
    questionId text not null,
    primary key (roomId, questionId),
    foreign key (roomId) references game(roomId) on delete cascade on update cascade,
    foreign key (questionId) references question(questionId) on delete set null on update cascade
);