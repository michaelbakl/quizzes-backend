CREATE TABLE if not exists question (
         questionId text not null primary key,
         content text,
         correctAnswerId text
);
