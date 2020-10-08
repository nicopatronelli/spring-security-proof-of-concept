SELECT *
FROM users;

SELECT *
FROM roles;

SELECT *
FROM users_roles;

-- username of admins
SELECT u.username 
FROM users u
	JOIN users_roles ur
		ON u.id = ur.user_id
	JOIN roles r
		ON  ur.rol_id = r.id
WHERE r.name = 'ROL_ADMIN'

-- Roles
INSERT INTO roles(id, name)
VALUES (1, 'ROL_ADMIN');

INSERT INTO roles(id, name)
VALUES (2, 'ROL_USER');

-- Users
INSERT INTO users(id, username, password, enabled)
VALUES (1, 'freddie', 'queen', true);

INSERT INTO users(id, username, password, enabled)
VALUES (2, 'braian', 'queen', true);

-- Roles by users
INSERT INTO users_roles
VALUES 
	(1, 1), -- freddie is ADMIN
	(2, 2); -- braian is USER
