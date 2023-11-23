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
  created_at            datetime,
  expires_at            datetime,
  confirmed_at          datetime,
  user_id               bigserial,
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