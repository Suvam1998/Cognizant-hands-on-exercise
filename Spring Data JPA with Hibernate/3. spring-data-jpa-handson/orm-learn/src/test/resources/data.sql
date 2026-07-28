-- ==== Payroll (Hands-on 2, 4, 5) ====
INSERT INTO department (dp_id, dp_name) VALUES (1, 'IT');
INSERT INTO department (dp_id, dp_name) VALUES (2, 'HR');

INSERT INTO skill (sk_id, sk_name) VALUES (1, 'Java');
INSERT INTO skill (sk_id, sk_name) VALUES (2, 'Python');
INSERT INTO skill (sk_id, sk_name) VALUES (3, 'SQL');

INSERT INTO employee (em_id, em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id)
VALUES (1, 'John Doe', 60000, TRUE, '1990-05-15', 1);
INSERT INTO employee (em_id, em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id)
VALUES (2, 'Jane Smith', 75000, TRUE, '1988-03-20', 1);
INSERT INTO employee (em_id, em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id)
VALUES (3, 'Bob Brown', 50000, FALSE, '1995-07-10', 2);

INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (1, 1);
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (1, 3);
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (2, 2);

-- ==== Quiz (Hands-on 3) ====
INSERT INTO app_user (user_id, user_name) VALUES (1, 'alice');

INSERT INTO question (q_id, q_text) VALUES (1, 'What is the extension of the hyper text markup language file?');
INSERT INTO question (q_id, q_text) VALUES (2, 'What is the maximum level of heading tag can be used in a HTML page?');
INSERT INTO question (q_id, q_text) VALUES (3, 'The HTML document itself begins with <html> and ends </html>. State True of False');
INSERT INTO question (q_id, q_text) VALUES (4, 'Choose the right option to store text value in a variable');

-- options (o_id, o_q_id, o_text, o_score, o_is_correct)
INSERT INTO options (o_id, o_q_id, o_text, o_score, o_is_correct) VALUES (1, 1, '.xhtm', 0.0, FALSE);
INSERT INTO options (o_id, o_q_id, o_text, o_score, o_is_correct) VALUES (2, 1, '.ht', 0.0, FALSE);
INSERT INTO options (o_id, o_q_id, o_text, o_score, o_is_correct) VALUES (3, 1, '.html', 1.0, TRUE);
INSERT INTO options (o_id, o_q_id, o_text, o_score, o_is_correct) VALUES (4, 1, '.htmx', 0.0, FALSE);
INSERT INTO options (o_id, o_q_id, o_text, o_score, o_is_correct) VALUES (5, 2, '5', 0.0, FALSE);
INSERT INTO options (o_id, o_q_id, o_text, o_score, o_is_correct) VALUES (6, 2, '3', 0.0, FALSE);
INSERT INTO options (o_id, o_q_id, o_text, o_score, o_is_correct) VALUES (7, 2, '4', 0.0, FALSE);
INSERT INTO options (o_id, o_q_id, o_text, o_score, o_is_correct) VALUES (8, 2, '6', 1.0, TRUE);
INSERT INTO options (o_id, o_q_id, o_text, o_score, o_is_correct) VALUES (9, 3, 'false', 0.0, FALSE);
INSERT INTO options (o_id, o_q_id, o_text, o_score, o_is_correct) VALUES (10, 3, 'true', 1.0, TRUE);
INSERT INTO options (o_id, o_q_id, o_text, o_score, o_is_correct) VALUES (11, 4, '''John''', 0.5, TRUE);
INSERT INTO options (o_id, o_q_id, o_text, o_score, o_is_correct) VALUES (12, 4, 'John', 0.0, FALSE);
INSERT INTO options (o_id, o_q_id, o_text, o_score, o_is_correct) VALUES (13, 4, '"John"', 0.5, TRUE);
INSERT INTO options (o_id, o_q_id, o_text, o_score, o_is_correct) VALUES (14, 4, '/John/', 0.0, FALSE);

