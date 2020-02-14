DO
$$
    BEGIN
        IF EXISTS(SELECT FROM pg_database WHERE datname = 'testing_system_db') THEN
            RAISE NOTICE 'Database testing_system_db already exists';
        ELSE
            PERFORM dblink_exec('dbname=' || current_database()
                , 'CREATE DATABASE testing_system_db');
        END IF;

        IF EXISTS(SELECT FROM pg_user WHERE usename = 'evangelion') THEN
            RAISE NOTICE 'User evangelion already exists';
        ELSE
            CREATE USER evangelion WITH encrypted PASSWORD '12345';
            GRANT all privileges ON DATABASE testing_system_db TO evangelion;
            ALTER USER evangelion WITH SUPERUSER;
        END IF;
    END
$$;


\c testing_system_db;

DO
$$
    BEGIN
        IF NOT EXISTS(SELECT 1 FROM pg_type WHERE typname = 'roles') THEN
            create type roles AS ENUM ('admin', 'moderator', 'teacher', 'student');
        ELSE
            RAISE NOTICE 'Type roles already exists';
        END IF;

        IF NOT EXISTS(SELECT 1 FROM pg_type WHERE typname = 'block_types') THEN
            create type types AS ENUM ('checkbox', 'radiobutton');
        ELSE
            RAISE NOTICE 'Type types already exists';
        END IF;
    END
$$;


CREATE TABLE IF NOT EXISTS "subjects"
(
    "id"   SERIAL PRIMARY KEY,
    "name" varchar
);

CREATE TABLE IF NOT EXISTS "users"
(
    "id"          SERIAL PRIMARY KEY,
    "role_type"   roles,
    "email"       varchar,
    "hash"        varchar,
    "salt"        varchar,
    "first_name"  varchar,
    "last_name"   varchar,
    "middle_name" varchar,
    "birth_date"  date,
    "work_title"  varchar,
    "created_at"  Date,
    "avatar"      varchar,
    "group_id"    int
);

CREATE TABLE IF NOT EXISTS "groups"
(
    "id"         SERIAL PRIMARY KEY,
    "name"       varchar,
    "created_at" Date
);

CREATE TABLE IF NOT EXISTS "tests"
(
    "id"          SERIAL PRIMARY KEY,
    "title"       varchar,
    "description" varchar,
    "subject_id"  int,
    "is_random"   boolean,
    "created_at"  Date,
    "max_points"  int,
    "creator_id"  int
);

CREATE TABLE IF NOT EXISTS "groups_tests"
(
    "test_id"       int,
    "group_id"      int,
    "is_neccessary" boolean,
    "max_attemps"   int DEFAULT 3,
    "deadline"      Date,
    "time_limit"    int,
    PRIMARY KEY ("test_id", "group_id")
);

CREATE TABLE IF NOT EXISTS "questions"
(
    "id"            SERIAL PRIMARY KEY,
    "question_type" types,
    "title"         varchar,
    "image"         varchar,
    "question_text" varchar,
    "test_id"       int
);

CREATE TABLE IF NOT EXISTS "answers"
(
    "id"          SERIAL PRIMARY KEY,
    "image"       varchar,
    "answer_text" varchar,
    "question_id" int,
    "is_correct"  boolean,
    "points"      int
);

CREATE TABLE IF NOT EXISTS "attempts"
(
    "id"           SERIAL PRIMARY KEY,
    "test_id"      int,
    "user_id"      int,
    "score"        int,
    "passing_date" Date
);

ALTER TABLE "users"
    ADD FOREIGN KEY ("group_id") REFERENCES "groups" ("id");

ALTER TABLE "tests"
    ADD FOREIGN KEY ("subject_id") REFERENCES "subjects" ("id");

ALTER TABLE "groups_tests"
    ADD FOREIGN KEY ("test_id") REFERENCES "tests" ("id");

ALTER TABLE "groups_tests"
    ADD FOREIGN KEY ("group_id") REFERENCES "groups" ("id");

ALTER TABLE "questions"
    ADD FOREIGN KEY ("test_id") REFERENCES "tests" ("id");

ALTER TABLE "answers"
    ADD FOREIGN KEY ("question_id") REFERENCES "questions" ("id");

ALTER TABLE "attempts"
    ADD FOREIGN KEY ("test_id") REFERENCES "tests" ("id");

ALTER TABLE "attempts"
    ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

COMMENT ON COLUMN "groups"."name" IS 'unique';