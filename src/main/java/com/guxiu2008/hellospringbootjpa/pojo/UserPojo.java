package com.guxiu2008.hellospringbootjpa.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.List;
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
public class UserPojo extends BasePojo {

    @Id
    @Setter
    @Getter
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Setter
    @Getter
    @Column(name = "name", nullable = true, length = 255)
    private String name;

    @Basic
    @Setter
    @Getter
    @Column(name = "age", nullable = true)
    private Integer age;

    @Basic
    @Setter
    @Getter
    @Column(name = "address", nullable = true, length = 255)
    private String address;

    @Getter
    @Setter
    /*
        CascadeType.PERSIST （级联保存） 保存实体也保存关联对象；
        CascadeType.REMOVE （级联删除） 删除实体也删除关联对象；
        CascadeType.REFRESH （级联刷新） 刷新实体时也刷新关联对象；
        CascadeType.MERGE （级联更新）更新实体也更新关联对象。 或者使用CascadeType.ALL，表示选择全部四项
     */
    @OneToMany(targetEntity = BookPojo.class, cascade = CascadeType.MERGE)
    @JoinColumn(name = "userid")
    private List<BookPojo> bookPojo;
}
