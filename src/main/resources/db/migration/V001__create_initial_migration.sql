CREATE TABLE usuario (
    id UUID PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    creation_date TIMESTAMP,
    modified_date TIMESTAMP,
    last_login TIMESTAMP,
    token VARCHAR(255),
    active BOOLEAN
);

CREATE TABLE phone (
    id UUID PRIMARY KEY,
    number VARCHAR(255),
    country_code VARCHAR(255),
    city_code VARCHAR(255),
    user_id UUID,
    FOREIGN KEY (user_id) REFERENCES usuario (id)
);
