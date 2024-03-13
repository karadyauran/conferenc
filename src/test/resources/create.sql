-- creating users table
CREATE TABLE users
(
    id       UUID PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    email    VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255)        NOT NULL,
    role     VARCHAR(50)         NOT NULL
);

-- creating events table
CREATE TABLE events
(
    id           UUID PRIMARY KEY,
    organizer_id UUID         NOT NULL,
    title        VARCHAR(255) NOT NULL,
    description  TEXT,
    start_time   TIMESTAMP    NOT NULL,
    end_time     TIMESTAMP    NOT NULL,
    location     VARCHAR(255),
    capacity     INTEGER,
    is_public    BOOLEAN DEFAULT true,
    FOREIGN KEY (organizer_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- creating bookings table
CREATE TABLE bookings
(
    id                  UUID PRIMARY KEY,
    user_id             UUID NOT NULL,
    event_id            UUID NOT NULL,
    status              VARCHAR(50) DEFAULT 'PENDING',
    number_of_attendees INTEGER     DEFAULT 1,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (event_id) REFERENCES events (id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- creating sessions table
CREATE TABLE sessions
(
    id         UUID PRIMARY KEY,
    event_id   UUID         NOT NULL,
    title      VARCHAR(255) NOT NULL,
    start_time TIMESTAMP    NOT NULL,
    end_time   TIMESTAMP    NOT NULL,
    speaker    VARCHAR(255),
    location   VARCHAR(255),
    FOREIGN KEY (event_id) REFERENCES events (id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- creating user_sessions table
CREATE TABLE user_sessions
(
    user_id    UUID NOT NULL,
    session_id UUID NOT NULL,
    status     VARCHAR(50),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (session_id) REFERENCES sessions (id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- creating event_categories table
CREATE TABLE event_categories
(
    id          UUID PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description TEXT
);

-- creating event_category_mapping table
CREATE TABLE event_category_mapping
(
    event_id    UUID NOT NULL,
    category_id UUID NOT NULL,
    FOREIGN KEY (event_id) REFERENCES events (id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES event_categories (id) ON DELETE CASCADE
);

-- Inserting test data into the users table
INSERT INTO users (id, username, email, password, role)
VALUES ('550e8400-e29b-41d4-a716-446655440000', 'john_doe', 'john@example.com', 'password123', 'ORGANIZER'),
       ('fa163f16-71f8-4d52-a8c3-215df450a285', 'jane_smith', 'jane@example.com', 'qwerty456', 'ATTENDEE');

-- Inserting test data into the events table
INSERT INTO events (id, organizer_id, title, description, start_time, end_time, location, capacity, is_public)
VALUES ('7d6bb0d2-fc4c-4879-b15e-df7a145ce9a1', '550e8400-e29b-41d4-a716-446655440000', 'Tech Conference 2024',
        'Annual tech conference featuring the latest innovations.', '2024-04-15 09:00:00', '2024-04-16 18:00:00',
        'Convention Center', 500, true),
       ('b8617157-8c20-4036-9fe0-d60dedfba2f3', '550e8400-e29b-41d4-a716-446655440000', 'Tech Conference 2024',
        'Annual tech conference featuring the latest innovations.', '2024-04-15 09:00:00', '2024-04-16 18:00:00',
        'Convention Center', 0, true),
       ('f1a60e4f-ae7e-447d-a08a-2d97a356b4e7', '550e8400-e29b-41d4-a716-446655440000', 'Workshop on AI',
        'Hands-on workshop exploring artificial intelligence concepts.', '2024-05-10 13:00:00', '2024-05-10 17:00:00',
        'Tech Hub', 50, true);

-- Inserting test data into the bookings table
INSERT INTO bookings (id, user_id, event_id, status, number_of_attendees)
VALUES ('3fde6a02-6824-447f-a621-5f103bf33b76', 'fa163f16-71f8-4d52-a8c3-215df450a285',
        '7d6bb0d2-fc4c-4879-b15e-df7a145ce9a1', 'CONFIRMED', 2),
       ('ce25d345-1c81-4e9b-8114-8a7548c8a5b3', 'fa163f16-71f8-4d52-a8c3-215df450a285',
        'f1a60e4f-ae7e-447d-a08a-2d97a356b4e7', 'PENDING', 1);

-- Inserting test data into the sessions table
INSERT INTO sessions (id, event_id, title, start_time, end_time, speaker, location)
VALUES ('a7c518d1-f074-4095-977d-3c123e6b13cc', '7d6bb0d2-fc4c-4879-b15e-df7a145ce9a1', 'Introduction to AI',
        '2024-04-15 10:00:00', '2024-04-15 11:30:00', 'Dr. Smith', 'Room A'),
       ('f865cd7e-0381-4f22-b624-af27dbf48d17', 'f1a60e4f-ae7e-447d-a08a-2d97a356b4e7', 'Future of Robotics',
        '2024-04-15 12:00:00', '2024-04-15 13:30:00', 'Dr. Johnson', 'Room B');

-- Inserting test data into the user_sessions table
INSERT INTO user_sessions (user_id, session_id, status)
VALUES ('fa163f16-71f8-4d52-a8c3-215df450a285', 'a7c518d1-f074-4095-977d-3c123e6b13cc', 'ATTENDED'),
       ('fa163f16-71f8-4d52-a8c3-215df450a285', 'f865cd7e-0381-4f22-b624-af27dbf48d17', 'REGISTERED');

-- Inserting test data into the event_categories table
INSERT INTO event_categories (id, name, description)
VALUES ('6a646d1c-5d76-4629-9107-1ec2c25c7753', 'Technology', 'Events related to technology and innovation'),
       ('2a171f32-8d35-43f3-b3f8-bc1fdac52f4c', 'Artificial Intelligence', 'Events focusing on AI advancements');

-- Inserting test data into the event_category_mapping table
INSERT INTO event_category_mapping (event_id, category_id)
VALUES ('7d6bb0d2-fc4c-4879-b15e-df7a145ce9a1', '6a646d1c-5d76-4629-9107-1ec2c25c7753'),
       ('f1a60e4f-ae7e-447d-a08a-2d97a356b4e7', '2a171f32-8d35-43f3-b3f8-bc1fdac52f4c');

