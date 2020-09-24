INSERT INTO users(username, password, enabled)
VALUES ('freddie', 'queen', true);

INSERT INTO users(username, password, enabled)
VALUES ('braian', 'queen', true);

INSERT INTO authorities(username, authority)
VALUES ('freddie', 'ROLE_ADMIN');

INSERT INTO authorities(username, authority)
VALUES ('braian', 'ROLE_USER');