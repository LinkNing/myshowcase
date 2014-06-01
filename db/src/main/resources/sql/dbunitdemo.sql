--create database if not exists dbunitdemo;

--use dbunitdemo;

drop table if exists user;

create table USER (
  id bigint auto_increment primary key,
  organization_id bigint,
  username varchar(100),
  password varchar(100),
  salt varchar(100),
  role_ids varchar(100),
  locked bool default false
);

insert into user(id, username, password, salt) values(-1, 'admin', '123', 'xxxx');