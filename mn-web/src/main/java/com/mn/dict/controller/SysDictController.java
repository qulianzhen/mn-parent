package com.mn.dict.controller;

import com.mn.commonbean.restful.Message;
import com.mn.dict.entity.param.SysDictParam;
import com.mn.dict.service.SysDictService;
import com.mn.mnutil.MessageUtil;
import com.mn.mnutil.SnowFlake;
import com.mn.module.page.PageQuerier;
import com.mn.util.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @ClassName:     SysDictController.java
 * @Description:   字典管理的Controller层
 *
 * @author         qlz
 * @version        1.0.0
 * @Date           20200720 10:40:15
 */
@RestController
@RequestMapping(value = "/api/sysDict")
public class SysDictController {
    private Logger logger = LoggerFactory.getLogger(SysDictController.class);

    @Autowired
    private SysDictService sysDictService;
    @Autowired
    private SnowFlake snowFlake;

    /**
     * 查询字典列表[分页]
     * @param param 分页查询参数
     * @return
     */
    @PostMapping(value = "/listPage")
    public Message listPageSysDict(@RequestBody PageQuerier<SysDictParam> param){
        if(param == null){
            param = new PageQuerier<SysDictParam>();
        }
        return PageUtil.easyUIPageSuccessMsg(sysDictService.listPage(param));
    }


    /**
     * 查询字典列表[不分页]
     * @param param 查询参数
     * @return
     */
    @PostMapping(value = "/list")
    public Message listSysDict(@RequestBody SysDictParam param){
        if(param == null){
            param = new SysDictParam();
        }
        return MessageUtil.successMsg(sysDictService.list(param));
    }

    /**
     * 保存字典
     * @param param 保存参数
     * @return
     */
    @PostMapping(value = "/save")
    public Message saveSysDict(@RequestBody SysDictParam param){
        if(param!=null &&(param.getId()==null || "".equals(param.getId()))){
            param.setId(snowFlake.nextId());
            sysDictService.insert(param);
        }else{
            sysDictService.update(param);
        }
        return MessageUtil.successMsg(param.getId());
    }

    /**
     * 获取字典
     * @param id 参数:主键
     * @return
     */
    @GetMapping(value = "/get")
    public Message getSysDict(Long id){
        return MessageUtil.successMsg(sysDictService.get(id));
    }

    /**
     * 删除字典
     * @param ids 参数:主键集合
     * @return
     */
    @PostMapping(value = "/delete")
    public Message getSysDict(@RequestBody List<Long> ids){
        sysDictService.delete(ids);
        return MessageUtil.successMsg();
    }

}
