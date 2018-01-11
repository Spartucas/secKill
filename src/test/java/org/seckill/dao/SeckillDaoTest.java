package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 配置Spring和junit整合，junit启动时加载springIOC容器
 * Created by guolei on 2018/1/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SeckillDaoTest {
    //注入dao实现类依赖
    @Resource
    private SeckillDao seckillDao;
    @Test
    public void reduceNumber() throws Exception {

        long id = 1000;
        Date date = new Date();
        int total = seckillDao.reduceNumber(id, date);
        System.out.println(total);
    }

    @Test
    public void queryById() throws Exception {
        long id = 1000;
        Seckill seckill=seckillDao.queryById(id);
        System.out.println(seckill.getName());

    }

    @Test
    public void queryAll() throws Exception {
        List<Seckill> list = seckillDao.queryAll(0, 100);
        for (Seckill seckill : list) {
            System.out.println(seckill);
        }
    }

}