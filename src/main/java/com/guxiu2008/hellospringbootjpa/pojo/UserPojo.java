package com.guxiu2008.hellospringbootjpa.pojo;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Objects;

/**
 * Package: com.guxiu2008.hellospringbootjpa.pojo
 * DESCRIPTION:
 *
 * @author guxiu2008
 * @create 2020-02-22 15:23
 **/
@Slf4j
@Entity
@Table(name = "user", schema = "jpa", catalog = "")
public class UserPojo {
    private Integer id;
    private String name;
    private Integer age;
    private String address;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "age", nullable = true)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Basic
    @Column(name = "address", nullable = true, length = 255)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPojo userPojo = (UserPojo) o;
        return Objects.equals(id, userPojo.id) &&
                Objects.equals(name, userPojo.name) &&
                Objects.equals(age, userPojo.age) &&
                Objects.equals(address, userPojo.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, address);
    }
}
