package cn.zp.controller;

import cn.zp.service.IUserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    IUserService userService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String find(@PathVariable("id") String id){
        return userService.findById(Integer.parseInt(id)).toString();
    }
}
