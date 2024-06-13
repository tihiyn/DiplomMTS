-- Database: Customer

-- DROP DATABASE IF EXISTS "Customer";

CREATE DATABASE "Customer"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Russian_Russia.1251'
    LC_CTYPE = 'Russian_Russia.1251'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

COMMENT ON DATABASE "Customer"
    IS 'База данных для микросервиса CustomerService';
	
CREATE TABLE customers (
	id_customers serial PRIMARY KEY,
	num_bank_accounts varchar(20),
	phone_number varchar(12),
	"password" varchar(255),
	"role" varchar(255)
);

COMMENT ON TABLE customers IS 'Таблица для хранения информации о клиентах';
COMMENT ON COLUMN customers.id_customers
	IS 'Уникальный идентификатор клиента';
COMMENT ON COLUMN customers.num_bank_accounts 
	IS 'Номер банковского счёта';
COMMENT ON COLUMN customers.phone_number
	IS 'Номер телефона';
COMMENT ON COLUMN customers.password
	IS 'Пароль от аккаунта';
COMMENT ON COLUMN customers.role
	IS 'Роль пользователя в системе';
	
CREATE TABLE codes (
	customers_id int PRIMARY KEY,
	code varchar(4),
	CONSTRAINT fk_customers FOREIGN KEY (customers_id) REFERENCES customers(id_customers)
);