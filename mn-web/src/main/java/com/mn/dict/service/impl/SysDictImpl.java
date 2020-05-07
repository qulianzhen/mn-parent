package com.mn.dict.service.impl;

import com.mn.dict.entity.po.SysDict;
import com.mn.dict.mapper.SysDictMapper;
import com.mn.dict.service.SysDictService;
import com.mn.mnutil.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("sysDictService")
public class SysDictImpl implements SysDictService {
    @Autowired
    private SnowFlake snowFlake;
    @Autowired
    private SysDictMapper sysDictMapper;

    @Override
    public List<SysDict> list() {
        return sysDictMapper.selectAll();
    }

    @Override
    @Transactional
    public void save(SysDict sysDict) {
        sysDict.setId(snowFlake.nextId());
        int ss = sysDictMapper.insert(sysDict);
        System.out.println("=================:"+ss);
        int i = 1/0;
        System.out.println("==========出异常了！");
    }
}
