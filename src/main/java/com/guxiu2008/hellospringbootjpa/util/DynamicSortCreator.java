package com.guxiu2008.hellospringbootjpa.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Package: com.guxiu2008.hellospringbootjpa.util
 * DESCRIPTION:
 *
 * @author guxiu2008
 * @create 2020-03-06 11:35
 **/
@Slf4j
@Component
public class DynamicSortCreator {

    public Sort getSort(List<String> listDesc, List<String> listAsc) {
        List<Sort.Order> listDescOrder = new ArrayList<Sort.Order>();
        if (listDesc != null && listDesc.size() != 0) {
            for (String filedName : listDesc) {
                Sort.Order order = new Sort.Order(Sort.Direction.DESC, filedName);
                listDescOrder.add(order);
            }
        }

        if (listAsc != null && listAsc.size() != 0) {
            for (String filedName : listAsc) {
                Sort.Order order = new Sort.Order(Sort.Direction.ASC, filedName);
                listDescOrder.add(order);
            }
        }

        return Sort.by(listDescOrder);
    }
}
