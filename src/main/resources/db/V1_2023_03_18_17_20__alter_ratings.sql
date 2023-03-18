ALTER TABLE ratings
ALTER COLUMN rating_stars type integer using rating_stars::integer;