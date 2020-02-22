package com.guxiu2008.hellospringbootjpa.repository;

import com.guxiu2008.hellospringbootjpa.pojo.UserPojo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Package: com.guxiu2008.hellospringbootjpa.service
 * DESCRIPTION:
 *
 * @author guxiu2008
 * @create 2020-02-22 15:28
 **/
public interface UserRepository extends JpaRepository<UserPojo, String> {
}
