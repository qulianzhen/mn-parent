package com.mn.util;

import com.github.pagehelper.PageInfo;
import com.mn.commonbean.restful.Message;
import com.mn.mnutil.MessageUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: (描述)
 * @Author:Mloong
 * @Date :create in 2020/6/25-21:58
 * @Version 1.0
 * @Modified By:
 */
public  class PageUtil {
    public  static <T> Message easyUIPageSuccessMsg(PageInfo<T> pageInfo){
        Map<String,Object> pageMap = new HashMap<>();
        pageMap.put("total",pageInfo.getTotal());
        pageMap.put("rows",pageInfo.getList());
        return MessageUtil.successMsg(pageMap);
    }
}
