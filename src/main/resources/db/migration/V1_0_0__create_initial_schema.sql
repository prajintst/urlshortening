-- Table: public.short_urls

-- DROP TABLE public.short_urls;

CREATE TABLE IF NOT EXISTS public.short_urls
(
    id int4 NOT NULL,
    is_alive boolean,
    url character varying(2048) COLLATE pg_catalog."default",
    CONSTRAINT short_urls_pkey PRIMARY KEY (id),
    CONSTRAINT uk_6sn0w9qm42t3vw03rp1tdqva UNIQUE (url)
    )

    TABLESPACE pg_default;

ALTER TABLE public.short_urls
    OWNER to postgres;