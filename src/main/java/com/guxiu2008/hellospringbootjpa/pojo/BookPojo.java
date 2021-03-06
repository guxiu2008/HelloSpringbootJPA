package com.guxiu2008.hellospringbootjpa.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
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
@Table(name = "book", schema = "jpa", catalog = "")
@ApiModel(description= "书本信息表")
public class BookPojo extends BasePojo {
    @Id
    @Getter
    @Setter
    @Column(name = "id", nullable = false)
    private String id;

    @Getter
    @Setter
    @Column(name = "name", nullable = true, length = 255)
    private String name;

    @Getter
    @Setter
    @Column(name = "userid", nullable = true)
    private String userid;

    @Getter
    @Setter
    @Column(name = "date", nullable = true)
    @ApiModelProperty(value = "时间. 类型为Date. 实例: 2020-02-23 00:00:00")
    private String date;
}
