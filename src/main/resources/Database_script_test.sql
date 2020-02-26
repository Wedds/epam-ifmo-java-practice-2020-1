DROP ALL OBJECTS;
create type roles AS ENUM ('admin', 'moderator', 'teacher', 'student');
create type types AS ENUM ('checkbox', 'radiobutton');

CREATE TABLE IF NOT EXISTS subjects
(
    id   SERIAL PRIMARY KEY,
    name varchar UNIQUE
);

CREATE TABLE IF NOT EXISTS groups
(
    id         SERIAL PRIMARY KEY,
    name       varchar UNIQUE,
    created_at timestamp
);

CREATE TABLE IF NOT EXISTS users
(
    id          SERIAL PRIMARY KEY,
    role_type   roles NOT NULL,
    email       varchar NOT NULL,
    hash        varchar NOT NULL,
    salt        varchar NOT NULL,
    first_name  varchar NOT NULL,
    last_name   varchar NOT NULL,
    middle_name varchar,
    birth_date  Date NOT NULL,
    work_title  varchar,
    created_at  timestamp NOT NULL,
    avatar      varchar,
    group_id    int REFERENCES groups (id) ON DELETE SET NULL ON UPDATE CASCADE
);



CREATE TABLE IF NOT EXISTS tests
(
    id          SERIAL PRIMARY KEY,
    title       varchar NOT NULL,
    description varchar,
    subject_id  int NOT NULL REFERENCES subjects (id) ON DELETE CASCADE ON UPDATE CASCADE,
    is_random   boolean NOT NULL,
    created_at  timestamp NOT NULL,
    max_points  int NOT NULL,
    creator_id  int NOT NULL REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS groups_tests
(
    test_id      int NOT NULL REFERENCES tests (id) ON DELETE CASCADE ON UPDATE CASCADE,
    group_id     int NOT NULL REFERENCES groups (id) ON DELETE CASCADE ON UPDATE CASCADE,
    is_necessary boolean NOT NULL,
    max_attempts int,
    deadline     timestamp,
    time_limit   int,
    PRIMARY KEY (test_id, group_id)
);

CREATE TABLE IF NOT EXISTS questions
(
    id            SERIAL PRIMARY KEY,
    question_type types NOT NULL,
    title         varchar,
    image         varchar,
    question_text varchar NOT NULL,
    test_id       int NOT NULL REFERENCES tests (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS answers
(
    id          SERIAL PRIMARY KEY,
    image       varchar,
    answer_text varchar NOT NULL,
    question_id int NOT NULL REFERENCES questions (id) ON DELETE CASCADE ON UPDATE CASCADE,
    is_correct  boolean NOT NULL,
    points      int NOT NULL
);

CREATE TABLE IF NOT EXISTS attempts
(
    id           SERIAL PRIMARY KEY,
    test_id      int NOT NULL REFERENCES tests (id) ON DELETE CASCADE ON UPDATE CASCADE,
    user_id      int NOT NULL REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    score        int NOT NULL,
    passing_date timestamp NOT NULL
);
