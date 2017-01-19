DROP DATABASE IF EXISTS airlinedb;

CREATE DATABASE IF NOT EXISTS airlinedb
  DEFAULT CHARACTER SET UTF8;

USE airlinedb;

CREATE TABLE IF NOT EXISTS users (
  id       INT UNIQUE PRIMARY KEY     NOT NULL AUTO_INCREMENT,
  name     VARCHAR(45)                NOT NULL,
  login    VARCHAR(10) UNIQUE         NOT NULL,
  password VARCHAR(15)                NOT NULL,
  role_id  INT                        NOT NULL
);

CREATE TABLE IF NOT EXISTS roles (
  id   INT UNIQUE PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(15) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS flights (
  id                 INT UNIQUE PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name               VARCHAR(25)            NOT NULL,
  departure_location VARCHAR(25)            NOT NULL,
  arrive_location    VARCHAR(25)            NOT NULL,
  status             VARCHAR(15)            NOT NULL,
  departure_date     DATETIME               NOT NULL,
  arrive_date        DATETIME               NOT NULL
);

CREATE TABLE IF NOT EXISTS staff (
  id         INT UNIQUE PRIMARY KEY AUTO_INCREMENT,
  first_name VARCHAR(15) NOT NULL,
  last_name  VARCHAR(15) NOT NULL,
  role_id    INT         NOT NULL
);

CREATE TABLE staff_roles (
  id   INT UNIQUE PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(15) UNIQUE NOT NULL
);


CREATE TABLE IF NOT EXISTS flight_brigade (
  flight_id INT NOT NULL,
  staff_id   INT NOT NULL
);

-- # CREATE TABLE IF NOT EXISTS requests (
-- #   id      INT(11) UNIQUE    NOT NULL AUTO_INCREMENT,
-- #   from_id INT(11)           NOT NULL,
-- #   to_id   INT(11)           NOT NULL,
-- #   message VARCHAR(100)      NOT NULL,
-- #   is_read TINYINT(1)        NOT NULL DEFAULT '0',
-- #   date    TIMESTAMP         NOT NULL DEFAULT CURRENT_TIMESTAMP
-- # );
