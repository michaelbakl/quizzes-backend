CREATE TABLE if not exists answer
(
    answerId   text not null primary key,
    answerText text,
    points     int
);