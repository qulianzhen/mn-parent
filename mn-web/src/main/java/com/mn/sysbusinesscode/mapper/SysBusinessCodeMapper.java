package com.mn.sysbusinesscode.mapper;
import com.mn.commonbean.tkmapper.MyMapper;
import com.mn.sysbusinesscode.entity.param.SysBusinessCodeParam;
import com.mn.sysbusinesscode.entity.po.SysBusinessCode;

import java.util.List;

/**
 * @ClassName:     SysBusinessCodeMapper.java
 * @Description:   业务流水号的mapper执行类
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2020-08-26 23:52:45
 */
public interface SysBusinessCodeMapper extends MyMapper<SysBusinessCode>{
    /**
     * 查询业务流水号记录
     * @param param
     * @return
     */
    List<SysBusinessCode> listSysBusinessCode(SysBusinessCodeParam param);

    /**
    * 新增业务流水号记录
    * @param sysBusinessCode
    */
    void insertSysBusinessCode(SysBusinessCode sysBusinessCode);

    /**
    * 修改业务流水号记录
    * @param sysBusinessCode
    */
    void updateSysBusinessCode(SysBusinessCode sysBusinessCode);

    /**
    * 查询单条业务流水号记录
    * @param id
    * @return
    */
    SysBusinessCode getSysBusinessCode(Long id);

    /**
    * 批量删除业务流水号记录
    * @param ids
    */
    void deleteSysBusinessCode(List<Long> ids);

    void updateCurrentVal(int i, Long id);
}