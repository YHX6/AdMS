drop database if exists adms;

create database adms;
use adms;


-- drop table user;

create table if not exists user (
	`id` varchar(20) not null primary key, 
    `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    `username` varchar(100) not null UNIQUE,
    `password` varchar(100) not null,
    `phone_number` varchar(20),
    `roles` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    `failed_attempt` tinyint(4) default 0,
    `account_non_locked` TINYINT NOT NULL DEFAULT 0,
    `lock_time` datetime
);

insert into user values 
('000000','Admin','admin','$2a$10$DMRe92jYFCUTNQgd1f9YKe4vmWcm3tTXRjBFzh.z82kXMYpEYQRZy', '','ROLE_STAFF,ROLE_MANAGER,ROLE_ADMIN', 0, 1,null),
('000001', 'Manager', 'manager','$2a$10$h8c.wqUwJ6k/P8.QW/PLW.5atXYu332j6ZRrrTlkxb9ikbBfMpwyW', '', 'ROLE_STAFF,ROLE_MANAGER', 0, 1, null),
('000002', 'Staff', 'staff','$2a$10$gIt1UXqA4VkJLsa6QI0YjemzsdqAqIjQzfTPa5vzcf.TrMglupG3a', '', 'ROLE_STAFF', 0, 1, null);


create table if not exists spot(
	`id` int not null primary key auto_increment,
    `type` varchar(20)  CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    `station` varchar(20)  CHARACTER SET utf8 COLLATE utf8_unicode_ci,
    `spot_number` varchar(20)  CHARACTER SET utf8 COLLATE utf8_unicode_ci,
    `train` varchar(20)  CHARACTER SET utf8 COLLATE utf8_unicode_ci,
    `status` varchar(20)  CHARACTER SET utf8 COLLATE utf8_unicode_ci,
    `remark` varchar(255)
);


create table ad (
	`id` int not null primary key auto_increment,
    `spot_id` int ,
    `ad_type` varchar(255)  CHARACTER SET utf8 COLLATE utf8_unicode_ci,
    `industry_type` varchar(255)  CHARACTER SET utf8 COLLATE utf8_unicode_ci,
    `content` varchar(255)  CHARACTER SET utf8 COLLATE utf8_unicode_ci,
    `company` varchar(255)  CHARACTER SET utf8 COLLATE utf8_unicode_ci,
    `ddl` date,
    `picture` varchar(255),
    `remark` varchar(255)  CHARACTER SET utf8 COLLATE utf8_unicode_ci,
--     `status` varchar(30)  CHARACTER SET utf8 COLLATE utf8_unicode_ci
	`isdroped` boolean
); 


create table record(
	`id` int primary key auto_increment,
    `user_id` varchar(20) not null,
    `username` varchar(100) not null,
    `ip` varchar(100),
    `uri` varchar(100),
    `operation` varchar(100),
    `time` datetime   
);
