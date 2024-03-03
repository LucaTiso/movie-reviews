-- Table: public.webappreview

-- DROP TABLE IF EXISTS public.webappreview;

CREATE TABLE IF NOT EXISTS public.webappreview
(
    rating integer NOT NULL,
    reviewdate date,
    id bigint NOT NULL,
    movie_id bigint,
    reviewtime timestamp(6) without time zone,
    webapp_user_id bigint NOT NULL,
    text character varying(255) COLLATE pg_catalog."default",
    username character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT webappreview_pkey PRIMARY KEY (id),
    CONSTRAINT fk1akeutkt4b7c9y15tvk68lvos FOREIGN KEY (movie_id)
        REFERENCES public.movie_new (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk77nn77asyfw8d5j4ykd2rs8nf FOREIGN KEY (webapp_user_id)
        REFERENCES public.webappuser (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.webappreview
