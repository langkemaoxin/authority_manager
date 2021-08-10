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

    /**
     * 成功或者失败的响应消息
     * @param success
     * @return
     */
    public static R<Object> buildR(boolean success){
        if(success){
            return R.ok(null);
        }

        return R.failed("操作失败");
    }
}
