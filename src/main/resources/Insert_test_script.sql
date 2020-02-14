\c testing_system_db;

INSERT INTO groups (name, created_at) values ('K3120','2020-01-03');
INSERT INTO groups (name, created_at) values ('K3240','2019-11-07');
INSERT INTO groups (name, created_at) values ('K3310','2020-01-01');
INSERT INTO groups (name, created_at) values ('M4120','2019-11-02');
INSERT INTO groups (name, created_at) values ('C2148','2020-01-04');
INSERT INTO groups (name, created_at) values ('I1238','2020-01-02');

INSERT INTO users (role_type, email, hash, salt, first_name, last_name, middle_name, birth_date, work_title, created_at) values ('admin', 'admin@pochta.ru', '45ec4bb100f5da97a4b939afbc1b6af2', '40a8903753c7d0780fa70ef3febd5f11', 'Donald', 'Tramp', 'Genadievich', '2000-01-20', 'programmer', '2020-02-11');
INSERT INTO users (role_type, email, hash, salt, first_name, last_name, birth_date, work_title, created_at) values ('teacher', 'teacher@pochta.ru', 'd46ffd73c3b76220f7ce65bc4ff26e30', 'e1cab46cgsdfshsfh9904a8a3b6743c', 'Абрикосик', 'Спелый', '1998-02-20', 'ЛУЧШИЙ УЧИТЕЛЬ ЕЕЕ', '2020-02-07');
INSERT INTO users (role_type, email, hash, salt, first_name, last_name, middle_name, birth_date, work_title, created_at, group_id) values ('student', 'student2@pochta.ru', 'd3267ce7e3247320c6194e7a5a16e18d', '2h32gho4u89fdsaghf0d6sad6t565ga8dyg', 'Светлана', 'Клавиатуровна', 'Константиновна','2003-02-20', 'Студент', '2020-01-23', 3);
INSERT INTO users (role_type, email, hash, salt, first_name, last_name, birth_date, work_title, created_at) values ('moderator', 'moder@pochta.ru', '4c6c76265940889d0392a6e48e57b6eb', '76dsgf7d676gdfs89gsd6s78h6f987h6df78gh', 'Лучший', 'Модер', '1920-02-20', 'Модератор', '2020-02-03');
INSERT INTO users (role_type, email, hash, salt, first_name, last_name, middle_name, birth_date, work_title, created_at, group_id) values ('student', 'student2@pochta.ru', 'd3267ce7e3247320c6194e7a5a16e18d', '2h32gho4u89fdsaghf0d6sad6t565ga8dyg', 'Светлана', 'Клавиатуровна', 'Константиновна','2003-02-20', 'Студент', '2020-01-23', 1);
INSERT INTO users (role_type, email, hash, salt, first_name, last_name, birth_date, work_title, created_at) values ('teacher', 'teacher2@pochta.ru', '4c6c76265940889d0392a6e48e57b6eb', 'jhgjg43jg23k1g423h5g1iu645264', 'Персик', 'Красный', '1998-02-20', 'Такой себе учитель', '2020-02-07');

INSERT INTO subjects (name) values ('Math');
INSERT INTO subjects (name) values ('Programming');
INSERT INTO subjects (name) values ('English language');
INSERT INTO subjects (name) values ('Physics');

INSERT INTO tests (title, subject_id, is_random, creator_id, description, max_points) values ('Wonderfull math', 1, TRUE, 2, 'ну, допустим, что это тест', 10);
INSERT INTO tests (title, subject_id, is_random, creator_id, description, max_points) values ('Progaprogapro', 2, FALSE, 6, 'все мы тут шли на прогеров', 15);
INSERT INTO tests (title, subject_id, is_random, creator_id, description, max_points) values ('Physics for chainycs', 4, FALSE, 2, 'а попали вот сюда', 10);
INSERT INTO tests (title, subject_id, is_random, creator_id, description, max_points) values ('And math again', 1, TRUE, 6, 'и нет теперь нам спасенья', 10);
INSERT INTO tests (title, subject_id, is_random, creator_id, description, max_points) values ('SLOJNAAA', 3, TRUE, 2, 'памагииииити', 5);

INSERT INTO groups_tests (group_id, test_id, is_neccessary, max_attemps, time_limit, deadline) values (1, 3, FALSE, 10, 30, '2030-01-08');
INSERT INTO groups_tests (group_id, test_id, is_neccessary, max_attemps, time_limit, deadline) values (2, 1, TRUE, 1, 30, '2020-02-15');
INSERT INTO groups_tests (group_id, test_id, is_neccessary) values (3, 4, FALSE);
INSERT INTO groups_tests (group_id, test_id, is_neccessary, time_limit, deadline) values (4, 1, TRUE, 20, '2020-02-11');
INSERT INTO groups_tests (group_id, test_id, is_neccessary, deadline) values (1, 2, FALSE, '2020-06-11');

INSERT INTO attempts (test_id, user_id, score, passing_date) VALUES (3, 1, 10, '2020-02-01');
INSERT INTO attempts (test_id, user_id, score, passing_date) VALUES (5, 1, 8, '2020-02-12');
INSERT INTO attempts (test_id, user_id, score, passing_date) VALUES (3, 2, 12, '2020-02-04');
INSERT INTO attempts (test_id, user_id, score, passing_date) VALUES (5, 4, 6, '2020-02-02');
INSERT INTO attempts (test_id, user_id, score, passing_date) VALUES (5, 5, 2, '2020-02-03');
INSERT INTO attempts (test_id, user_id, score, passing_date) VALUES (3, 3, 5, '2020-02-06');


INSERT INTO questions (question_type, test_id, question_text) values ('radiobutton', 1, '2 + 2 is?');
INSERT INTO questions (question_type, test_id, question_text) values ('checkbox', 1, 'x^2 = 4, x is?');
INSERT INTO questions (question_type, test_id, question_text) values ('radiobutton', 1, '2^64 is');
INSERT INTO questions (question_type, test_id, question_text) values ('checkbox', 5, 'На чьей стороне ты?');
INSERT INTO questions (question_type, test_id, question_text) values ('checkbox', 5, 'Translate of cat');
INSERT INTO questions (question_type, test_id, question_text) values ('radiobutton', 2, 'The best OS');
INSERT INTO questions (question_type, test_id, question_text) values ('radiobutton', 3, 'You anyway will not understand it, so just try to guess');
INSERT INTO questions (question_type, test_id, question_text) values ('checkbox', 3, '1/0');

INSERT INTO answers (answer_text, question_id, is_correct, points) VALUES ('4', 1, True, 2);
INSERT INTO answers (answer_text, question_id, is_correct, points) VALUES ('5', 1, True, 2);
INSERT INTO answers (answer_text, question_id, is_correct, points) VALUES ('2', 1, FALSE, -2);
INSERT INTO answers (answer_text, question_id, is_correct, points) VALUES ('многа', 2, True, 3);
INSERT INTO answers (answer_text, question_id, is_correct, points) VALUES ('ваще прям дофига', 3, True, 3);
INSERT INTO answers (answer_text, question_id, is_correct, points) VALUES ('мало чет', 3, FALSE, -5);
INSERT INTO answers (answer_text, question_id, is_correct, points) VALUES ('Apple', 4, True, 5);
INSERT INTO answers (answer_text, question_id, is_correct, points) VALUES ('Android', 4, FALSE, -5);


