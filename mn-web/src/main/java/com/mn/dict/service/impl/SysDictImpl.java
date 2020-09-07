package com.mn.dict.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mn.commonbean.exception.BusinessException;
import com.mn.dict.entity.param.SysDictItemParam;
import com.mn.dict.entity.param.SysDictParam;
import com.mn.dict.entity.po.SysDict;
import com.mn.dict.entity.po.SysDictItem;
import com.mn.dict.mapper.SysDictMapper;
import com.mn.dict.service.SysDictService;
import com.mn.mnutil.PojoConvertUtil;
import com.mn.module.page.PageQuerier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("sysDictService")
public class SysDictImpl implements SysDictService {
    @Autowired
    private SysDictMapper sysDictMapper;

    /**
     * 分页查询列表
     * @param pageQuerierParam 分页查询参数
     * @return
     */
    @Override
    public PageInfo<SysDict> listPage(PageQuerier<SysDictParam> pageQuerierParam) {
        PageHelper.startPage(pageQuerierParam.getPage(),pageQuerierParam.getRows());
        return new PageInfo<SysDict>(sysDictMapper.listSysDict(pageQuerierParam.getSearch()));
    }

    /**
     * 查询列表[不分页]
     * @param param 查询参数
     * @return
     */
    @Override
    public List<SysDict> list(SysDictParam param) {
        return sysDictMapper.listSysDict(param);
    }

    /**
     * 新增记录
     * @param param
     */
    @Override
    @Transactional
    public void insert(SysDictParam param) {
        SysDict sysDict = PojoConvertUtil.convertPojo(param,SysDict.class);
        sysDictMapper.insertSysDict(sysDict);
    }

    /**
     * 修改记录
     * @param param
     */
    @Override
    @Transactional
    public void update(SysDictParam param) {
        sysDictMapper.updateSysDict(PojoConvertUtil.convertPojo(param,SysDict.class));
    }

    /**
     * 根据主键获取一条记录
     * @param id
     * @return
     */
    @Override
    public SysDict get(Long id) {
        return sysDictMapper.getSysDict(id);
    }

    /**
     * 批量删除记录
     * @param ids
     */
    @Override
    @Transactional
    public void delete(List<Long> ids) {
        if(ids == null || ids.isEmpty()){
            throw  new BusinessException("参数为空!");
        }
        for(Long id:ids){
            sysDictMapper.deleteSysDictItemByDictTypeId(id);
        }
        sysDictMapper.deleteSysDict(ids);
    }

    @Override
    public List<SysDictItem> listDictItem(Long dictTypeId) {
        return sysDictMapper.listDictItem(dictTypeId);
    }

    @Override
    @Transactional
    public void saveSysDictItem(List<SysDictItemParam> params) {
        sysDictMapper.deleteSysDictItemByDictTypeId(params.get(0).getDictTypeId());
        sysDictMapper.saveSysDictItem(PojoConvertUtil.convertPojos(params,SysDictItem.class));
    }
}
