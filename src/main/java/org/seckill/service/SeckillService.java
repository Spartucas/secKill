package org.seckill.service;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;

import java.util.List;

/**
 * Created by guolei on 2018/1/7.
 */
public interface SeckillService {

    //查询所有秒杀记录
    List<Seckill> getSeckillList();

    //查询单个秒杀记录
    Seckill getSeckillById(long seckillId);

    //秒杀开始时输出秒杀接口的地址，否则输出系统时间和秒杀时间
    Exposer exportSeckillUrl(long seckillId);

    //执行秒杀操作
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
            throws SecurityException,RepeatKillException,SeckillCloseException;

    //执行秒杀操作通过存储过程
    SeckillExecution executeSeckillByProcedure(long seckillId, long userPhone, String md5)
            throws SecurityException,RepeatKillException,SeckillCloseException;
}
