-- DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS trips;
DROP TABLE IF EXISTS cars;

DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq
  START 100000;

CREATE TABLE users
(
  id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name       VARCHAR                              NOT NULL,
  email      VARCHAR                              NOT NULL,
  password   VARCHAR DEFAULT '0000'               NOT NULL,
  registered TIMESTAMP DEFAULT now()              NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx
  ON users (email);

INSERT INTO users (name, email) VALUES ('Valera', 'valer@mail.ru');
INSERT INTO users (name, email) VALUES ('Vasya', 'vas@mail.ru');

-- CREATE TABLE user_roles
-- (
--   user_id INTEGER NOT NULL,
--   role    VARCHAR,
--   CONSTRAINT user_roles_idx UNIQUE (user_id, role),
--   FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
-- );

CREATE TABLE cars (
  id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  user_id     INTEGER             NOT NULL,
  active      BOOL DEFAULT TRUE   NOT NULL,
  description TEXT,
  mileage     INTEGER DEFAULT 0   NOT NULL,
  fuel        FLOAT4 DEFAULT 0    NOT NULL,
  winter_city FLOAT4 DEFAULT 10   NOT NULL,
  winter_road FLOAT4 DEFAULT 10   NOT NULL,
  summer_city FLOAT4 DEFAULT 10   NOT NULL,
  summer_road FLOAT4 DEFAULT 10   NOT NULL,
  warmup      FLOAT4 DEFAULT 0.5  NOT NULL,
  idling      FLOAT4 DEFAULT 2.5  NOT NULL,
  summer_time BOOL DEFAULT TRUE   NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

INSERT INTO cars (user_id, description, mileage, fuel, winter_city, winter_road, summer_city, summer_road, idling)
VALUES (100000, 'Nissan', 98993, 26.65, 13.43, 11.43, 12.45, 10.49, 2.45);
INSERT INTO cars (user_id, description, mileage, fuel, winter_city, winter_road, summer_city, summer_road, idling)
VALUES (100000, 'Opel', 42040, 14.68, 12.78, 10.98, 11.43, 9.63, 2.25);

CREATE TABLE trips (
  id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  car_id      INTEGER                               NOT NULL,
  date        TIMESTAMP DEFAULT now()               NOT NULL,
  mileage_c   FLOAT4 DEFAULT 0                      NOT NULL,
  mileage_r   FLOAT4 DEFAULT 0                      NOT NULL,
  warmup_time TIME DEFAULT '00:00:00'               NOT NULL,
  idling_time TIME DEFAULT '00:00:00'               NOT NULL,
  fuel_left   FLOAT4                                NOT NULL,
  summer      BOOL DEFAULT TRUE                     NOT NULL,
  description TEXT,
  timestamp   TIMESTAMP DEFAULT now()               NOT NULL,
  FOREIGN KEY (car_id) REFERENCES cars (id) ON DELETE CASCADE
);

INSERT INTO trips (car_id, fuel_left, description) VALUES (100002, 25, 'Pafik_Test_Trip');
INSERT INTO trips (car_id, fuel_left, description) VALUES (100002, 20, 'Pafik_Test_Trip2');
INSERT INTO trips (car_id, fuel_left, description) VALUES (100003, 500, 'Opel_Test_Trip3');
