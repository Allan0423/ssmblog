package cn.zp.service.impl;

import cn.zp.mapper.UserMapper;
import cn.zp.model.User;
import cn.zp.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserService implements IUserService {

    @Resource
    UserMapper mapper;

    @Override
    public void save(User user) {
        mapper.save(user);
    }

    @Override
    public boolean update(User user) {
        return mapper.update(user);
    }

    @Override
    public boolean delete(int id) {
        return mapper.delete(id);
    }

    @Override
    public User findById(int id) {
        return mapper.findById(id);
    }

    @Override
    public List<User> findAll() {
        return mapper.findAll();
    }
}
