package cn.zp.service;

import cn.zp.model.TestUser;

import java.util.List;

public interface ITestUserService {
    void save(TestUser testUser);
    boolean update(TestUser testUser);
    boolean delete(int id);
    TestUser findById(int id);
    List<TestUser> findAll();
}
