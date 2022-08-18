CREATE DATABASE "finance-service"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

\connect finance-service

CREATE SCHEMA IF NOT EXISTS finance
    AUTHORIZATION postgres;

CREATE TABLE IF NOT EXISTS finance.banks
(
    uuid uuid NOT NULL,
    city text COLLATE pg_catalog."default" NOT NULL,
    name text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT banks_pkey PRIMARY KEY (uuid)
)

TABLESPACE pg_default;

CREATE TABLE IF NOT EXISTS finance.usds
(
    office_uuid uuid NOT NULL,
    buy double precision NOT NULL,
    sell double precision NOT NULL,
    CONSTRAINT usds_pkey PRIMARY KEY (office_uuid)
)

TABLESPACE pg_default;

CREATE TABLE IF NOT EXISTS finance.euros
(
    office_uuid uuid NOT NULL,
    buy double precision NOT NULL,
    sell double precision NOT NULL,
    CONSTRAINT euros_pkey PRIMARY KEY (office_uuid)
)

TABLESPACE pg_default;

CREATE TABLE IF NOT EXISTS finance.rubs
(
    office_uuid uuid NOT NULL,
    buy double precision NOT NULL,
    sell double precision NOT NULL,
    CONSTRAINT rubs_pkey PRIMARY KEY (office_uuid)
)

TABLESPACE pg_default;

CREATE TABLE IF NOT EXISTS finance.offices
(
    uuid uuid NOT NULL,
    address text COLLATE pg_catalog."default" NOT NULL,
    bank_uuid uuid NOT NULL,
    city text COLLATE pg_catalog."default" NOT NULL,
    euro_office_uuid uuid,
    rub_office_uuid uuid,
    usd_office_uuid uuid,
    CONSTRAINT offices_pkey PRIMARY KEY (uuid),
    CONSTRAINT fk242ptm01ut1kbqlk4rc818y2h FOREIGN KEY (usd_office_uuid)
        REFERENCES finance.usds (office_uuid),
    CONSTRAINT fk419xsrsvlypndc5cd7m6ws5b1 FOREIGN KEY (rub_office_uuid)
        REFERENCES finance.rubs (office_uuid),
    CONSTRAINT fktbaa67vm9r8deaerqnrm20tt7 FOREIGN KEY (euro_office_uuid)
        REFERENCES finance.euros (office_uuid)
)

TABLESPACE pg_default;

CREATE TABLE IF NOT EXISTS finance.banks_offices
(
    bank_uuid uuid NOT NULL,
    offices_uuid uuid NOT NULL,
    CONSTRAINT banks_offices_pkey PRIMARY KEY (bank_uuid, offices_uuid),
    CONSTRAINT uk_b5tu6figm1hwwju0bgh8unh7y UNIQUE (offices_uuid),
    CONSTRAINT fkcgi2m2khtn02i3id82ym5pqyu FOREIGN KEY (offices_uuid)
        REFERENCES finance.offices (uuid),
    CONSTRAINT fkpsok26y69v5id8v2se85kqdtm FOREIGN KEY (bank_uuid)
        REFERENCES finance.banks (uuid)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS finance.banks_offices
    OWNER to postgres;

ALTER TABLE IF EXISTS finance.offices
    OWNER to postgres;

ALTER TABLE IF EXISTS finance.rubs
    OWNER to postgres;

ALTER TABLE IF EXISTS finance.euros
    OWNER to postgres;

ALTER TABLE IF EXISTS finance.usds
    OWNER to postgres;

ALTER TABLE IF EXISTS finance.banks
    OWNER to postgres;