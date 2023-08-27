drop database if exists jgdb;

create database jgdb;
use jgdb;


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

-- CREATE TABLE attempts ( 
--    id int(45) NOT NULL AUTO_INCREMENT, 
--    username varchar(20) NOT NULL, 
--    attempts varchar(45) NOT NULL, 
--    PRIMARY KEY (id) 
-- );


insert into user values 
('000000','XYH','xyh10022','$2a$10$6nJgyaYmL4nDCiTE.rLxEu0T3KEBrDiwGs1dAFideBwkEnZACt6Fi', '','ROLE_STAFF,ROLE_MANAGER,ROLE_ADMIN', 0, 1,null),
('000001', '张泽山', 'zzs12345','$2a$10$YEmcUKnHyqm2UV1MGeKmuOJetvGfI6w/hlnsFJEEJ7eGD4.D.gHti', '', 'ROLE_STAFF,ROLE_MANAGER,ROLE_ADMIN', 0, 1, null);


create table if not exists spot(
	`id` int not null primary key auto_increment,
    `type` varchar(20)  CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    `station` varchar(20)  CHARACTER SET utf8 COLLATE utf8_unicode_ci,
    `spot_number` varchar(20)  CHARACTER SET utf8 COLLATE utf8_unicode_ci,
    `train` varchar(20)  CHARACTER SET utf8 COLLATE utf8_unicode_ci,
    `status` varchar(20)  CHARACTER SET utf8 COLLATE utf8_unicode_ci,
    `remark` varchar(255)
);

-- insert into spot (`type`, `station`, `spot_number`, `train`, `status`, `remark`) values 
-- ("列车", "/", "/", "1号线", "未占用",""),
-- ("列车", "/", "/", "2号线", "未占用",""),
-- ("列车", "/", "/", "3号线", "未占用",""),
-- ("列车", "/", "/", "4号线", "未占用",""),
-- ("列车", "/", "/", "5号线", "未占用",""),
-- ("列车", "/", "/", "6号线", "未占用",""),
-- ("列车", "/", "/", "7号线", "未占用",""),
-- ("列车", "/", "/", "8号线", "未占用",""),
-- ("站内", "东阳站", "3号灯箱", "/","未占用", ""),
-- ("站内", "东阳站", "11号灯箱", "/","未占用", ""),
-- ("站内", "东阳站", "1号灯箱", "/","未占用", ""),
-- ("站内", "东阳站", "5号灯箱", "/","未占用", ""),
-- ("站内", "金华站", "5号灯箱", "/","未占用", ""),
-- ("站内", "金华站", "13号灯箱", "/","未占用", ""),
-- ("站内", "丽水站", "7号灯箱", "/","未占用", ""),
-- ("站内", "丽水站", "10号灯箱", "/","未占用", ""),
-- ("站内", "丽水站", "1号灯箱", "/","未占用", ""),
-- ("站内", "杭州站", "9号灯箱", "/","未占用", ""),
-- ("站内", "杭州站", "8号灯箱", "/","未占用", ""),
-- ("站内", "杭州站", "7号灯箱", "/","未占用", ""),
-- ("站内", "杭州站", "6号灯箱", "/","未占用", ""),
-- ("站内", "杭州站", "5号灯箱", "/","未占用", ""),
-- ("站内", "杭州站", "4号灯箱", "/","未占用", "")
-- ;



-- drop table ad;

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



