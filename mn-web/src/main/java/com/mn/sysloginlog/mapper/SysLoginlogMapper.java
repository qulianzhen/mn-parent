package com.mn.sysloginlog.mapper;
import com.mn.commonbean.tkmapper.MyMapper;
import com.mn.sysloginlog.entity.param.SysLoginlogParam;
import com.mn.sysloginlog.entity.po.SysLoginlog;

import java.util.List;

/**
 * @ClassName:     SysLoginlogMapper.java
 * @Description:   登录日志的mapper执行类
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2020-10-07 17:32:43
 */
public interface SysLoginlogMapper extends MyMapper<SysLoginlog>{
    /**
     * 查询登录日志记录
     * @param param
     * @return
     */
    List<SysLoginlog> listSysLoginlog(SysLoginlogParam param);

    /**
    * 新增登录日志记录
    * @param sysLoginlog
    */
    void insertSysLoginlog(SysLoginlog sysLoginlog);
}