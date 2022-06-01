# 7quizzes-backend

Spring Boot application for quizzes.
You can create or join room, answer questions in it and then see if the answers were right.

### Getting started 
Download this project and download necessary tools.

#### Maven 
`sudo apt-get install maven` - if your use ubuntu \
`brew install maven` - install maven with homebrew 

#### Database 
This project uses Postgresql 14.3

To install enter:

`sudo sh -c 'echo "deb http://apt.postgresql.org/pub/repos/apt $(lsb_release -cs)-pgdg main" > /etc/apt/sources.list.d/pgdg.list'`

`wget --quiet -O - https://www.postgresql.org/media/keys/ACCC4CF8.asc | sudo apt-key add -`

`sudo apt update`

`sudo apt -y install postgresql-14.3` 

### Run 

Use this command to run the project: 
`java -jar seven_quizzes-1.0-SNAPSHOT.jar`

### Using the application

At first you should sign in or sign up.

Requests which can do a user with privilege `USER`: 

#### POST-request to /signin
Sing in into an existing account. 

```
{ 
    "email": "username@gmail.com",
    "password": "w3GeUSKMk8*Nh7*G"
} 
```

#### POST-request to /signup
You can create an account and then sign in into it. 

```
{ 
    "email": "username@gmail.com", 
    "password": "w3GeUSKMk8*Nh7*G" 
} 
```

#### GET-request to /rooms
Allows you to get list of rooms.

#### POST-request to /rooms
Allows you to create a room.

```
{ 
    "roomName": "Room 1" 
}
```

#### GET-request to /rooms/{roomId}
Allows you to get room by id.

#### POST-request to /rooms/{roomId}/join
Allows you to join a room with specified id.

#### GET-request to /rooms/{roomId}/game
Allows you to get game info in the specified room.

#### POST-request to /rooms/{roomId}/game/start
Allows starting a game in the specified room to it`s owner.

#### GET-request to /rooms/{roomId}/game/question/{questionId}
Allows getting question by it`s id in the specified room.

#### POST-request to /rooms/{roomId}/game/question/{questionId}/answer
Allows answering the question by it`s id in the specified room.

```
{
  "answerId": "3fa85f64-5717-4562-b3fc-2c963f66afa6"
}
```

#### GET-request to /whoami
Allows getting information about the authorized user.

Requests which can be done by user with privilege `ADMIN`: 

#### GET-request to /users
Allows getting list of all users on the server.

#### GET-request to /users/{id}
Allows getting user by specified id.





