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

        IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'types') THEN
            create type types AS ENUM ('checkbox', 'radiobutton');
        ELSE
            RAISE NOTICE 'Type types already exists';
        END IF;
    END
$$;


CREATE TABLE IF NOT EXISTS "subjects"
(
    "id"   SERIAL PRIMARY KEY,
    "name" varchar UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS "groups"
(
    "id"         SERIAL PRIMARY KEY,
    "name"       varchar UNIQUE  NOT NULL,
    "created_at" timestamp NOT NULL
);

CREATE TABLE IF NOT EXISTS "users"
(
    "id"          SERIAL PRIMARY KEY,
    "role_type"   roles NOT NULL ,
    "email"       varchar NOT NULL ,
    "hash"        varchar NOT NULL ,
    "salt"        varchar NOT NULL ,
    "first_name"  varchar NOT NULL ,
    "last_name"   varchar NOT NULL ,
    "middle_name" varchar,
    "birth_date"  date NOT NULL ,
    "work_title"  varchar,
    "created_at"  timestamp NOT NULL ,
    "avatar"      varchar,
    "group_id"    int REFERENCES "groups" ("id")
);



CREATE TABLE IF NOT EXISTS "tests"
(
    "id"          SERIAL PRIMARY KEY,
    "title"       varchar NOT NULL ,
    "description" varchar,
    "subject_id"  int REFERENCES "subjects" ("id") NOT NULL ,
    "is_random"   boolean NOT NULL ,
    "created_at"  timestamp NOT NULL ,
    "max_points"  int NOT NULL ,
    "creator_id"  int NOT NULL
);

CREATE TABLE IF NOT EXISTS "groups_tests"
(
    "test_id"      int REFERENCES "tests" ("id") NOT NULL ,
    "group_id"     int REFERENCES "groups" ("id") NOT NULL ,
    "is_necessary" boolean NOT NULL ,
    "max_attempts" int,
    "deadline"     timestamp,
    "time_limit"   int,
    PRIMARY KEY ("test_id", "group_id")
);

CREATE TABLE IF NOT EXISTS "questions"
(
    "id"            SERIAL PRIMARY KEY,
    "question_type" types NOT NULL ,
    "title"         varchar,
    "image"         varchar,
    "question_text" varchar NOT NULL ,
    "test_id"       int REFERENCES "tests" ("id") NOT NULL
);

CREATE TABLE IF NOT EXISTS "answers"
(
    "id"          SERIAL PRIMARY KEY,
    "image"       varchar,
    "answer_text" varchar NOT NULL ,
    "question_id" int REFERENCES "questions" ("id") NOT NULL ,
    "is_correct"  boolean NOT NULL ,
    "points"      int NOT NULL
);

CREATE TABLE IF NOT EXISTS "attempts"
(
    "id"           SERIAL PRIMARY KEY,
    "test_id"      int REFERENCES "tests" ("id") NOT NULL ,
    "user_id"      int REFERENCES "users" ("id") NOT NULL ,
    "score"        int NOT NULL ,
    "passing_date" timestamp NOT NULL
);
