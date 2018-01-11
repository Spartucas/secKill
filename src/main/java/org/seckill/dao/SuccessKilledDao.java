package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKilled;

/**
 * Created by guolei on 2018/1/6.
 */
public interface SuccessKilledDao {

    int insertSuccessKiller(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
}
