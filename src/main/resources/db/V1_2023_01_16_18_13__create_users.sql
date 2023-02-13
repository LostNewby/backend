create table if not exists users
(
    id                      serial not null,
    email                   varchar(220) not null,
    password                varchar(2000) not null,
    active                  boolean not null default false,
    role                    varchar(225),
    first_name              varchar(225),
    last_name               varchar(225),
    phone_number            varchar(225) not null,
    token                   varchar(225),
    primary key (id)
);

create unique index users_email_uindex
    on users (email);

create unique index users_phone_number_uindex
    on users (phone_number);

comment on table users is 'Пользователи';
comment on column users.email is 'Email ';
comment on column users.password is 'Пароль ';
comment on column users.last_name is 'Фамилия';
comment on column users.first_name is 'Имя';
comment on column users.phone_number is 'Номер телефона';
comment on column users.role is 'Роль ';


