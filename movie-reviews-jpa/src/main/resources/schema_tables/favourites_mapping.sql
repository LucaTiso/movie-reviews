-- Table: public.favourites_mapping

-- DROP TABLE IF EXISTS public.favourites_mapping;

CREATE TABLE IF NOT EXISTS public.favourites_mapping
(
    movie_id bigint NOT NULL,
    webapp_user_id bigint NOT NULL,
    CONSTRAINT favourites_mapping_pkey PRIMARY KEY (movie_id, webapp_user_id),
    CONSTRAINT fk1eoj4pl1qsnvehdfcu7x1nimm FOREIGN KEY (movie_id)
        REFERENCES public.movie_new (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk96qaabimqwjypywmw0cwm46iq FOREIGN KEY (webapp_user_id)
        REFERENCES public.webappuser (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.favourites_mapping
    OWNER to tiso;