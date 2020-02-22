package com.guxiu2008.hellospringbootjpa.repository;

import com.guxiu2008.hellospringbootjpa.pojo.BookPojo;
import com.guxiu2008.hellospringbootjpa.pojo.UserPojo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Package: com.guxiu2008.hellospringbootjpa.service
 * DESCRIPTION:
 *
 * @author guxiu2008
 * @create 2020-02-22 15:28
 **/
public interface BookRepository extends JpaRepository<BookPojo, String> {
    public List<BookPojo> findByName(String bookName);
}
