create table func_tiny_url
(
  id int not null,
  path varchar(16) not null,
  src varchar(500) not null,
  created datetime not null,
  primary key (id)
);
-- ENGINE=InnoDB DEFAULT CHARSET=utf8mb4