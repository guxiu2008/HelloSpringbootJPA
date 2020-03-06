package com.guxiu2008.hellospringbootjpa.repository;

import com.guxiu2008.hellospringbootjpa.pojo.BookPojo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Package: com.guxiu2008.hellospringbootjpa.service
 * DESCRIPTION:
 *
 * @author guxiu2008
 * @create 2020-02-22 15:28
 **/
@Repository
public interface BookRepository extends JpaRepository<BookPojo, String>, JpaSpecificationExecutor {
    public List<BookPojo> findByName(String bookName);
}
