CREATE DATABASE "Account"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Russian_Russia.1251'
    LC_CTYPE = 'Russian_Russia.1251'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

COMMENT ON DATABASE "Account"
    IS 'База данных для микросервиса Account';
	
CREATE TABLE bank_accounts (
	id_bank_accounts serial PRIMARY KEY,
	num_bank_accounts varchar(20),
	amount numeric(17, 2)
);

COMMENT ON TABLE bank_accounts IS 'Таблица для хранения информации о банковских счетах';
COMMENT ON COLUMN bank_accounts.id_bank_accounts 
	IS 'Уникальный идентификатор банковского счёта';
COMMENT ON COLUMN bank_accounts.num_bank_accounts 
	IS 'Номер банковского счёта';
COMMENT ON COLUMN bank_accounts.amount 
	IS 'Сумма банковского счёта';