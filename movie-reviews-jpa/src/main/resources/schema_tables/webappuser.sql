-- Table: public.webappuser

-- DROP TABLE IF EXISTS public.webappuser;

CREATE TABLE IF NOT EXISTS public.webappuser
(
    id bigint NOT NULL,
    lastupdatetime timestamp(6) without time zone,
    registrationtime timestamp(6) without time zone,
    country character varying(255) COLLATE pg_catalog."default",
    email character varying(255) COLLATE pg_catalog."default",
    password character varying(255) COLLATE pg_catalog."default",
    username character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT webappuser_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.webappuser
    OWNER to tiso;