-- insert into ad (`spot_id`,`ad_type`,`industry_type`, `content`,`company`, `ddl`,`picture`,`remark`, `isdroped`) values
-- ("2", "12灯箱包柱","蔡徐坤媒体","iKun", "小黑子有限公司","2023/6/20","cxk.jpg", "", false),
-- ("3", "12灯箱包柱","蔡徐坤媒体","iKun", "小黑子有限公司","2023/6/17","cxk.jpg", "", false),
-- ("4", "12灯箱包柱","蔡徐坤媒体","iKun", "小黑子有限公司","2023/6/18","cxk.jpg", "", false),
-- ("2", "12灯箱包柱","蔡徐坤媒体","iKun", "小黑子有限公司","2023/6/19","cxk.jpg", "", false),
-- ("4", "12灯箱包柱","蔡徐坤媒体","iKun", "小黑子有限公司","2024/12/1","cxk.jpg", "", false),
-- ("2", "12灯箱包柱","蔡徐坤媒体2","iKun2", "小黑子有限公司2","2027/7/1","cxk.jpg", "",false),
-- ("1", "12灯箱包柱","张泽山丑照","iZZS", "小白子有限公司","2026/6/13","zzs.jpg", "asd", false),
-- ("1", "12灯箱包柱","张泽山丑照2","iZZS2", "小白子有限公司2","2023/5/13","zzs.jpg", "asd", false),
-- ("4", "12灯箱包柱","张泽山丑3","iZZ3", "小白子有限公司","2023/5/13","zzs.jpg", "asd", true),
-- ("1", "12灯箱包柱","张泽山丑3","iZZ3", "小白子有限公司","2020/5/13","zzs.jpg", "asd", true),
-- ("2", "12灯箱包柱","蔡徐坤媒体","iKun", "小黑子有限公司","2023/6/20","cxk.jpg", "", false),
-- ("3", "12灯箱包柱","蔡徐坤媒体","iKun", "小黑子有限公司","2023/6/17","cxk.jpg", "", false),
-- ("4", "12灯箱包柱","蔡徐坤媒体","iKun", "小黑子有限公司","2023/6/18","cxk.jpg", "", false),
-- ("2", "12灯箱包柱","蔡徐坤媒体","iKun", "小黑子有限公司","2023/6/19","cxk.jpg", "", false),
-- ("4", "12灯箱包柱","蔡徐坤媒体","iKun", "小黑子有限公司","2024/12/1","cxk.jpg", "", false),
-- ("2", "12灯箱包柱","蔡徐坤媒体2","iKun2", "小黑子有限公司2","2027/7/1","cxk.jpg", "",false),
-- ("1", "12灯箱包柱","张泽山丑照","iZZS", "小白子有限公司","2026/6/13","zzs.jpg", "asd", false),
-- ("1", "12灯箱包柱","张泽山丑照2","iZZS2", "小白子有限公司2","2023/5/13","zzs.jpg", "asd", false),
-- ("4", "12灯箱包柱","张泽山丑3","iZZ3", "小白子有限公司","2023/5/13","zzs.jpg", "asd", true),
-- ("1", "12灯箱包柱","张泽山丑3","iZZ3", "小白子有限公司","2020/5/13","zzs.jpg", "asd", true),
-- ("2", "12灯箱包柱","蔡徐坤媒体","iKun", "小黑子有限公司","2023/6/20","cxk.jpg", "", false),
-- ("3", "12灯箱包柱","蔡徐坤媒体","iKun", "小黑子有限公司","2023/6/17","cxk.jpg", "", false),
-- ("4", "12灯箱包柱","蔡徐坤媒体","iKun", "小黑子有限公司","2023/6/18","cxk.jpg", "", false),
-- ("2", "12灯箱包柱","蔡徐坤媒体","iKun", "小黑子有限公司","2023/6/19","cxk.jpg", "", false),
-- ("4", "12灯箱包柱","蔡徐坤媒体","iKun", "小黑子有限公司","2024/12/1","cxk.jpg", "", false),
-- ("2", "12灯箱包柱","蔡徐坤媒体2","iKun2", "小黑子有限公司2","2027/7/1","cxk.jpg", "",false),
-- ("1", "12灯箱包柱","张泽山丑照","iZZS", "小白子有限公司","2026/6/13","zzs.jpg", "asd", false),
-- ("1", "12灯箱包柱","张泽山丑照2","iZZS2", "小白子有限公司2","2023/5/13","zzs.jpg", "asd", false),
-- ("4", "12灯箱包柱","张泽山丑3","iZZ3", "小白子有限公司","2023/5/13","zzs.jpg", "asd", true),
-- ("1", "12灯箱包柱","张泽山丑3","iZZ3", "小白子有限公司","2020/5/13","zzs.jpg", "asd", true),
-- ("2", "12灯箱包柱","蔡徐坤媒体","iKun", "小黑子有限公司","2023/6/20","cxk.jpg", "", false),
-- ("3", "12灯箱包柱","蔡徐坤媒体","iKun", "小黑子有限公司","2023/6/17","cxk.jpg", "", false),
-- ("4", "12灯箱包柱","蔡徐坤媒体","iKun", "小黑子有限公司","2023/6/18","cxk.jpg", "", false),
-- ("2", "12灯箱包柱","蔡徐坤媒体","iKun", "小黑子有限公司","2023/6/19","cxk.jpg", "", false),
-- ("4", "12灯箱包柱","蔡徐坤媒体","iKun", "小黑子有限公司","2024/12/1","cxk.jpg", "", false),
-- ("2", "12灯箱包柱","蔡徐坤媒体2","iKun2", "小黑子有限公司2","2027/7/1","cxk.jpg", "",false),
-- ("1", "12灯箱包柱","张泽山丑照","iZZS", "小白子有限公司","2026/6/13","zzs.jpg", "asd", false),
-- ("1", "12灯箱包柱","张泽山丑照2","iZZS2", "小白子有限公司2","2023/5/13","zzs.jpg", "asd", false),
-- ("4", "12灯箱包柱","张泽山丑3","iZZ3", "小白子有限公司","2023/5/13","zzs.jpg", "asd", true),
-- ("1", "12灯箱包柱","张泽山丑3","iZZ3", "小白子有限公司","2020/5/13","zzs.jpg", "asd", true),
-- ("2", "12灯箱包柱","蔡徐坤媒体","iKun", "小黑子有限公司","2023/6/20","cxk.jpg", "", false),
-- ("3", "12灯箱包柱","蔡徐坤媒体","iKun", "小黑子有限公司","2023/6/17","cxk.jpg", "", false),
-- ("4", "12灯箱包柱","蔡徐坤媒体","iKun", "小黑子有限公司","2023/6/18","cxk.jpg", "", false),
-- ("2", "12灯箱包柱","蔡徐坤媒体","iKun", "小黑子有限公司","2023/6/19","cxk.jpg", "", false),
-- ("4", "12灯箱包柱","蔡徐坤媒体","iKun", "小黑子有限公司","2024/12/1","cxk.jpg", "", false),
-- ("2", "12灯箱包柱","蔡徐坤媒体2","iKun2", "小黑子有限公司2","2027/7/1","cxk.jpg", "",false),
-- ("1", "12灯箱包柱","张泽山丑照","iZZS", "小白子有限公司","2026/6/13","zzs.jpg", "asd", false),
-- ("1", "12灯箱包柱","张泽山丑照2","iZZS2", "小白子有限公司2","2023/5/13","zzs.jpg", "asd", false),
-- ("4", "12灯箱包柱","张泽山丑3","iZZ3", "小白子有限公司","2023/5/13","zzs.jpg", "asd", true),
-- ("1", "12灯箱包柱","张泽山丑3","iZZ3", "小白子有限公司","2020/5/13","zzs.jpg", "asd", true),
-- ("2", "12灯箱包柱","蔡徐坤媒体","iKun", "小黑子有限公司","2023/6/20","cxk.jpg", "", false),
-- ("3", "12灯箱包柱","蔡徐坤媒体","iKun", "小黑子有限公司","2023/6/17","cxk.jpg", "", false),
-- ("4", "12灯箱包柱","蔡徐坤媒体","iKun", "小黑子有限公司","2023/6/18","cxk.jpg", "", false),
-- ("2", "12灯箱包柱","蔡徐坤媒体","iKun", "小黑子有限公司","2023/6/19","cxk.jpg", "", false),
-- ("4", "12灯箱包柱","蔡徐坤媒体","iKun", "小黑子有限公司","2024/12/1","cxk.jpg", "", false),
-- ("2", "12灯箱包柱","蔡徐坤媒体2","iKun2", "小黑子有限公司2","2027/7/1","cxk.jpg", "",false),
-- ("1", "12灯箱包柱","张泽山丑照","iZZS", "小白子有限公司","2026/6/13","zzs.jpg", "asd", false),
-- ("1", "12灯箱包柱","张泽山丑照2","iZZS2", "小白子有限公司2","2023/5/13","zzs.jpg", "asd", false),
-- ("4", "12灯箱包柱","张泽山丑3","iZZ3", "小白子有限公司","2023/5/13","zzs.jpg", "asd", true),
-- ("1", "12灯箱包柱","张泽山丑3","iZZ3", "小白子有限公司","2020/5/13","zzs.jpg", "asd", true),
-- ("2", "12灯箱包柱","蔡徐坤媒体","iKun", "小黑子有限公司","2023/6/20","cxk.jpg", "", false),
-- ("3", "12灯箱包柱","蔡徐坤媒体","iKun", "小黑子有限公司","2023/6/17","cxk.jpg", "", false),
-- ("4", "12灯箱包柱","蔡徐坤媒体","iKun", "小黑子有限公司","2023/6/18","cxk.jpg", "", false),
-- ("2", "12灯箱包柱","蔡徐坤媒体","iKun", "小黑子有限公司","2023/6/19","cxk.jpg", "", false),
-- ("4", "12灯箱包柱","蔡徐坤媒体","iKun", "小黑子有限公司","2024/12/1","cxk.jpg", "", false),
-- ("2", "12灯箱包柱","蔡徐坤媒体2","iKun2", "小黑子有限公司2","2027/7/1","cxk.jpg", "",false),
-- ("1", "12灯箱包柱","张泽山丑照","iZZS", "小白子有限公司","2026/6/13","zzs.jpg", "asd", false),
-- ("1", "12灯箱包柱","张泽山丑照2","iZZS2", "小白子有限公司2","2023/5/13","zzs.jpg", "asd", false),
-- ("4", "12灯箱包柱","张泽山丑3","iZZ3", "小白子有限公司","2023/5/13","zzs.jpg", "asd", true),
-- ("1", "12灯箱包柱","张泽山丑3","iZZ3", "小白子有限公司","2020/5/13","zzs.jpg", "asd", true),
-- ("2", "12灯箱包柱","蔡徐坤媒体","iKun", "小黑子有限公司","2023/6/20","cxk.jpg", "", false),
-- ("3", "12灯箱包柱","蔡徐坤媒体","iKun", "小黑子有限公司","2023/6/17","cxk.jpg", "", false),
-- ("4", "12灯箱包柱","蔡徐坤媒体","iKun", "小黑子有限公司","2023/6/18","cxk.jpg", "", false),
-- ("2", "12灯箱包柱","蔡徐坤媒体","iKun", "小黑子有限公司","2023/6/19","cxk.jpg", "", false),
-- ("4", "12灯箱包柱","蔡徐坤媒体","iKun", "小黑子有限公司","2024/12/1","cxk.jpg", "", false),
-- ("2", "12灯箱包柱","蔡徐坤媒体2","iKun2", "小黑子有限公司2","2027/7/1","cxk.jpg", "",false),
-- ("1", "12灯箱包柱","张泽山丑照","iZZS", "小白子有限公司","2026/6/13","zzs.jpg", "asd", false),
-- ("1", "12灯箱包柱","张泽山丑照2","iZZS2", "小白子有限公司2","2023/5/13","zzs.jpg", "asd", false),
-- ("4", "12灯箱包柱","张泽山丑3","iZZ3", "小白子有限公司","2023/5/13","zzs.jpg", "asd", true),
-- ("1", "12灯箱包柱","张泽山丑3","iZZ3", "小白子有限公司","2020/5/13","zzs.jpg", "asd", true)
-- ;

 