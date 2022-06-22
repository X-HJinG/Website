CREATE DATABASE IF NOT EXISTS work DEFAULT CHARACTER SET = utf8mb4;

Use work;

CREATE TABLE `account`(
	`userId` varchar(20) primary key,
	`password` varchar(20),
  `userName` varchar(20)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

CREATE TABLE `laccount`(
	`userId` varchar(20) primary key,
	`password` varchar(20),
  `userName` varchar(20)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

CREATE TABLE `userInfo`(
	`userId` varchar(20) primary key,
  `userName` varchar(20),
  `userTel` varchar(20),
  `userNo` varchar(20),
  `mNo` bigint(20),
  `dNo` bigint(20)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
 

CREATE TABLE `notice`(
	`nid` int primary key auto_increment,
	`userId` varchar(20),
  `uploadtime` datetime(3),
  `mainContext` varchar(250),
  `remark` varchar(20) not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

CREATE TABLE `record`(
`rid` int primary key auto_increment,
`userId` varchar(20) ,
`historyDate` date,
`address` varchar(30),
`remark` varchar(50),
`status` bigint(20)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

INSERT INTO laccount (userId,password,userName) VALUES ('admin','admin','zhangsan');
INSERT INTO userInfo (userId,userName,userTel,userNo,mNo,dNo) VALUES ('admin','zhangsan','123','20196172',0,1);

-- // 公告定时清理 —— mysql 定时任务


-- delimiter $$  
-- create procedure checkNotice()
-- begin
-- 	delete from notice where uploadtime < DATE_SUB(now(),INTERVAL 1 HOUR);
-- end$$
-- delimiter;
-- set sql_safe_updates =0;


-- //查看定时任务是否开启
-- SELECT @@event_scheduler;

-- //查询事件
-- SELECT * FROM information_schema.events; 

-- //定时调度
-- create event if not exists autoDelNotice
-- on schedule every 1 day starts '2021-06-25 00:00:00'
-- on completion preserve
-- enable
-- do call checkNotice();
