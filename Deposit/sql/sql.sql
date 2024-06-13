create table deposits_types (
                                id_deposits_types serial primary key,
                                deposits_types_name varchar(255),
                                is_top_up boolean,
                                is_withdraw boolean
);

insert into deposits_types (deposits_types_name, is_top_up, is_withdraw) VALUES ('С пополнением и снятием', true, true);
insert into deposits_types (deposits_types_name, is_top_up, is_withdraw) VALUES ('С пополнением, но без снятия', true, false);
insert into deposits_types (deposits_types_name, is_top_up, is_withdraw) VALUES ('Без пополнения и без снятия', false, false);

create table deposit_terms (
                               id_deposit_terms serial primary key,
                               term_length int
);

insert into deposit_terms (term_length) VALUES (3);
insert into deposit_terms (term_length) VALUES (6);
insert into deposit_terms (term_length) VALUES (12);

create table insert_options (
                                id_insert_options serial primary key,
                                name varchar(255)
);

insert into insert_options (name) values ('Ежемесячно');
insert into insert_options (name) values ('В конце проекта');

create table deposits (
                          id_deposits serial primary key,
                          num_bank_account varchar(20),
                          deposits_types_id int,
                          deposit_terms_id int,
                          insert_options_id int,
                          amount numeric(17, 2),
                          start_date date,
                          end_date date,
                          is_capitalized boolean,
                          rate numeric(3, 1)
)