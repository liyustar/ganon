create table biz_account
(
    id int not null auto_increment,
    acc_code varchar(16) not null,
    amt double not null,
    created datetime not null default current_timestamp,
    primary key (id)
);
-- ENGINE=InnoDB DEFAULT CHARSET=utf8mb4