package com.mn.dict.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mn.commonbean.exception.BusinessException;
import com.mn.dict.entity.param.SysDictItemParam;
import com.mn.dict.entity.param.SysDictParam;
import com.mn.dict.entity.po.SysDict;
import com.mn.dict.entity.po.SysDictItem;
import com.mn.dict.entity.vo.SysDictItemSimpleVo;
import com.mn.dict.mapper.SysDictMapper;
import com.mn.dict.service.SysDictService;
import com.mn.mnutil.PojoConvertUtil;
import com.mn.mnutil.StringUtil;
import com.mn.module.page.PageQuerier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
    public synchronized void insert(SysDictParam param) {
        SysDict sysDict = PojoConvertUtil.convertPojo(param,SysDict.class);
        /*校验类型标志是否重复*/
        SysDictParam queryParam = new SysDictParam();
        queryParam.setDictType(param.getDictType());
        List<SysDict> dictList = sysDictMapper.listSysDict(queryParam);
        if(dictList!=null && !dictList.isEmpty()){
            throw new BusinessException("该字典类型已经存在!");
        }
        sysDictMapper.insertSysDict(sysDict);
    }

    /**
     * 修改记录(修改了字典类型标志，需要清理缓存)
     * @param param
     */
    @Override
    @Transactional
    @CacheEvict(cacheNames="MN_USER_DICT",allEntries=true)
    public synchronized void update(SysDictParam param) {

        /*校验类型标志是否重复*/
        SysDictParam queryParam = new SysDictParam();
        queryParam.setDictType(param.getDictType());
        queryParam.setId(param.getId());
        queryParam.setNotThisIdFlag(1);
        List<SysDict> dictList = sysDictMapper.listSysDict(queryParam);
        if(dictList!=null && !dictList.isEmpty()){
            throw new BusinessException("该字典类型已经存在!");
        }

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
    @CacheEvict(cacheNames="MN_USER_DICT",allEntries=true)
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
    @CacheEvict(cacheNames="MN_USER_DICT",key="#params[0].dictType")
    public void saveSysDictItem(List<SysDictItemParam> params) {
        sysDictMapper.deleteSysDictItemByDictTypeId(params.get(0).getDictTypeId());
        sysDictMapper.saveSysDictItem(PojoConvertUtil.convertPojos(params,SysDictItem.class));
    }

    @Override
    @Cacheable(cacheNames="MN_USER_DICT", key="#dictType")
    public List<SysDictItemSimpleVo> listSysDictItemByKey(String dictType) {
        if(StringUtil.isEmpty(dictType)){
            throw new BusinessException("字典Key不能为空!");
        }
        List<SysDictItem> itemList = sysDictMapper.listDictItemByKey(dictType);
        return PojoConvertUtil.convertPojos(itemList,SysDictItemSimpleVo.class);
    }
}
