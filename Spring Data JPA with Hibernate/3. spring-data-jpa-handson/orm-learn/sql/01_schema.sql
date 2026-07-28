-- MySQL DDL for HQL / native / quiz / criteria hands-on. Run in ormlearn schema.
USE ormlearn;

-- Payroll (Hands-on 2, 4, 5)
CREATE TABLE IF NOT EXISTS department (
  dp_id INT NOT NULL AUTO_INCREMENT, dp_name VARCHAR(100), PRIMARY KEY (dp_id));
CREATE TABLE IF NOT EXISTS skill (
  sk_id INT NOT NULL AUTO_INCREMENT, sk_name VARCHAR(100), PRIMARY KEY (sk_id));
CREATE TABLE IF NOT EXISTS employee (
  em_id INT NOT NULL AUTO_INCREMENT, em_name VARCHAR(100), em_salary NUMERIC(10,2),
  em_permanent BOOLEAN, em_date_of_birth DATE, em_dp_id INT,
  PRIMARY KEY (em_id), FOREIGN KEY (em_dp_id) REFERENCES department(dp_id));
CREATE TABLE IF NOT EXISTS employee_skill (
  es_em_id INT NOT NULL, es_sk_id INT NOT NULL, PRIMARY KEY (es_em_id, es_sk_id),
  FOREIGN KEY (es_em_id) REFERENCES employee(em_id),
  FOREIGN KEY (es_sk_id) REFERENCES skill(sk_id));

-- Quiz (Hands-on 3)
CREATE TABLE IF NOT EXISTS app_user (
  user_id INT NOT NULL AUTO_INCREMENT, user_name VARCHAR(50), PRIMARY KEY (user_id));
CREATE TABLE IF NOT EXISTS question (
  q_id INT NOT NULL AUTO_INCREMENT, q_text VARCHAR(255), PRIMARY KEY (q_id));
CREATE TABLE IF NOT EXISTS options (
  o_id INT NOT NULL AUTO_INCREMENT, o_q_id INT, o_text VARCHAR(100),
  o_score NUMERIC(4,2), o_is_correct BOOLEAN,
  PRIMARY KEY (o_id), FOREIGN KEY (o_q_id) REFERENCES question(q_id));
CREATE TABLE IF NOT EXISTS attempt (
  at_id INT NOT NULL AUTO_INCREMENT, at_user_id INT, at_date DATE,
  PRIMARY KEY (at_id), FOREIGN KEY (at_user_id) REFERENCES app_user(user_id));
CREATE TABLE IF NOT EXISTS attempt_question (
  aq_id INT NOT NULL AUTO_INCREMENT, aq_at_id INT, aq_q_id INT,
  PRIMARY KEY (aq_id), FOREIGN KEY (aq_at_id) REFERENCES attempt(at_id),
  FOREIGN KEY (aq_q_id) REFERENCES question(q_id));
CREATE TABLE IF NOT EXISTS attempt_option (
  ao_id INT NOT NULL AUTO_INCREMENT, ao_aq_id INT, ao_o_id INT, ao_selected BOOLEAN,
  PRIMARY KEY (ao_id), FOREIGN KEY (ao_aq_id) REFERENCES attempt_question(aq_id),
  FOREIGN KEY (ao_o_id) REFERENCES options(o_id));

-- Product (Hands-on 6)
CREATE TABLE IF NOT EXISTS product (
  p_id INT NOT NULL AUTO_INCREMENT, p_name VARCHAR(100), p_category VARCHAR(50),
  p_ram INT, p_cpu_speed NUMERIC(4,2), p_rating NUMERIC(3,1), p_os VARCHAR(30),
  PRIMARY KEY (p_id));

-- Sample data is in ../src/test/resources/data.sql (standard SQL; loads in MySQL too).
