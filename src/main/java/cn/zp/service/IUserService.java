package cn.zp.service;

import cn.zp.model.User;

import java.util.List;

public interface IUserService {
    void save(User user);
    boolean update(User user);
    boolean delete(int id);
    User findById(int id);
    List<User> findAll();
}
