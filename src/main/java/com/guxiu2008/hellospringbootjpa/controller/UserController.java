package com.guxiu2008.hellospringbootjpa.controller;

import com.guxiu2008.hellospringbootjpa.pojo.UserPojo;
import com.guxiu2008.hellospringbootjpa.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Package: com.guxiu2008.hellospringbootjpa.controller
 * DESCRIPTION:
 *
 * @author guxiu2008
 * @create 2020-02-22 15:39
 **/
@Slf4j
@Api(description = "用户操作接口")
@Controller("user")
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "获取用户信息", notes="全量查询")
    //@ApiImplicitParam(name = "telephone", value = "电话号码", paramType = "query", required = true, dataType = "Integer")
    @RequestMapping(value = "findAll", method= RequestMethod.POST)
    @ResponseBody
    public List<UserPojo> findAll() {
        return userService.findAll();
    }
}
