package com.mn.dict.mapper;

import com.mn.commonbean.tkmapper.MyMapper;
import com.mn.dict.entity.param.SysDictParam;
import com.mn.dict.entity.po.SysDict;

import java.util.List;

public interface SysDictMapper extends MyMapper<SysDict> {

    /**
     * 查询字典记录
     * @param search
     * @return
     */
    List<SysDict> listSysDict(SysDictParam search);

    /**
     * 新增字典记录
     * @param sysDict
     */
    void insertSysDict(SysDict sysDict);

    /**
     * 修改字典记录
     * @param sysDict
     */
    void updateSysDict(SysDict sysDict);

    /**
     * 查询单条字典记录
     * @param id
     * @return
     */
    SysDict getSysDict(Long id);

    /**
     * 批量删除字典记录
     * @param ids
     */
    void deleteSysDict(List<Long> ids);
}
