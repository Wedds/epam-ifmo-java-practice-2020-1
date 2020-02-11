DROP DATABASE IF EXISTS testing_system_db ;
CREATE DATABASE testing_system_db ;
\c testing_system_db


CREATE TYPE "roles" AS ENUM (
  'admin',
  'moderator',
  'teacher',
  'student'
);

CREATE TYPE "test_type" AS ENUM (
  'private',
  'public'
);

CREATE TYPE "block_types" AS ENUM (
  'input',
  'checkbox',
  'radiobutton',
  'textfield'
);

CREATE TABLE "users" (
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

CREATE TABLE "groups" (
  "id" SERIAL PRIMARY KEY,
  "name" varchar NOT NULL
);

CREATE TABLE "users_groups" (
  "id" SERIAL PRIMARY KEY,
  "user_id" int NOT NULL,
  "group_id" int NOT NULL
);

CREATE TABLE "subjects" (
  "id" SERIAL PRIMARY KEY,
  "name" varchar NOT NULL
);

CREATE TABLE "tests" (
  "id" SERIAL PRIMARY KEY,
  "title" varchar NOT NULL,
  "type" test_type NOT NULL,
  "subject_id" int,
  "is_random" boolean NOT NULL,
  "creator_id" int NOT NULL

);

CREATE TABLE "questions" (
  "id" SERIAL PRIMARY KEY,
  "type" block_types NOT NULL,
  "test_id" int NOT NULL,
  "phrase" varchar NOT NULL,
  "right_answer" varchar,
  "structure" varchar
);

CREATE TABLE "test_attempts" (
  "user_id" int NOT NULL,
  "id" SERIAL PRIMARY KEY,
  "test_id" int NOT NULL,
  "score" int DEFAULT 0 ,
  "passing_date" date
);

CREATE TABLE "test_attempt_answers" (
  "id" SERIAL PRIMARY KEY,
  "test_attempt_id" int NOT NULL,
  "question_id" int NOT NULL,
  "answer" varchar NOT NULL,
  "score" int
);

CREATE TABLE "groups_tests" (
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


