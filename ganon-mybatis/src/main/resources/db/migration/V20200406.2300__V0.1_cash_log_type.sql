alter table biz_cash_log add column biz_type tinyint unsigned not null default 0;
alter table biz_cash_log change column article_id biz_id int;
