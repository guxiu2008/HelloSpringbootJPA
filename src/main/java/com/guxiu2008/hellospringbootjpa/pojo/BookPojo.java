package com.guxiu2008.hellospringbootjpa.pojo;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.sql.Timestamp;
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
@Table(name = "book", schema = "jpa", catalog = "")
public class BookPojo {
    private Integer id;
    private String name;
    private Integer userid;
    private Timestamp date;

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
    @Column(name = "userid", nullable = true)
    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookPojo bookPojo = (BookPojo) o;
        return Objects.equals(id, bookPojo.id) &&
                Objects.equals(name, bookPojo.name) &&
                Objects.equals(userid, bookPojo.userid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, userid);
    }

    @Basic
    @Column(name = "date", nullable = true)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
