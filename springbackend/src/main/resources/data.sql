--states
INSERT INTO states (id, name, description)
VALUES (1, 'Active', 'Recort is active');

INSERT INTO states (id, name, description)
VALUES (2, 'InActive', 'Recort is inactive');

INSERT INTO states (id, name, description)
VALUES (3, 'Deleted', 'Recort is deleted');

--rights
INSERT INTO rights (id, name, description, state_id)
VALUES (1, 'getAllUser', 'Get All User', 1);

INSERT INTO rights (id, name, description, state_id)
VALUES (2, 'getAllActiveUser', 'Get All Active User', 1);

INSERT INTO rights (id, name, description, state_id)
VALUES (3, 'getUserById', 'Get User By Id', 1);

INSERT INTO rights (id, name, description, state_id)
VALUES (4, 'createUser', 'Create New User', 1);

INSERT INTO rights (id, name, description, state_id)
VALUES (5, 'updateUser', 'Update Existing User', 1);



--1. create Admin Role & User
--1.1. create Role
INSERT INTO roles (id, name, description, state_id)
VALUES (1, 'Admin', 'Admin Role With All Rights', 1);

--1.2. create RoleRights
INSERT INTO roles_rights (role_id, right_id, state_id)
SELECT 1, id, 1 FROM rights;

--1.3 create User
INSERT INTO users (id, first_name, last_name, user_name, password, description, email, phone, personal_id, state_id)
VALUES(1, 'James', 'Bond', 'admin', '1234', 'user with all rights', 'james.bond007@admin.com', '777 777 777', '007007007', 1);

--1.4 create UserRole
INSERT INTO users_roles (id, role_id, user_id, state_id)
VALUES (1, 1, 1, 1);


