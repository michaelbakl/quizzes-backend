create table if not exists answersToQuestions
(
    questionId text,
    answerId   text,
    primary key (questionId, answerId),
    foreign key (questionId) references question (questionId),
    foreign key (answerId) references answer (answerId)
);