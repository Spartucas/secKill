package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by guolei on 2018/1/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SuccessKilledDaoTest {

    @Resource
    private SuccessKilledDao successKilledDao;
    @Test
    public void insertSuccessKiller() throws Exception {
        int insertCount = successKilledDao.insertSuccessKiller(1000L, 18625532730L);
        System.out.println(insertCount);

    }

    @Test
    public void queryByIdWithSeckill() throws Exception {
        long id = 1000L;
        long userPhone = 18625532730L;
        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(id, userPhone);
        System.out.println(successKilled);
    }

}