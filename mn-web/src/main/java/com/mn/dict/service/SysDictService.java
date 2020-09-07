package com.mn.dict.service;

import com.github.pagehelper.PageInfo;
import com.mn.dict.entity.param.SysDictItemParam;
import com.mn.dict.entity.param.SysDictParam;
import com.mn.dict.entity.po.SysDict;
import com.mn.dict.entity.po.SysDictItem;
import com.mn.module.page.PageQuerier;

import java.util.List;

public interface SysDictService {

    /**
     * 分页查询列表
     * @param pageQuerierParam 分页查询参数
     * @return
     */
    public PageInfo<SysDict> listPage(PageQuerier<SysDictParam> pageQuerierParam);

    /**
     * 查询列表[不分页]
     * @param param 查询参数
     * @return
     */
    public List<SysDict> list(SysDictParam param);

    /**
     * 新增记录
     * @param param
     */
    void insert(SysDictParam param);

    /**
     * 修改记录
     * @param param
     */
    void update(SysDictParam param);

    /**
     * 根据主键获取一条记录
     * @param id 主键
     * @return
     */
    SysDict get(Long id);

    /**
     * 批量删除记录
     * @param ids 主键集合
     */
    void delete(List<Long> ids);

    /**
     * 根据字典ID查询字典项
     * @param dictTypeId 字典ID
     * @return
     */
    List<SysDictItem> listDictItem(Long dictTypeId);

    /**
     * 批量保存字典明细
     * @param params
     */
    void saveSysDictItem(List<SysDictItemParam> params);
}
