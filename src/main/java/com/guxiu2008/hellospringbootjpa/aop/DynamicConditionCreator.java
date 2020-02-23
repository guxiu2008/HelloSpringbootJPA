package com.guxiu2008.hellospringbootjpa.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Package: com.guxiu2008.hellospringbootjpa.aop
 * DESCRIPTION:
 *
 * @author guxiu2008
 * @create 2020-02-23 21:11
 **/
@Slf4j
public class DynamicConditionCreator<T> {

    public Specification getSpecification(T t) {
        Map<String, String> mapFieldType = getMapFieldType(t);
        return getSpecification(t, mapFieldType);
    }

    private Map<String, String> getMapFieldType(T t) {
        Class clazz = t.getClass();
        Map<String, String> mapFieldType = new HashMap<>();
        // 获取对象属性
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.getName().equals("log")) {
                continue;
            }
            field.setAccessible(true); // 私有属性必须设置访问权限
            mapFieldType.put(field.getName(), field.getType().getName());
            log.debug(String.format("Field name: %s, Field type name: %s", field.getName(), field.getType().getName()));

        }
        return mapFieldType;
    }

    private Specification getSpecification(T t, Map<String, String> mapFieldType) {
        Class clazz;
        Object obj;
        try {
            clazz = t.getClass();
            obj = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
        Specification<T> query = new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                try {
                    List<Predicate> predicates = new ArrayList<>();
                    StringBuffer methodName = new StringBuffer();
                    Method method;
                    for (Map.Entry<String, String> entry : mapFieldType.entrySet()) {
                        methodName.delete(0, methodName.length());
                        methodName.append("get" + entry.getKey().substring(0, 1).toUpperCase() + entry.getKey().substring(1));
                        method = clazz.getMethod(methodName.toString(), new Class[]{});
                        Object resultValue = method.invoke(obj, new Object[]{});
                        switch (entry.getValue()) {
                            case "java.lang.Integer":
                                if (resultValue != null) {
                                    predicates.add(criteriaBuilder.equal(root.get(entry.getKey()), resultValue));
                                }
                                break;
                            case "java.lang.String":
                                if (!StringUtils.isEmpty(resultValue)) {
                                    predicates.add(criteriaBuilder.equal(root.get(entry.getKey()), resultValue));
                                }
                                break;
                            default:
                                continue;
                        }

                    }
                    return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                    return null;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    return null;
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };
        return query;
    }
}
