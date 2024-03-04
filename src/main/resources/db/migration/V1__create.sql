-- creating users table
CREATE TABLE users
(
    id            SERIAL PRIMARY KEY,
    username      VARCHAR(255)        NOT NULL,
    email         VARCHAR(255) UNIQUE NOT NULL,
    password_hash VARCHAR(255)        NOT NULL,
    role          VARCHAR(50)         NOT NULL
);

-- creating events table
CREATE TABLE events
(
    id           SERIAL PRIMARY KEY,
    organizer_id INTEGER      NOT NULL,
    title        VARCHAR(255) NOT NULL,
    description  TEXT,
    start_time   TIMESTAMP    NOT NULL,
    end_time     TIMESTAMP    NOT NULL,
    location     VARCHAR(255),
    capacity     INTEGER,
    is_public    BOOLEAN DEFAULT true,
    FOREIGN KEY (organizer_id) REFERENCES users (id)
);

-- creating bookings table
CREATE TABLE bookings
(
    id         SERIAL PRIMARY KEY,
    user_id            INTEGER NOT NULL,
    event_id           INTEGER NOT NULL,
    status            VARCHAR(50),
    number_of_attendees INTEGER DEFAULT 1,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (event_id) REFERENCES events (id)
);

-- creating sessions table
CREATE TABLE sessions
(
    id SERIAL PRIMARY KEY,
    event_id   INTEGER      NOT NULL,
    title     VARCHAR(255) NOT NULL,
    start_time TIMESTAMP    NOT NULL,
    end_time   TIMESTAMP    NOT NULL,
    speaker   VARCHAR(255),
    location  VARCHAR(255),
    FOREIGN KEY (event_id) REFERENCES events (id)
);

-- creating user_sessions table
CREATE TABLE user_sessions
(
    user_id    INTEGER NOT NULL,
    session_id INTEGER NOT NULL,
    status    VARCHAR(50),
    PRIMARY KEY (user_id, session_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (session_id) REFERENCES sessions (id)
);

-- creating event_categories table
CREATE TABLE event_categories
(
    id  SERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description TEXT
);

-- creating event_category_mapping table
CREATE TABLE event_category_mapping
(
    event_id    INTEGER NOT NULL,
    category_id INTEGER NOT NULL,
    PRIMARY KEY (event_id, category_id),
    FOREIGN KEY (event_id) REFERENCES events (id),
    FOREIGN KEY (category_id) REFERENCES event_categories (id)
);
