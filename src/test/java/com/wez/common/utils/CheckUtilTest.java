package com.wez.common.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author wez
 * @Date 2020/4/1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-context.xml"})
public class CheckUtilTest {

    @Autowired
    MessageSource resources;

    @Test
    public void test() {
        CheckUtil.setResources(resources);

        try {
            CheckUtil.notNull(null, "id.error", 1001);
        } catch(Throwable t) {
            t.printStackTrace();
        }
    }

}
