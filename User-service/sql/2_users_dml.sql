\connect bot-user

INSERT INTO security.roles(role)
	VALUES ('ROLE_USER'), ('ROLE_ADMIN');

INSERT INTO security.users(
	uuid, chat_id, city, dt_create, dt_update, password, status, username, size)
	VALUES ('2f8b1bb5-f0cd-4ae8-afb8-cc69344b79f9', 1183323233, 'MINSK', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'pass', 'ACTIVATED', 'admin', 10);

INSERT INTO security.users_authorities (
    authorities_id,
    user_uuid)
VALUES ( (
        SELECT
            id
        FROM
            security.roles
        WHERE
            ROLE = 'ROLE_USER'), '2f8b1bb5-f0cd-4ae8-afb8-cc69344b79f9');

INSERT INTO security.users_authorities (
    authorities_id,
    user_uuid)
VALUES ( (
        SELECT
            id
        FROM
            security.roles
        WHERE
            ROLE = 'ROLE_ADMIN'), '2f8b1bb5-f0cd-4ae8-afb8-cc69344b79f9');
