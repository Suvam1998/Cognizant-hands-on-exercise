-- Run in MySQL before starting the app with ddl-auto=validate.
CREATE SCHEMA IF NOT EXISTS ormlearn;
USE ormlearn;

CREATE TABLE IF NOT EXISTS country (
    co_code VARCHAR(2)  PRIMARY KEY,
    co_name VARCHAR(60)
);

-- Then load the country rows:
--   mysql -u root -p ormlearn < 02_country_data.sql
