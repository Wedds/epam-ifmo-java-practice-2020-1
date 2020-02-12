DO $$
    BEGIN
        IF EXISTS (SELECT FROM pg_database WHERE datname = 'testing_system_db') THEN
            RAISE NOTICE 'Database testing_system_db already exists';
        ELSE
            PERFORM dblink_exec('dbname=' || current_database()
                , 'CREATE DATABASE testing_system_db');
        END IF;

        IF EXISTS (SELECT FROM pg_user WHERE usename = 'evangelion') THEN
            RAISE NOTICE 'User evangelion already exists';
        ELSE
            CREATE USER evangelion WITH encrypted PASSWORD '12345';
            GRANT all privileges ON DATABASE testing_system_db TO evangelion;
            ALTER USER evangelion WITH SUPERUSER;
        END IF;
    END
$$;


\c testing_system_db;

DO $$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'roles') THEN
            create type roles AS ENUM ('admin', 'moderator', 'teacher', 'student');
        ELSE
            RAISE NOTICE 'Type roles already exists';
        END IF;

        IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'block_types') THEN
            create type block_types AS ENUM ('input', 'checkbox', 'radiobutton', 'textfield');
        ELSE
            RAISE NOTICE 'Type block_types already exists';
        END IF;
    END
$$;

CREATE TABLE IF NOT EXISTS "users" (
  "id" SERIAL PRIMARY KEY,
  "role_type" roles NOT NULL,
  "email" varchar NOT NULL,
  "hash" varchar NOT NULL,
  "salt" varchar NOT NULL,
  "first_name" varchar NOT NULL,
  "last_name" varchar NOT NULL,
  "middle_name" varchar,
  "birth_date" date NOT NULL,
  "work_title" varchar NOT NULL,
  "created_at" timestamp NOT NULL
);

CREATE TABLE IF NOT EXISTS "groups" (
  "id" SERIAL PRIMARY KEY,
  "name" varchar NOT NULL,
  "is_open" boolean NOT NULL
);

CREATE TABLE IF NOT EXISTS "users_groups" (
  "id" SERIAL PRIMARY KEY,
  "user_id" int NOT NULL,
  "group_id" int NOT NULL
);

CREATE TABLE IF NOT EXISTS "subjects" (
  "id" SERIAL PRIMARY KEY,
  "name" varchar NOT NULL
);

CREATE TABLE IF NOT EXISTS "tests" (
  "id" SERIAL PRIMARY KEY,
  "title" varchar NOT NULL,
  "subject_id" int,
  "is_random" boolean NOT NULL,
  "creator_id" int NOT NULL

);

CREATE TABLE IF NOT EXISTS "questions" (
  "id" SERIAL PRIMARY KEY,
  "question_type" block_types NOT NULL,
  "test_id" int NOT NULL,
  "phrase" varchar NOT NULL,
  "right_answer" varchar,
  "structure" varchar
);

CREATE TABLE IF NOT EXISTS "test_attempts" (
  "user_id" int NOT NULL,
  "id" SERIAL PRIMARY KEY,
  "test_id" int NOT NULL,
  "score" int DEFAULT 0 ,
  "passing_date" date
);

CREATE TABLE IF NOT EXISTS "test_attempt_answers" (
  "id" SERIAL PRIMARY KEY,
  "test_attempt_id" int NOT NULL,
  "question_id" int NOT NULL,
  "answer" varchar NOT NULL,
  "score" int
);

CREATE TABLE IF NOT EXISTS "groups_tests" (
  "id" SERIAL PRIMARY KEY,
  "group_id" int NOT NULL,
  "test_id" int NOT NULL,
  "is_neccessary" boolean NOT NULL,
  "max_attemps" int DEFAULT 3,
  "time_limit" int,
  "deadline" date
);

ALTER TABLE "users_groups" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "users_groups" ADD FOREIGN KEY ("group_id") REFERENCES "groups" ("id");

ALTER TABLE "tests" ADD FOREIGN KEY ("subject_id") REFERENCES "subjects" ("id");

ALTER TABLE "questions" ADD FOREIGN KEY ("test_id") REFERENCES "tests" ("id");

ALTER TABLE "test_attempts" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "test_attempts" ADD FOREIGN KEY ("test_id") REFERENCES "tests" ("id");

ALTER TABLE "test_attempt_answers" ADD FOREIGN KEY ("test_attempt_id") REFERENCES "test_attempts" ("id");

ALTER TABLE "test_attempt_answers" ADD FOREIGN KEY ("question_id") REFERENCES "questions" ("id");

ALTER TABLE "groups_tests" ADD FOREIGN KEY ("group_id") REFERENCES "groups" ("id");

ALTER TABLE "groups_tests" ADD FOREIGN KEY ("test_id") REFERENCES "tests" ("id");

ALTER TABLE "tests" ADD FOREIGN KEY ("creator_id") REFERENCES "users" ("id");
