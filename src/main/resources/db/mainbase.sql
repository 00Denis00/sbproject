CREATE DATABASE IF NOT EXISTS mainbase;
USE mainbase;

CREATE TABLE IF NOT EXISTS files
(
	id INT PRIMARY KEY AUTO_INCREMENT,
	name varchar(255)
);

CREATE TABLE IF NOT EXISTS histories
(
	id INT PRIMARY KEY,
	user_id int not null
);

CREATE TABLE IF NOT EXISTS histories_files
(
	History_id int not null,
    files_id int not null
);

CREATE TABLE IF NOT EXISTS users
(
	id INT PRIMARY KEY AUTO_INCREMENT,
	name varchar(255)
);

