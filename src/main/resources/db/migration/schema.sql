-- SEQUENCE: public.products_id_seq

-- DROP SEQUENCE public.products_id_seq;

CREATE SEQUENCE public.products_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.products_id_seq
    OWNER TO postgres;

-- Table: public.products

-- DROP TABLE public.products;

CREATE TABLE public.products
(
    id bigint NOT NULL DEFAULT nextval('products_id_seq'::regclass),
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    created_by bigint,
    updated_by bigint,
    code character varying(255) COLLATE pg_catalog."default",
    description character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    status character varying(255) COLLATE pg_catalog."default",
    type character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT products_pkey PRIMARY KEY (id)
);

ALTER TABLE public.products
    OWNER to postgres;

-- SEQUENCE: public.roles_id_seq

-- DROP SEQUENCE public.roles_id_seq;

CREATE SEQUENCE public.roles_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.roles_id_seq
    OWNER TO postgres;

-- Table: public.roles

-- DROP TABLE public.roles;


CREATE TABLE public.roles
(
    id bigint NOT NULL DEFAULT nextval('roles_id_seq'::regclass),
    name character varying(60) COLLATE pg_catalog."default",
    CONSTRAINT roles_pkey PRIMARY KEY (id),
    CONSTRAINT uk_name UNIQUE (name)
);

ALTER TABLE public.roles
    OWNER to postgres;

-- Table: public.user_roles

-- DROP TABLE public.user_roles;

CREATE TABLE public.user_roles
(
    user_id bigint NOT NULL,
    role_id bigint NOT NULL,
    CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id),
    CONSTRAINT user_roles_roles_fkey FOREIGN KEY (role_id)
        REFERENCES public.roles (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT user_roles_users_fkey FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

ALTER TABLE public.user_roles
    OWNER to postgres;

-- SEQUENCE: public.users_id_seq

-- DROP SEQUENCE public.users_id_seq;

CREATE SEQUENCE public.users_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.users_id_seq
    OWNER TO postgres;
-- Table: public.users

-- DROP TABLE public.users;

CREATE TABLE public.users
(
    id bigint NOT NULL DEFAULT nextval('users_id_seq'::regclass),
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    email character varying(40) COLLATE pg_catalog."default",
    name character varying(40) COLLATE pg_catalog."default",
    password character varying(100) COLLATE pg_catalog."default",
    username character varying(15) COLLATE pg_catalog."default",
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT uk_email UNIQUE (email),
    CONSTRAINT uk_username UNIQUE (username)
);

ALTER TABLE public.users
    OWNER to postgres;