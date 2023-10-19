create table users (
  id                    bigserial,
  username              varchar(128) not null unique,
  password              varchar(80) not null,
  phone                 varchar(30) not null unique,
  email                 varchar(128) unique,
  primary key (id)
);

create table roles (
  id                    bigserial,
  name                  varchar(50) not null,
  primary key (id)
);

CREATE TABLE users_roles (
  user_id               bigint not null,
  role_id               int not null,
  primary key (user_id, role_id),
  foreign key (user_id) references users (id),
  foreign key (role_id) references roles (id)
);

insert into roles (name)
values
('ROLE_USER'), ('ROLE_ADMIN');

insert into users (username, password, phone, email)
values
('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', '+79288886969', 'user@gmail.com'),
('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', '+79288900666', 'admin@gmail.com');

insert into users_roles (user_id, role_id)
values
(1, 1),
(2, 2);