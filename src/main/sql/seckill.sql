-- 事务Sql在mysql端执行
-- 秒杀执行存储过程
DELIMITER $$ -- 换行符由 ; 转换为 $$
-- 定义存储过程
--参数：in 输入参数; out 输出参数
--row_count():返回上一条修改类型sql的影响行数
--row_count():0:未修改数据；>0表示修改的行数; <0：sql错误/未执行修改Sql
CREATE PROCEDURE `seckill`.`execute_seckill`
  (in v_seckill_id bigint,in v_phone bigint,
   in v_kill_time TIMESTAMP ,out r_result INT )
BEGIN
  DECLARE insert_count int DEFAULT 0;
  START TRANSACTION ;
  insert ignore into success_killed
    (seckill_id,user_phone,create_time)
    VALUES (v_seckill_id,v_phone,v_kill_time);
  select row_count() into insert_count;
  if (insert_count = 0) THEN
    ROLLBACK ;
    set r_result = -1;
  elseif (insert_count <0) then
    ROLLBACK ;
    set r_result = -2;
  else
    update seckill set number = number-1 where seckill_id = v_seckill_id
    and end_time > v_kill_time and start_time < v_kill_time and number >0;
  select row_count() into insert_count;
  if (insert_count =0) THEN
  ROLLBACK ;
  set r_result=0;
  elseif (insert_count <0) THEN
  ROLLBACK ;
  set r_result = -2;
  ELSE
    COMMIT ;
    SET r_result = 1;
  end if;
  end if;
END ;
$$
--存储过程定义结束

DELIMITER ;

set @r_result =-3;
--执行存储过程
call execute_seckill(1004,18625532777,now(),@r_result);
--获取结果
SELECT @r_result;
