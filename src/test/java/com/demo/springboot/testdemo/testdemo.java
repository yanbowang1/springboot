package com.demo.springboot.testdemo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Auther: user
 * @Date: 2018/11/8
 * @Description:
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestConfig.class})
@ActiveProfiles("dev")
public class testdemo {

    @Autowired
    private TestBean testBean;

    @Test
    public void test(){
        String expects = "from development profile";
        String actual = testBean.getContent();
        Assert.assertEquals(expects,actual);
    }
}
