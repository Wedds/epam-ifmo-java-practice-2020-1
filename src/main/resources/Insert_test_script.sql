\c testing_system_db;

INSERT INTO users (role_type, email, hash, salt, first_name, last_name, middle_name, birth_date, work_title, created_at) values ('admin', 'admin@pochta.ru', '45ec4bb100f5da97a4b939afbc1b6af2', '40a8903753c7d0780fa70ef3febd5f11', 'Donald', 'Tramp', 'Genadievich', '2000-01-20', 'programmer', '2020-02-11');
INSERT INTO users (role_type, email, hash, salt, first_name, last_name, birth_date, work_title, created_at) values ('teacher', 'teacher@pochta.ru', 'd46ffd73c3b76220f7ce65bc4ff26e30', 'e1cab46cgsdfshsfh9904a8a3b6743c', 'Абрикосик', 'Спелый', '1998-02-20', 'ЛУЧШИЙ УЧИТЕЛЬ ЕЕЕ', '2020-02-07');
INSERT INTO users (role_type, email, hash, salt, first_name, last_name, middle_name, birth_date, work_title, created_at) values ('student', 'student2@pochta.ru', 'd3267ce7e3247320c6194e7a5a16e18d', '2h32gho4u89fdsaghf0d6sad6t565ga8dyg', 'Светлана', 'Клавиатуровна', 'Константиновна','2003-02-20', 'Студент', '2020-01-23');
INSERT INTO users (role_type, email, hash, salt, first_name, last_name, birth_date, work_title, created_at) values ('moderator', 'moder@pochta.ru', '4c6c76265940889d0392a6e48e57b6eb', '76dsgf7d676gdfs89gsd6s78h6f987h6df78gh', 'Лучший', 'Модер', '1920-02-20', 'Модератор', '2020-02-03');
INSERT INTO users (role_type, email, hash, salt, first_name, last_name, middle_name, birth_date, work_title, created_at) values ('student', 'student2@pochta.ru', 'd3267ce7e3247320c6194e7a5a16e18d', '2h32gho4u89fdsaghf0d6sad6t565ga8dyg', 'Светлана', 'Клавиатуровна', 'Константиновна','2003-02-20', 'Студент', '2020-01-23');
INSERT INTO users (role_type, email, hash, salt, first_name, last_name, birth_date, work_title, created_at) values ('teacher', 'teacher2@pochta.ru', '4c6c76265940889d0392a6e48e57b6eb', 'jhgjg43jg23k1g423h5g1iu645264', 'Персик', 'Красный', '1998-02-20', 'Такой себе учитель', '2020-02-07');


INSERT INTO groups (name, is_open) values ('K3120', FALSE);
INSERT INTO groups (name, is_open) values ('K3240', FALSE);
INSERT INTO groups (name, is_open) values ('K3310', FALSE);
INSERT INTO groups (name, is_open) values ('M4120', FALSE);
INSERT INTO groups (name, is_open) values ('OpenMath', TRUE);
INSERT INTO groups (name, is_open) values ('OpenProgramming', TRUE);

INSERT INTO users_groups (user_id, group_id) values (4,2);
INSERT INTO users_groups (user_id, group_id) values (4,5);
INSERT INTO users_groups (user_id, group_id) values (5,1);
INSERT INTO users_groups (user_id, group_id) values (5,5);
INSERT INTO users_groups (user_id, group_id) values (5,6);

INSERT INTO subjects (name) values ('Math');
INSERT INTO subjects (name) values ('Programming');
INSERT INTO subjects (name) values ('English language');
INSERT INTO subjects (name) values ('Physics');

