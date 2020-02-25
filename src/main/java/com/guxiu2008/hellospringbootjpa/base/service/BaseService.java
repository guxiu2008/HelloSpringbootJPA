package com.guxiu2008.hellospringbootjpa.base.service;

import com.guxiu2008.hellospringbootjpa.base.repository.BaseRepository;
import com.guxiu2008.hellospringbootjpa.util.DynamicConditionCreator;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Package: com.guxiu2008.hellospringbootjpa.service
 * DESCRIPTION:
 *
 * @author guxiu2008
 * @create 2020-02-24 22:58
 **/
@Service
public abstract class BaseService<T> {
    @Autowired
    protected DynamicConditionCreator dynamicConditionCreator;

    @Setter
    private BaseRepository baseRepository;

    public List<T> findByCondition(T t) {
        List<T> resultList = null;
        Specification<T> querySpecifi = dynamicConditionCreator.getSpecificationbyPojo(t);
        return baseRepository.findAll(querySpecifi);
    }
}
