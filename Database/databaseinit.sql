create database if not exists game;
use game;
create table accounts
(
    accountID   int auto_increment
        primary key,
    username    varchar(255)           not null,
    password    varchar(255)           not null,
    accessRight enum ('Admin', 'User') not null
);

create index username
    on accounts (username);

create table games
(
    gameID      int auto_increment
        primary key,
    price       double default 0 not null,
    name        varchar(255)     not null,
    description text             null,
    bannerURL   varchar(255)     null,
    genre       varchar(255)     null
);

create table users
(
    userID      int auto_increment
        primary key,
    username    varchar(255)         not null,
    balance     double     default 0 not null,
    primeStatus tinyint(1) default 0 not null,
    constraint users_ibfk_1
        foreign key (username) references accounts (username)
);

create table usergames
(
    userID int not null,
    gameID int not null,
    constraint `PRIMARY`
        primary key (userID, gameID),
    constraint usergames_ibfk_1
        foreign key (userID) references users (userID),
    constraint usergames_ibfk_2
        foreign key (gameID) references games (gameID)
);

create index gameID
    on usergames (gameID);

create index username
    on users (username);
