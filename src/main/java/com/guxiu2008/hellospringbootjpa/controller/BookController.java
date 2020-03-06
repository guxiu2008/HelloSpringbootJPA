package com.guxiu2008.hellospringbootjpa.controller;

import com.guxiu2008.hellospringbootjpa.pojo.BookPojo;
import com.guxiu2008.hellospringbootjpa.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
@Controller("book")
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    /*
    @ApiOperation(value = "获取书本信息", notes="全量查询")
    //@ApiImplicitParam(name = "telephone", value = "电话号码", paramType = "query", required = true, dataType = "Integer")
    @RequestMapping(value = "findAll", method= RequestMethod.GET)
    @ResponseBody
    public List<BookPojo> findAll() {
        return bookService.findAll();
    }

    @ApiOperation(value = "获取书本信息", notes="根据书本名称查询")
    //@ApiImplicitParam(name = "telephone", value = "电话号码", paramType = "query", required = true, dataType = "Integer")
    @RequestMapping(value = "findByName", method= RequestMethod.GET)
    @ResponseBody
    public List<BookPojo> findByName(String bookName) {
        return bookService.findByName(bookName);
    }

     */
    @ApiOperation(value = "获取书本信息", notes="根据表属性名称查询")
    //@ApiImplicitParam(name = "telephone", value = "电话号码", paramType = "query", required = true, dataType = "Integer")
    @RequestMapping(value = "findByCondition", method= RequestMethod.POST)
    @ResponseBody
    public List<BookPojo> findByCondition(BookPojo bookPojo) {
        return bookService.findByConditionDefault(bookPojo);
    }

    @ApiOperation(value = "分页获取书本信息", notes="根据表属性名称查询")
    //@ApiImplicitParam(name = "telephone", value = "电话号码", paramType = "query", required = true, dataType = "Integer")
    @RequestMapping(value = "findByConditionPage", method= RequestMethod.POST)
    @ResponseBody
    public Page<BookPojo> findByConditionPage(
            @PageableDefault(value = 15, sort = { "id" }, direction = Sort.Direction.ASC)
            Pageable pageable,
            BookPojo bookPojo) {
        return bookService.findByConditionDefaultPage(bookPojo, pageable);
    }

    /*
    @ApiOperation(value = "获取书本信息", notes="忽略id字段查询")
    //@ApiImplicitParam(name = "telephone", value = "电话号码", paramType = "query", required = true, dataType = "Integer")
    @RequestMapping(value = "findByConditionIgnoreId", method= RequestMethod.POST)
    @ResponseBody
    public List<BookPojo> findByConditionIgnoreId(BookPojo bookPojo) {
        return bookService.findByConditionIgnoreId(bookPojo);
    }

     */
}
