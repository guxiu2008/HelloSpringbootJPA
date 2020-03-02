package com.guxiu2008.hellospringbootjpa.base.controller;

import com.guxiu2008.hellospringbootjpa.base.service.BaseService;
import com.guxiu2008.hellospringbootjpa.pojo.BookPojo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Package: com.guxiu2008.hellospringbootjpa.base.controller
 * DESCRIPTION:
 *
 * @author guxiu2008
 * @create 2020-02-26 22:25
 **/
@Slf4j
@Controller
public class BaseController<T> {
    protected List<String> params = new ArrayList<>();
}
