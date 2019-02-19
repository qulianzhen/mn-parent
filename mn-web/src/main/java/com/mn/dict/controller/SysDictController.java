package com.mn.dict.controller;

import com.mn.dict.entity.po.SysDict;
import com.mn.dict.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/sysDict")
public class SysDictController {
    @Autowired
    private SysDictService sysDictService;

    @GetMapping(value = "/list")
    public List<SysDict> list(){
        return sysDictService.list();
    }

    @PostMapping(value = "save")
    public void save(SysDict sysDict){
        sysDictService.save(sysDict);
    }

}
