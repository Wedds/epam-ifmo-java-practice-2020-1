INSERT INTO groups (id, name, created_at) values (1, 'K3120','2020-12-19 13:10:50');
INSERT INTO groups (id, name, created_at) values (2, 'K3240','2019-11-07 21:38:40');
INSERT INTO groups (id, name, created_at) values (3, 'K3310','2020-01-01 20:36:40');
INSERT INTO groups (id, name, created_at) values (4, 'M4120','2019-11-02 10:38:40');
INSERT INTO groups (id, name, created_at) values (5, 'C2148','2020-01-04 20:28:40');
INSERT INTO groups (id, name, created_at) values (6, 'I1238','2020-01-02 20:15:40');

ALTER TABLE groups ALTER COLUMN id RESTART WITH 7;

INSERT INTO users (id, role_type, email, hash, salt, first_name, last_name, middle_name, birth_date, work_title, created_at) values (1, 'admin', 'admin@pochta.ru', '45ec4bb100f5da97a4b939afbc1b6af2', '40a8903753c7d0780fa70ef3febd5f11', 'Donald', 'Tramp', 'Genadievich', '2000-01-20', 'programmer', '2020-02-11 06:38:40');
INSERT INTO users (id, role_type, email, hash, salt, first_name, last_name, birth_date, work_title, created_at) values (2, 'teacher', 'teacher@pochta.ru', 'd46ffd73c3b76220f7ce65bc4ff26e30', 'e1cab46cgsdfshsfh9904a8a3b6743c', 'Абрикосик', 'Спелый', '1998-02-20', 'ЛУЧШИЙ УЧИТЕЛЬ ЕЕЕ', '2020-02-07 15:38:40');
INSERT INTO users (id, role_type, email, hash, salt, first_name, last_name, middle_name, birth_date, work_title, created_at, group_id) values (3, 'student', 'student2@pochta.ru', 'd3267ce7e3247320c6194e7a5a16e18d', '2h32gho4u89fdsaghf0d6sad6t565ga8dyg', 'Светлана', 'Клавиатуровна', 'Константиновна','2003-02-20', 'Студент', '2020-01-23 20:38:40', 3);
INSERT INTO users (id, role_type, email, hash, salt, first_name, last_name, birth_date, work_title, created_at) values (4, 'moderator', 'moder@pochta.ru', '4c6c76265940889d0392a6e48e57b6eb', '76dsgf7d676gdfs89gsd6s78h6f987h6df78gh', 'Лучший', 'Модер', '1920-02-20', 'Модератор', '2020-02-03 19:38:40');
INSERT INTO users (id, role_type, email, hash, salt, first_name, last_name, middle_name, birth_date, work_title, created_at, group_id) values (5, 'student', 'student2@pochta.ru', 'd3267ce7e3247320c6194e7a5a16e18d', '2h32gho4u89fdsaghf0d6sad6t565ga8dyg', 'Светлана', 'Клавиатуровна', 'Константиновна','2003-02-20', 'Студент', '2020-01-23 20:38:40', 1);
INSERT INTO users (id, role_type, email, hash, salt, first_name, last_name, birth_date, work_title, created_at) values (6, 'teacher', 'teacher2@pochta.ru', '4c6c76265940889d0392a6e48e57b6eb', 'jhgjg43jg23k1g423h5g1iu645264', 'Персик', 'Красный', '1998-02-20', 'Такой себе учитель', '2020-02-07 20:35:40');

ALTER TABLE users ALTER COLUMN id RESTART WITH 7;

INSERT INTO subjects (id, name) values (1, 'Math');
INSERT INTO subjects (id, name) values (2, 'Programming');
INSERT INTO subjects (id, name) values (3, 'English language');
INSERT INTO subjects (id, name) values (4, 'Physics');

ALTER TABLE subjects ALTER COLUMN id RESTART WITH 5;

