create table users (
  id                    bigserial,
  username              varchar(30) not null unique,
  password              varchar(80) not null,
  phone                 varchar(80) not null unique,
  email                 varchar(50) unique,
  is_active             boolean,
  is_verified           boolean,
  primary key (id)
);

create table email_token (
  id                    bigserial,
  token                 varchar(256) not null,
  created_at            date,
  expires_at            date,
  confirmed_at          date,
  user_id               bigserial,
  primary key (id)
);

create table file_data (
  id                bigserial,
  name              varchar,
  type              varchar,
  file_path         varchar,
  primary key (id)
);

CREATE TABLE technic_type (
  id                  bigserial,
  name                varchar not null,
  primary key (id)
);

create table vehicle_manufacturer (
  id                bigserial,
  name              varchar,
  primary key (id)
);

create table vehicle_model (
  id                bigserial,
  name              varchar,
  manufacturer_id   bigserial,
  primary key (id)
);

create table roles (
  id                    bigserial,
  name                  varchar(50) not null,
  primary key (id)
);

CREATE TABLE users_roles (
  user_id               bigserial not null,
  role_id               int not null,
  primary key (user_id, role_id),
  foreign key (user_id) references users (id),
  foreign key (role_id) references roles (id)
);

CREATE TABLE order_technic (
  id                            bigserial,
  technic_type_id               bigserial,
  vehicle_manufacturer_id       bigserial,
  vehicle_model_id              bigserial,
  manufacturing_date            bigserial,
  additional_equipment          varchar,
  unit_amount                   bigserial,
  shift_type_id                 bigserial,
  address                       varchar,
  occupation_start              date,
  occupation_end                date,
  occupation_days               bigserial,
  additional_info               varchar,
  price                         bigserial,
  payment_unit_id               bigserial,
  payment_type_id               bigserial,
  user_id                       bigserial,
  created_at                    date,
  is_active                     boolean,
  is_verified                   boolean,
  primary key (id)
);

CREATE TABLE images_orders (
  order_id          bigserial not null,
  image_id          bigserial not null,
  primary key (order_id, image_id),
  foreign key (order_id) references order_technic (id),
  foreign key (image_id) references file_data (id)
);

insert into technic_type (name)
values ('Tractor');

insert into vehicle_manufacturer (name)
values
('Toyota'), ('Cat'), ('Nissan');

insert into vehicle_model (name, manufacturer_id)
values
('Mark V', 1), ('Mark III', 1), ('Mark VI', 1), ('Mark UwU', 2), ('Mark mrrrrr', 2);

insert into roles (name)
values
('ROLE_USER'), ('ROLE_ADMIN');

insert into users (username, password, phone, email, is_active, is_verified)
values
('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', '+79286669999', 'user@gmail.com', true, true),
('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', '+79288900666', 'admin@gmail.com', false, false);

insert into users_roles (user_id, role_id)
values
(1, 1),
(2, 2);