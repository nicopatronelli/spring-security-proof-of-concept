SELECT *
FROM users;

SELECT *
FROM user_roles;

INSERT INTO users 
VALUES (1, true, 'queen', 'freddie');

INSERT INTO user_roles
VALUES (1, 'ROLE_ADMIN');

INSERT INTO users 
VALUES (2, true, 'queen', 'braian');

INSERT INTO user_roles
VALUES (2, 'ROLE_USER');

SELECT *
FROM users