INSERT INTO tests (id, title, subject_id, is_random, creator_id, description, max_points, created_at) values (1, 'Wonderfull math', 1, TRUE, 2, 'ну, допустим, что это тест', 10, '2019-01-01 14:38:40');
INSERT INTO tests (id, title, subject_id, is_random, creator_id, description, max_points, created_at) values (2, 'Progaprogapro', 2, FALSE, 6, 'все мы тут шли на прогеров', 15, '2019-01-01 14:38:40');
INSERT INTO tests (id, title, subject_id, is_random, creator_id, description, max_points, created_at) values (3, 'Physics for chainycs', 4, FALSE, 2, 'а попали вот сюда', 10, '2019-01-10 14:38:40');
INSERT INTO tests (id, title, subject_id, is_random, creator_id, description, max_points, created_at) values (4, 'And math again', 1, TRUE, 6, 'и нет теперь нам спасенья', 10, '2019-01-01 14:38:40');
INSERT INTO tests (id, title, subject_id, is_random, creator_id, description, max_points, created_at) values (5,'SLOJNAAA', 3, TRUE, 2, 'памагииииити', 5, '2019-01-01 14:38:40');

ALTER TABLE tests ALTER COLUMN id RESTART WITH 6;

INSERT INTO groups_tests (group_id, test_id, is_necessary, max_attempts, time_limit, deadline) values (1, 3, FALSE, 10, 30, '2030-01-08 20:38:40');
INSERT INTO groups_tests (group_id, test_id, is_necessary, max_attempts, time_limit, deadline) values (2, 1, TRUE, 1, 30, '2020-02-15 20:38:40');
INSERT INTO groups_tests (group_id, test_id, is_necessary) values (3, 4, FALSE);
INSERT INTO groups_tests (group_id, test_id, is_necessary, time_limit, deadline) values (4, 1, TRUE, 20, '2020-02-11 20:38:40');
INSERT INTO groups_tests (group_id, test_id, is_necessary, deadline) values (1, 2, FALSE, '2020-06-11 20:38:40');

INSERT INTO attempts (id, test_id, user_id, score, passing_date) VALUES (1, 3, 1, 10, '2020-02-01 14:38:40');
INSERT INTO attempts (id, test_id, user_id, score, passing_date) VALUES (2, 5, 1, 8, '2020-02-12 20:38:40');
INSERT INTO attempts (id, test_id, user_id, score, passing_date) VALUES (3, 3, 2, 12, '2020-02-04 20:38:40');
INSERT INTO attempts (id, test_id, user_id, score, passing_date) VALUES (4, 5, 4, 6, '2020-02-02 10:38:40');
INSERT INTO attempts (id, test_id, user_id, score, passing_date) VALUES (5, 5, 5, 2, '2020-02-03 21:38:40');
INSERT INTO attempts (id, test_id, user_id, score, passing_date) VALUES (6, 3, 3, 5, '2020-02-06 20:58:40');

ALTER TABLE attempts ALTER COLUMN id RESTART WITH 7;

INSERT INTO questions (id, question_type, test_id, question_text) values (1, 'radiobutton', 1, '2 + 2 = ?');
INSERT INTO questions (id, question_type, test_id, question_text) values (2, 'checkbox', 1, 'x^y = ?');
INSERT INTO questions (id, question_type, test_id, question_text) values (3, 'radiobutton', 1, 'Best OS?');
INSERT INTO questions (id, question_type, test_id, question_text) values (4, 'checkbox', 1, 'Translate "cat":');
INSERT INTO questions (id, question_type, test_id, question_text) values (5, 'radiobutton', 1, 'Wheel of fortune (Randomize)');
INSERT INTO questions (id, question_type, test_id, question_text) values (6, 'checkbox', 1, 'The best PL:');
INSERT INTO questions (id, question_type, test_id, question_text) values (7, 'radiobutton', 1, '-1/0');

