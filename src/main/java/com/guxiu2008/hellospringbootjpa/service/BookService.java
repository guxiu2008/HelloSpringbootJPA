package com.guxiu2008.hellospringbootjpa.service;

import com.guxiu2008.hellospringbootjpa.pojo.BookPojo;
import com.guxiu2008.hellospringbootjpa.pojo.UserPojo;
import com.guxiu2008.hellospringbootjpa.repository.BookRepository;
import com.guxiu2008.hellospringbootjpa.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    BookRepository bookRepository;

    public List<BookPojo> findAll() {
        return bookRepository.findAll();
    }

    public List<BookPojo> findByName(String bookName) {
        return bookRepository.findByName(bookName);
    }
}
