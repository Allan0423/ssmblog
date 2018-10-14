package cn.zp.controller;

import cn.zp.service.ITestUserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class TestController {

    @Resource
    ITestUserService userService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String find(@PathVariable("id") String id){
        return userService.findById(Integer.parseInt(id)).toString();
    }
}