ALTER TABLE  questions ALTER COLUMN id RESTART WITH 8;
/* Question 1 */
INSERT INTO answers (id, answer_text, question_id, is_correct, points) VALUES (1, '4', 1, True, 2);
INSERT INTO answers (id, answer_text, question_id, is_correct, points) VALUES (2, '5', 1, False, 0);
INSERT INTO answers (id, answer_text, question_id, is_correct, points) VALUES (3, '6', 1, False, 0);
INSERT INTO answers (id, answer_text, question_id, is_correct, points) VALUES (4, '7', 1, False, 0);
/* Question 2 */
INSERT INTO answers (id, answer_text, question_id, is_correct, points) VALUES (5, '= 8 where x = 2, y = 3', 2, True, 2);
INSERT INTO answers (id, answer_text, question_id, is_correct, points) VALUES (6, '= 25 where x = 5, y = 2', 2, True, 2);
INSERT INTO answers (id, answer_text, question_id, is_correct, points) VALUES (7, '= 25 where x = 5, y = 7', 2, False, 0);
INSERT INTO answers (id, answer_text, question_id, is_correct, points) VALUES (8, '= 10 where x = 5, y = 2', 2, False, 0);
/* Question 3 */
INSERT INTO answers (id, answer_text, question_id, is_correct, points) VALUES (9, 'Linux', 3, True, 2);
INSERT INTO answers (id, answer_text, question_id, is_correct, points) VALUES (10, 'Windows', 3, False, 0);
INSERT INTO answers (id, answer_text, question_id, is_correct, points) VALUES (11, 'FreeDOS', 3, False, 0);
INSERT INTO answers (id, answer_text, question_id, is_correct, points) VALUES (12, 'MS-DOS', 3, False, 0);
/* Question 4 */
INSERT INTO answers (id, answer_text, question_id, is_correct, points) VALUES (13, 'Котики', 4, True, 2);
INSERT INTO answers (id, answer_text, question_id, is_correct, points) VALUES (14, 'Котятки', 4, True, 2);
INSERT INTO answers (id, answer_text, question_id, is_correct, points) VALUES (15, 'Котятушки', 4, True, 2);
INSERT INTO answers (id, answer_text, question_id, is_correct, points) VALUES (16, 'Котофеи', 4, True, 2);
/* Question 5 */
INSERT INTO answers (id, answer_text, question_id, is_correct, points) VALUES (17, '1', 5, True, 2);
INSERT INTO answers (id, answer_text, question_id, is_correct, points) VALUES (18, '2', 5, True, 2);
INSERT INTO answers (id, answer_text, question_id, is_correct, points) VALUES (19, '3', 5, True, 2);
INSERT INTO answers (id, answer_text, question_id, is_correct, points) VALUES (20, '4', 5, True, 2);
/* Question 6 */
INSERT INTO answers (id, answer_text, question_id, is_correct, points) VALUES (21, 'Python', 6, False, 0);
INSERT INTO answers (id, answer_text, question_id, is_correct, points) VALUES (22, 'Java', 6, False, 0);
INSERT INTO answers (id, answer_text, question_id, is_correct, points) VALUES (23, 'JavaScript', 6, False, 0);
INSERT INTO answers (id, answer_text, question_id, is_correct, points) VALUES (24, 'C', 6, False, 0);
INSERT INTO answers (id, answer_text, question_id, is_correct, points) VALUES (25, 'Depends on', 6, True, 5);
/* Question 7 */
INSERT INTO answers (id, answer_text, question_id, is_correct, points) VALUES (26, 'Infinity', 7, False, 0);
INSERT INTO answers (id, answer_text, question_id, is_correct, points) VALUES (27, '-Infinity', 7, True, 5);
INSERT INTO answers (id, answer_text, question_id, is_correct, points) VALUES (28, '0', 7, False, 0);
INSERT INTO answers (id, answer_text, question_id, is_correct, points) VALUES (29, '-0', 7, False, 0);

ALTER TABLE answers ALTER COLUMN id RESTART WITH 30;

