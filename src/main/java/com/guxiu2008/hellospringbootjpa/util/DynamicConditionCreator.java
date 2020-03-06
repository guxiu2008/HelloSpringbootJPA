package com.guxiu2008.hellospringbootjpa.util;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
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
@Component
public class DynamicConditionCreator<T> {

    @Setter
    private List<StringBuffer> params = new ArrayList<StringBuffer>();

    @Setter
    private T obj;

    @Setter
    private Map<String, String> mapObjFieldType = new HashMap<String, String>();

    @Setter
    private List<String> listDefaultIgnoreFields = new ArrayList<String>();

    public void addDefaultIgnoreField(String fieldName) {
        listDefaultIgnoreFields.add(fieldName);
    }

    public Specification getSpecificationbyPojo(T t) {
        this.setObj(t);
        this.getMapFieldType();
        return getSpecificationbyPojo();
    }

    private void getMapFieldType() {
        Class clazz = obj.getClass();
        // 获取对象属性
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.getName().equals("log")) {
                continue;
            }
            field.setAccessible(true); // 私有属性必须设置访问权限
            mapObjFieldType.put(field.getName(), field.getType().getName());
            log.debug(String.format("Field name: %s, Field type name: %s", field.getName(), field.getType().getName()));

        }
    }

    private Specification getSpecificationbyPojo() {
        Specification<T> query = new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                try {
                    List<Predicate> predicates = new ArrayList<>();
                    if (params == null || params.size() == 0) {
                        predicates = getPredicateDefault(root, criteriaQuery, criteriaBuilder);
                    } else {
                        predicates = getPredicateCustom(root, criteriaQuery, criteriaBuilder);
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
                } catch (ParseException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };
        return query;
    }

    private List<Predicate> getPredicateDefault(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ParseException {
        Class clazz = obj.getClass();
        List<Predicate> predicates = new ArrayList<>();
        StringBuffer methodName = new StringBuffer();
        Method method;
        for (Map.Entry<String, String> entry : mapObjFieldType.entrySet()) {
            if (listDefaultIgnoreFields.contains(entry.getKey())) {
                continue;
            }
            methodName.delete(0, methodName.length());
            methodName.append("get" + entry.getKey().substring(0, 1).toUpperCase() + entry.getKey().substring(1));
            method = clazz.getMethod(methodName.toString(), new Class[]{});
            Object resultValue = method.invoke(obj, new Object[]{});
            log.debug("Obj type: " + entry.getValue());

            if (entry.getValue().indexOf("List") != -1) {
                continue;
            }

            if (entry.getValue().indexOf("String") != -1
                    && !StringUtils.isEmpty(resultValue)
                    && resultValue.toString().indexOf("%") != -1) {
                predicates.add(criteriaBuilder.like(root.get(entry.getKey()), (String) resultValue));
            } else if (!StringUtils.isEmpty(resultValue)) {
                predicates.add(criteriaBuilder.equal(root.get(entry.getKey()), resultValue));
            }
        }
        return predicates;
    }

    private List<Predicate> getPredicateCustom(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) throws NoSuchMethodException, IllegalAccessException, ParseException, InvocationTargetException {
        List<Predicate> predicates = getPredicateDefault(root, criteriaQuery, criteriaBuilder);

        return predicates;
    }
}
