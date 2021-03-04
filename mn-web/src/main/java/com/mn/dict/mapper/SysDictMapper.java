package com.mn.dict.mapper;

import com.mn.commonbean.tkmapper.MyMapper;
import com.mn.dict.entity.param.SysDictParam;
import com.mn.dict.entity.po.SysDict;
import com.mn.dict.entity.po.SysDictItem;

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

    /**
     * 根据字典ID查询字典明细
     * @param dictTypeId
     * @return
     */
    List<SysDictItem> listDictItem(Long dictTypeId);

    /**
     * 批量保存字典明细
     * @param sysDictItems
     */
    void saveSysDictItem(List<SysDictItem> sysDictItems);

    /**
     * 根据字典ID删除字典项
     * @param dictTypeId
     */
    void deleteSysDictItemByDictTypeId(Long dictTypeId);

    /**
     * 根据字典key查询字典项目信息
     * @param dictType
     * @return
     */
    List<SysDictItem> listDictItemByKey(String dictType);
}
