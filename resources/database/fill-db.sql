USE airlinedb;

-- Roles
INSERT INTO roles VALUE (DEFAULT, 'Administrator');
INSERT INTO roles VALUE (DEFAULT, 'Dispatcher');

-- Staff roles
INSERT INTO staff_roles VALUE (DEFAULT, 'Pilot');
INSERT INTO staff_roles VALUE (DEFAULT, 'Navigator');
INSERT INTO staff_roles VALUE (DEFAULT, 'Radioman');
INSERT INTO staff_roles VALUE (DEFAULT, 'Stewardess');

-- Users
INSERT INTO users VALUE(DEFAULT, 'Ivanov Ivan', 'ivanov', 'ivanov', 1);
INSERT INTO users VALUE(DEFAULT, 'Petrov Petr', 'petrov', 'petrov', 2);

-- Flight
INSERT INTO flights VALUE
    (DEFAULT, 'London - Munich', 'London', 'Munich', 2, '2016-02-12 21:40:00', '2016-02-12 22:40:00');
INSERT INTO flights VALUE
    (DEFAULT, 'Istanbul - New York', 'Istanbul', 'New York', 0, '2016-02-12 19:40:00', '2016-02-13 04:40:00');
INSERT INTO flights VALUE
    (DEFAULT, 'Kiev - Moscow', 'Kiev', 'Moscow', 0, '2017-02-12 19:40:00', '2017-02-13 04:40:00');

-- Staff

INSERT INTO staff VALUE(DEFAULT, 'Bob', 'Smith', 1);
INSERT INTO staff VALUE(DEFAULT, 'Martin', 'McFly', 1);
INSERT INTO staff VALUE(DEFAULT, 'Alex', 'Aiono', 2);
INSERT INTO staff VALUE(DEFAULT, 'John', 'Deere', 2);
INSERT INTO staff VALUE(DEFAULT, 'Ted', 'Lapidus', 3);
INSERT INTO staff VALUE(DEFAULT, 'Roy', 'Canin', 3);
INSERT INTO staff VALUE(DEFAULT, 'Ann', 'Coulter', 4);
INSERT INTO staff VALUE(DEFAULT, 'Michel', 'Renee', 4);
INSERT INTO staff VALUE(DEFAULT, 'Margaret', 'Qualley', 4);

INSERT INTO flight_brigade VALUE(1, 1);
INSERT INTO flight_brigade VALUE(1, 3);
INSERT INTO flight_brigade VALUE(1, 5);
INSERT INTO flight_brigade VALUE(1, 7);

INSERT INTO flight_brigade VALUE(2, 2);
INSERT INTO flight_brigade VALUE(2, 4);
INSERT INTO flight_brigade VALUE(2, 6);
INSERT INTO flight_brigade VALUE(2, 8);
INSERT INTO flight_brigade VALUE(2, 9);

INSERT INTO requests VALUE(DEFAULT, 2, 1, 'Test request', DEFAULT, DEFAULT);

SELECT *
FROM requests;


SELECT *
FROM roles;

SELECT *
FROM staff_roles;

SELECT *
FROM users;

SELECT *
FROM flights;

SELECT *
FROM staff;