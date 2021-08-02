DROP TABLE IF EXISTS todo;

DROP TABLE IF EXISTS USER_ROLE;
DROP TABLE IF EXISTS ROLE;
DROP TABLE IF EXISTS USER;

CREATE TABLE USER 
(
ID INT NOT NULL AUTO_INCREMENT,
USER_NAME VARCHAR(20),
PASSWORD VARCHAR(256), 
PRIMARY KEY (ID),
UNIQUE KEY (USER_NAME)
);

CREATE TABLE todo
(
 id varchar(36) not null primary key,
 description varchar(255) not null,
 username varchar(255) not null,
 created timestamp,
 modified timestamp,
 completed boolean
);

CREATE TABLE ROLE 
(
ID INT NOT NULL AUTO_INCREMENT,
NAME VARCHAR(20),
PRIMARY KEY (ID)
);

CREATE TABLE USER_ROLE(
USER_ID int,
ROLE_ID int,
FOREIGN KEY (user_id)
REFERENCES user(id),
FOREIGN KEY (role_id)
REFERENCES role(id)
);

insert into user(USER_NAME,password) values ('test','pwd123');
insert into user(USER_NAME,password) values ('john','john123');
insert into user(USER_NAME,password) values ('paul','pwd123');

insert into role values(1,'ROLE_ADMIN');
insert into role values(2,'ROLE_USER');

insert into user_role values(1,1);
insert into user_role values(2,1);
insert into user_role values(3,1);


