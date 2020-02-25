package com.guxiu2008.hellospringbootjpa.base.repository;

import com.guxiu2008.hellospringbootjpa.pojo.BookPojo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

/**
 * Package: com.guxiu2008.hellospringbootjpa.base.repository
 * DESCRIPTION:
 *
 * @author guxiu2008
 * @create 2020-02-24 23:02
 **/
@Component
public abstract interface BaseRepository extends JpaRepository<BookPojo, String>, JpaSpecificationExecutor {
}
