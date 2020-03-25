create table sys_user
(
  id int not null,
  name varchar(32) not null,
  password varchar(32),
  password_md5 varchar(64),
  password_sha varchar(64),
  primary key (id)
);
-- ENGINE=InnoDB DEFAULT CHARSET=utf8mb4