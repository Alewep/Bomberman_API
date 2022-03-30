DROP TABLE IF EXISTS PLAYER;
DROP TABLE IF EXISTS GAME;
DROP TABLE IF EXISTS HISTORY;



CREATE TABLE PLAYER (
    login_player varchar,
    password_player varchar NOT NULL,
    primary key (login_player)
);

CREATE TABLE GAME (
    game_id INTEGER PRIMARY KEY AUTOINCREMENT,
    winer varchar,
    map_name varchar,
    time_start timestamp NOT NULL,
    time_end timestamp NOT NULL,
    foreign key (winer) REFERENCES PlAYER(login_player)

);

CREATE TABLE HISTORY (
    login_player varchar,
    game_id int,
    primary key (login_player, game_id),
	foreign key (login_player) REFERENCES PlAYER(login_player)
);

