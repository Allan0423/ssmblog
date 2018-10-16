package cn.zp;

import cn.zp.model.TestUser;
import cn.zp.service.ITestUserService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class SSMTest {
    private static Logger LOGGER = Logger.getLogger(SSMTest.class);

    @Resource
    private ITestUserService userService;

    @Test
    public void test1(){
        TestUser testUser = userService.findById(1);
        LOGGER.info(testUser.toString());
    }
}