INSERT INTO tests (title, subject_id, is_random, creator_id) values ('Wonderfull math', 1, TRUE, 2);
INSERT INTO tests (title, subject_id, is_random, creator_id) values ('Progaprogapro', 2, FALSE, 6);
INSERT INTO tests (title, subject_id, is_random, creator_id) values ('Physics for chainycs', 4, FALSE, 2);
INSERT INTO tests (title, subject_id, is_random, creator_id) values ('And math again', 1, TRUE, 6);
INSERT INTO tests (title, subject_id, is_random, creator_id) values ('SLOJNAAA', 3, TRUE, 2);

INSERT INTO questions (question_type, test_id, phrase, right_answer, structure) values ('radiobutton', 1, '2 + 2 is?', '4', '10, 6, 15, 4');
INSERT INTO questions (question_type, test_id, phrase, right_answer, structure) values ('checkbox', 1, 'x^2 = 4, x is?', '-2, 2', '8, -4, 2, 4, -2');
INSERT INTO questions (question_type, test_id, phrase, right_answer) values ('input', 1, '2^64 is', 'hren znaet');
INSERT INTO questions (question_type, test_id, phrase) values ('textfield', 5, 'Write me an essay');
INSERT INTO questions (question_type, test_id, phrase, right_answer, structure) values ('checkbox', 5, 'Translate of cat', 'кот', 'сервер, мышка, кот, паспорт');
INSERT INTO questions (question_type, test_id, phrase, right_answer) values ('input', 2, 'The best OS', 'Ubuntu');
INSERT INTO questions (question_type, test_id, phrase, right_answer) values ('input', 3, 'You anyway will not understand it, so just try to guess', 'resistance');
INSERT INTO questions (question_type, test_id, phrase, right_answer) values ('input', 3, '1/0', 'AAAAAAAAAA');

INSERT INTO groups_tests (group_id, test_id, is_neccessary, max_attemps, time_limit, deadline) values (1, 3, FALSE, 10, 30, '2030-01-08');
INSERT INTO groups_tests (group_id, test_id, is_neccessary, max_attemps, time_limit, deadline) values (2, 1, TRUE, 1, 30, '2020-02-15');
INSERT INTO groups_tests (group_id, test_id, is_neccessary) values (3, 4, FALSE);
INSERT INTO groups_tests (group_id, test_id, is_neccessary, time_limit, deadline) values (4, 1, TRUE, 20, '2020-02-11');
INSERT INTO groups_tests (group_id, test_id, is_neccessary, deadline) values (1, 2, FALSE, '2020-06-11');

INSERT INTO test_attempts (user_id, test_id, score, passing_date) values (4, 2, 458, '2020-02-13');
INSERT INTO test_attempts (user_id, test_id, score, passing_date) values (4, 1, 879, '2020-02-12');
INSERT INTO test_attempts (user_id, test_id, score, passing_date) values (5, 2, 256, '2020-02-10');
INSERT INTO test_attempts (user_id, test_id, score, passing_date) values (5, 3, 643, '2020-01-30');

INSERT INTO test_attempt_answers (test_attempt_id, question_id, answer, score) values (1, 6, 'Windows', 0);
INSERT INTO test_attempt_answers (test_attempt_id, question_id, answer, score) values (2, 1, '4', 100);
INSERT INTO test_attempt_answers (test_attempt_id, question_id, answer, score) values (2, 2, '-4, 2', 50);
INSERT INTO test_attempt_answers (test_attempt_id, question_id, answer, score) values (2, 3, 'mnoga', 0);
INSERT INTO test_attempt_answers (test_attempt_id, question_id, answer) values (2, 4, 'fdshdfghghdj tgdgjfgfgjfgjghjfghjfghjfghjdgsyrtytuyijmvkndgy');
INSERT INTO test_attempt_answers (test_attempt_id, question_id, answer, score) values (3, 6, 'Ubuntu', 100);
INSERT INTO test_attempt_answers (test_attempt_id, question_id, answer, score) values (4, 7, 'не знаю(((', 0);
INSERT INTO test_attempt_answers (test_attempt_id, question_id, answer, score) values (4, 8, 'AAAAAAAAAA', 100);
