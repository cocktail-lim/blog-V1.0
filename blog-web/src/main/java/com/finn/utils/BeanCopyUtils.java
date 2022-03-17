package com.finn.utils;

import java.util.ArrayList;
import java.util.List;

/*
 * @description: 复制bean
 * @author: Finn
 * @create: 2022/03/07 22:40
 */
public class BeanCopyUtils {
    /*
    * @Description: 复制对象
    * @Param: [source, target]
    * @return: T
    * @Author: Finn
    * @Date: 2022/03/07 22:40
    */
    public static <T> T copyObject(Object source, Class<T> target) {
        T temp = null;
        try {
            temp = target.newInstance();
            if (null != source) {
                org.springframework.beans.BeanUtils.copyProperties(source, temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

    /*
    * @Description: 复制列表
    * @Param: [source, target]
    * @return: java.util.List<T>
    * @Author: Finn
    * @Date: 2022/03/07 22:41
    */
    public static <T, S> List<T> copyList(List<S> source, Class<T> target) {
        List<T> list = new ArrayList<>();
        if (null != source && source.size() > 0) {
            for (Object obj : source) {
                list.add(BeanCopyUtils.copyObject(obj, target));
            }
        }
        return list;
    }
}
