--数据库初始化脚本

--创建数据库
CREATE database seckill;
--创建数据库
use seckill;
--创建秒杀库存表
CREATE TABLE seckill(
seckill_id bigint NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
name VARCHAR (120) NOT NULL COMMENT '商品名称',
number INT NOT NULL COMMENT '商品数量',
start_time TIMESTAMP NOT NULL COMMENT '秒杀开始时间',
end_time TIMESTAMP NOT NULL COMMENT '秒杀结束时间',
create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '秒杀创建时间',
PRIMARY KEY (seckill_id),
KEY idx_start_time(start_time),
KEY idx_end_time(end_time),
KEY idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT = 1000 DEFAULT CHARSET = utf8 COMMENT = '秒杀库存表';


-- 初始化数据
INSERT INTO seckill(name,number,start_time,end_time)
values
  ('7000元秒杀iphoneX',100,'2018-01-08 00:00:00','2018-01-08 0:01:00'),
  ('6000元秒杀iphone8 Plus',100,'2018-01-08 00:00:00','2018-01-08 0:01:00'),
  ('5000元秒杀iphone8',200,'2018-01-08 00:00:00','2018-01-08 0:01:00'),
  ('4000元秒杀iphone7 Plus',300,'2018-01-08 00:00:00','2018-01-08 0:01:00'),
  ('3500元秒杀iphone7',400,'2018-01-08 00:00:00','2018-01-08 0:01:00');


-- 秒杀成功明细表
-- 用户登录认证相关的信息
CREATE TABLE success_killed(
`seckill_id` bigint NOT NULL comment '秒杀商品id',
`user_phone` bigint NOT NULL comment '用户手机号',
`state` tinyint NOT NULL DEFAULT -1 comment '状态标识：-1：无效 0：成功 1：已付款 2：已发货',
`create_time` TIMESTAMP NOT NULL comment '创建时间',
PRIMARY KEY (seckill_id,user_phone), /*联合主键*/
key idx_create_time(create_time)
)ENGINE=InnoDB DEFAULT CHARSET = utf8 COMMENT = '秒杀成功明细表';


