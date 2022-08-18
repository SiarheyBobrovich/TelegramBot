CREATE DATABASE "bot-user"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

\connect bot-user

CREATE SCHEMA IF NOT EXISTS security
    AUTHORIZATION postgres;

CREATE TABLE IF NOT EXISTS security.roles
(
    id bigint NOT NULL DEFAULT nextval('security.roles_id_seq'::regclass),
    role text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT roles_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

CREATE TABLE IF NOT EXISTS security.users
(
    uuid uuid NOT NULL,
    chat_id bigint NOT NULL,
    city text COLLATE pg_catalog."default" NOT NULL,
    dt_create timestamp without time zone NOT NULL,
    dt_update timestamp(3) without time zone NOT NULL,
    password text COLLATE pg_catalog."default" NOT NULL,
    status text COLLATE pg_catalog."default" NOT NULL,
    username text COLLATE pg_catalog."default" NOT NULL,
    size integer NOT NULL DEFAULT 10,
    CONSTRAINT users_pkey PRIMARY KEY (uuid),
    CONSTRAINT uk_nr2rmfhq6wfp39vcduy7iketb UNIQUE (chat_id),
    CONSTRAINT uk_r43af9ap4edm43mmtq01oddj6 UNIQUE (username)
)

TABLESPACE pg_default;

CREATE TABLE IF NOT EXISTS security.users_authorities
(
    user_uuid uuid NOT NULL,
    authorities_id bigint NOT NULL,
    CONSTRAINT users_authorities_pkey PRIMARY KEY (user_uuid, authorities_id),
    CONSTRAINT fk40fukc61kvbvpc2rhv01q1g2l FOREIGN KEY (authorities_id)
        REFERENCES security.roles (id) MATCH SIMPLE,
    CONSTRAINT fkba4prrcyu0sqh6or2hueohe4p FOREIGN KEY (user_uuid)
        REFERENCES security.users (uuid) MATCH SIMPLE
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS security.users_authorities
    OWNER to postgres;

ALTER TABLE IF EXISTS security.users OWNER TO postgres;

ALTER TABLE IF EXISTS security.users_authorities OWNER TO postgres;

ALTER TABLE IF EXISTS security.roles OWNER TO postgres;