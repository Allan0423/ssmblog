package cn.zp.service.impl;

import cn.zp.mapper.TestMapper;
import cn.zp.model.TestUser;
import cn.zp.service.ITestUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("testUserService")
@Transactional(rollbackFor = Exception.class)
public class TestUserServiceImpl implements ITestUserService {

    @Resource
    TestMapper mapper;

    @Override
    public void save(TestUser testUser) {
        mapper.save(testUser);
    }

    @Override
    public boolean update(TestUser testUser) {
        return mapper.update(testUser);
    }

    @Override
    public boolean delete(int id) {
        return mapper.delete(id);
    }

    @Override
    public TestUser findById(int id) {
        return mapper.findById(id);
    }

    @Override
    public List<TestUser> findAll() {
        return mapper.findAll();
    }
}
