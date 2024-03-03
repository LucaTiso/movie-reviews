-- Table: public.security_user

-- DROP TABLE IF EXISTS public.security_user;

CREATE TABLE IF NOT EXISTS public.security_user
(
    id bigint NOT NULL,
    username character varying(255) COLLATE pg_catalog."default",
    email character varying(255) COLLATE pg_catalog."default",
    password character varying(255) COLLATE pg_catalog."default",
    country character varying(255) COLLATE pg_catalog."default",
    first_name character varying(255) COLLATE pg_catalog."default",
    last_name character varying(255) COLLATE pg_catalog."default",
    security_role character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT security_user_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.security_user
    OWNER to tiso;