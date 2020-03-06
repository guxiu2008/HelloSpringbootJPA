package com.guxiu2008.hellospringbootjpa.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Package: com.guxiu2008.hellospringbootjpa.pojo
 * DESCRIPTION:
 *
 * @author guxiu2008
 * @create 2020-03-05 16:13
 **/
public class BasePojo {

    @Setter
    @Getter
    private List<String> listAscField;

    @Setter
    @Getter
    private List<String> listDescField;

}
