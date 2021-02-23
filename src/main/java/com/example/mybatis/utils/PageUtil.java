package com.example.mybatis.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname PageUtil
 * @Description TODO 分页工具类
 * @Date 2021/2/18 12:25 下午
 * @Author z7-x
 */
public class PageUtil {
    /**
     * @Description: 方法 getPage 的功能描述：TODD 分页
     * @Date 2021/2/23 10:17 上午
     */
    public static <T> Page<T> getPage(List<T> data, int page, int pageSize) {
        int startNum = page * pageSize;      // 起始位置
        int endNum = (page + 1) * pageSize;  // 结束位置
        if (CollectionUtils.isEmpty(data)) {
            return new PageImpl<>(new ArrayList<>(0), PageRequest.of(page, pageSize), 0);
        }
        if (startNum > data.size()) {
            return new PageImpl<>(new ArrayList<>(0), PageRequest.of(page, pageSize), data.size());
        }
        List<T> resultData = new ArrayList<>(pageSize);
        int limit = Math.min(endNum, data.size());
        for (int i = startNum ; i < limit; i ++) {
            resultData.add(data.get(i));
        }
        return new PageImpl<>(resultData, PageRequest.of(page, pageSize), data.size());
    }

    /**
     * @Description: 方法 getList 的功能描述：TODD 分页
     * @Date 2021/2/23 10:17 上午
     */
    public static <T> List<T> getList(List<T> data, int page, int pageSize) {
        int startNum = page * pageSize;      // 起始位置
        int endNum = (page + 1) * pageSize;  // 结束位置
        if (CollectionUtils.isEmpty(data)) {
            return new ArrayList<>(0);
        }
        if (startNum > data.size()) {
            return new ArrayList<>(0);
        }
        List<T> resultData = new ArrayList<>(pageSize);
        int limit = Math.min(endNum, data.size());
        for (int i = startNum ; i < limit; i ++) {
            resultData.add(data.get(i));
        }
        return resultData;
    }
}
