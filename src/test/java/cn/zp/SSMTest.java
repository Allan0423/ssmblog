package cn.zp;

import cn.zp.model.User;
import cn.zp.service.IUserService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class SSMTest {
    private static Logger LOGGER = Logger.getLogger(SSMTest.class);

    @Resource
    private IUserService userService;

    @Test
    public void test1(){
        User user = userService.findById(1);
        LOGGER.info(user.toString());
    }
}
