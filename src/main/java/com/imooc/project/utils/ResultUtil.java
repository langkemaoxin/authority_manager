package com.imooc.project.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 抽取一个公共方法，用来封装返回结果
 *
 */
public class ResultUtil {

    public static R<Map<String, Object>> buildPageR(IPage<?> page) {

        HashMap<String, Object> data = new HashMap<>();
        data.put("count", page.getTotal());
        data.put("records", page.getRecords());

        return R.ok(data);
    }
}
