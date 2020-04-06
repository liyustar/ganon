create table biz_user
(
  id int not null auto_increment,
  name varchar(32) not null,
  password varchar(32),
  created datetime not null default CURRENT_TIMESTAMP,
  primary key (id),
  unique key name_uindex (name)
);
-- ENGINE=InnoDB DEFAULT CHARSET=utf8mb4

create table biz_article
(
    id int not null auto_increment,
    title varchar(200) not null,
    author_id int not null,
    content text,
    created datetime not null default CURRENT_TIMESTAMP,
    primary key (id),
    key author_id_index (author_id)
);
-- ENGINE=InnoDB DEFAULT CHARSET=utf8mb4

create table biz_article_comment
(
    id int not null auto_increment,
    article_id int not null,
    creator_id int not null,
    content text,
    created datetime not null default CURRENT_TIMESTAMP,
    primary key (id),
    key article_id_index (article_id),
    key creator_id_index (creator_id)
);
-- ENGINE=InnoDB DEFAULT CHARSET=utf8mb4

create table biz_account
(
    id int not null auto_increment,
    acc_code varchar(32) not null,
    user_id int not null,
    amt double not null default 0,
    remark varchar(200),
    created datetime not null default CURRENT_TIMESTAMP,
    primary key (id),
    unique key acc_code_uindex (acc_code),
    key user_id_index (user_id)
);
-- ENGINE=InnoDB DEFAULT CHARSET=utf8mb4

create table biz_cash_log
(
    id int not null auto_increment,
    acc_from varchar(32) not null,
    acc_to varchar(32) not null,
    article_id int not null,
    amt double not null,
    remark varchar(200),
    created datetime not null default CURRENT_TIMESTAMP,
    primary key (id)
);
-- ENGINE=InnoDB DEFAULT CHARSET=utf8mb4