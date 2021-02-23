package com.example.mybatis.utils;

import org.apache.commons.lang.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;


/**
 * @Classname DataConversionUtil
 * @Description TODO 此工具类用于处理各类数据转换
 * @Date 2021/2/19 2:11 下午
 * @Author z7-x
 */
public class ConversionUtil {

    /**
     * @Description: 方法 listConvert 的功能描述：TODD List<T>类型 转 List<Map<String,Object>>类型
     * @Return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @Date 2021/2/19 2:11 下午
     */
    public static <T> List<Map<String, Object>> setList(List<T> list) {
        List<Map<String, Object>> list_map = new ArrayList<Map<String, Object>>();
        try {
            for (T t : list) {
                Field[] fields = t.getClass().getDeclaredFields();
                Map<String, Object> m = new HashMap<String, Object>();
                for (Field field : fields) {
                    String keyName = field.getName();
                    PropertyDescriptor pd = new PropertyDescriptor(keyName, t.getClass());
                    Method getMethod = pd.getReadMethod();// 获得getter方法
                    Object o = getMethod.invoke(t);// 执行get方法返回一个Object
                    m.put(keyName, o);
                }
                list_map.add(m);
            }
            return list_map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description: 方法 setList 的功能描述：TODD List<Map<String, Object>> 到 List<T> 数据转换
     * @Return java.util.List<T>
     * @Date 2021/2/19 2:21 下午
     */
    public static <T> List<T> setList(List<Map<String, Object>> srcList, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        srcList.forEach(x -> {
            try {
                T t = clazz.newInstance();
                Field[] fields = t.getClass().getDeclaredFields();
                for (Field field : fields) {
                    if (!"serialVersionUID".equals(field.getName())) {
                        //设置对象的访问权限，保证对private的属性的访问
                        field.setAccessible(true);
                        //读取配置转换字段名，并从map中取出数据
                        Object v = x.get(field.getName());
                        field.set(t, convert(v, field.getType()));
                    }
                }
                list.add(t);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });
        return list;
    }

    /**
     * Field类型转换
     */
    private static <T> T convert(Object obj, Class<T> type) {
        if (obj != null && StringUtils.isNotBlank(obj.toString())) {
            if (type.equals(String.class)) {
                return (T) obj.toString();
            } else if (type.equals(BigDecimal.class)) {
                return (T) new BigDecimal(obj.toString());
            }
            //其他类型转换......
        }
        return null;
    }

    /**
     * @Description: 方法 getAgeByBirth 的功能描述：TODD 传入Date类型 转 int类型
     * @Return int 返回具体年龄
     * @Date 2021/2/19 2:14 下午
     */
    public static int setDate(Date birthDay) throws ParseException {
        int age = 0;
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) { //出生日期晚于当前时间，无法计算
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);  //当前年份
        int monthNow = cal.get(Calendar.MONTH);  //当前月份
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); //当前日期
        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        age = yearNow - yearBirth;   //计算整岁数
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;//当前日期在生日之前，年龄减一
            } else {
                age--;//当前月份在生日之前，年龄减一
            }
        }
        return age;
    }

}
