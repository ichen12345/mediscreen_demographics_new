DROP TABLE IF EXISTS patient CASCADE;

CREATE TABLE IF NOT EXISTS patient (
    id BIGSERIAL PRIMARY KEY, -- Use BIGSERIAL for auto-incrementing
    dob DATE,
    family VARCHAR(25),
    given VARCHAR(25),
    address VARCHAR(100),
    phone VARCHAR(25),
    sex VARCHAR(10)
);
