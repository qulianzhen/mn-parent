package com.mn.dict.service;

import com.mn.dict.entity.po.SysDict;

import java.util.List;

public interface SysDictService {
    public List<SysDict> list();

    void save(SysDict sysDict);
}
