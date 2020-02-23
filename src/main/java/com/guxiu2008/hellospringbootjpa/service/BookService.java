package com.guxiu2008.hellospringbootjpa.service;

import com.guxiu2008.hellospringbootjpa.pojo.BookPojo;
import com.guxiu2008.hellospringbootjpa.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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

    public List<BookPojo> findAll(BookPojo bookPojo) {

        Specification<BookPojo> query = new Specification<BookPojo>() {
            @Override
            public Predicate toPredicate(Root<BookPojo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        };
        return bookRepository.findAll(query);
    }

    public List<BookPojo> findByName(String bookName) {
        return bookRepository.findByName(bookName);
    }
}
