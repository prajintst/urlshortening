ALTER TABLE public.short_urls
    ADD COLUMN created_at timestamp without time zone default current_timestamp,
    ADD COLUMN checked_at timestamp without time zone default current_timestamp;