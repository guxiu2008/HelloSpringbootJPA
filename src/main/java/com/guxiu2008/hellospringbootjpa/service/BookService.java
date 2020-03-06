package com.guxiu2008.hellospringbootjpa.service;

import com.guxiu2008.hellospringbootjpa.pojo.BookPojo;
import com.guxiu2008.hellospringbootjpa.repository.BookRepository;
import com.guxiu2008.hellospringbootjpa.util.DynamicConditionCreator;
import com.guxiu2008.hellospringbootjpa.util.DynamicSortCreator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Package: com.guxiu2008.hellospringbootjpa.service
 * DESCRIPTION:
 *
 * @author guxiu2008
 * @create 2020-02-22 15:32
 **/
@Slf4j
@Service
public class BookService {

    @Autowired
    DynamicConditionCreator dynamicConditionCreator;

    @Autowired
    DynamicSortCreator dynamicSortCreator;

    @Autowired
    private BookRepository bookRepository;

    public List<BookPojo> findAll() {
        return bookRepository.findAll();
    }

    public List<BookPojo> findByName(String bookName) {
        return bookRepository.findByName(bookName);
    }

    public List<BookPojo> findByConditionDefault(BookPojo bookPojo) {
        return bookRepository.findAll(this.dynamicConditionCreator.getSpecificationbyPojo(bookPojo));
    }

    public Page<BookPojo> findByConditionDefaultPage(BookPojo bookPojo, Pageable pageable) {
        //Sort sort = this.dynamicSortCreator.getSort(bookPojo.getListDescField(), bookPojo.getListAscField());
        return bookRepository.findAll(this.dynamicConditionCreator.getSpecificationbyPojo(bookPojo), pageable);
    }
}