-- attempt by user 1
INSERT INTO attempt (at_id, at_user_id, at_date) VALUES (1, 1, '2019-10-01');

INSERT INTO attempt_question (aq_id, aq_at_id, aq_q_id) VALUES (1, 1, 1);
INSERT INTO attempt_question (aq_id, aq_at_id, aq_q_id) VALUES (2, 1, 2);
INSERT INTO attempt_question (aq_id, aq_at_id, aq_q_id) VALUES (3, 1, 3);
INSERT INTO attempt_question (aq_id, aq_at_id, aq_q_id) VALUES (4, 1, 4);

-- attempt_option: one row per option, ao_selected = user's choice
INSERT INTO attempt_option (ao_id, ao_aq_id, ao_o_id, ao_selected) VALUES (1, 1, 1, FALSE);
INSERT INTO attempt_option (ao_id, ao_aq_id, ao_o_id, ao_selected) VALUES (2, 1, 2, FALSE);
INSERT INTO attempt_option (ao_id, ao_aq_id, ao_o_id, ao_selected) VALUES (3, 1, 3, TRUE);
INSERT INTO attempt_option (ao_id, ao_aq_id, ao_o_id, ao_selected) VALUES (4, 1, 4, FALSE);
INSERT INTO attempt_option (ao_id, ao_aq_id, ao_o_id, ao_selected) VALUES (5, 2, 5, FALSE);
INSERT INTO attempt_option (ao_id, ao_aq_id, ao_o_id, ao_selected) VALUES (6, 2, 6, TRUE);
INSERT INTO attempt_option (ao_id, ao_aq_id, ao_o_id, ao_selected) VALUES (7, 2, 7, FALSE);
INSERT INTO attempt_option (ao_id, ao_aq_id, ao_o_id, ao_selected) VALUES (8, 2, 8, FALSE);
INSERT INTO attempt_option (ao_id, ao_aq_id, ao_o_id, ao_selected) VALUES (9, 3, 9, FALSE);
INSERT INTO attempt_option (ao_id, ao_aq_id, ao_o_id, ao_selected) VALUES (10, 3, 10, TRUE);
INSERT INTO attempt_option (ao_id, ao_aq_id, ao_o_id, ao_selected) VALUES (11, 4, 11, TRUE);
INSERT INTO attempt_option (ao_id, ao_aq_id, ao_o_id, ao_selected) VALUES (12, 4, 12, FALSE);
INSERT INTO attempt_option (ao_id, ao_aq_id, ao_o_id, ao_selected) VALUES (13, 4, 13, FALSE);
INSERT INTO attempt_option (ao_id, ao_aq_id, ao_o_id, ao_selected) VALUES (14, 4, 14, FALSE);

-- ==== Products (Hands-on 6 - Criteria Query) ====
INSERT INTO product (p_id, p_name, p_category, p_ram, p_cpu_speed, p_rating, p_os) VALUES (1, 'Dell XPS 13', 'laptop', 16, 3.5, 4.5, 'Windows');
INSERT INTO product (p_id, p_name, p_category, p_ram, p_cpu_speed, p_rating, p_os) VALUES (2, 'MacBook Pro', 'laptop', 16, 3.2, 4.8, 'macOS');
INSERT INTO product (p_id, p_name, p_category, p_ram, p_cpu_speed, p_rating, p_os) VALUES (3, 'HP Pavilion', 'laptop', 8, 2.8, 3.9, 'Windows');
INSERT INTO product (p_id, p_name, p_category, p_ram, p_cpu_speed, p_rating, p_os) VALUES (4, 'Lenovo ThinkPad', 'laptop', 32, 3.9, 4.6, 'Windows');
INSERT INTO product (p_id, p_name, p_category, p_ram, p_cpu_speed, p_rating, p_os) VALUES (5, 'Asus Chromebook', 'laptop', 4, 2.1, 3.5, 'ChromeOS');
