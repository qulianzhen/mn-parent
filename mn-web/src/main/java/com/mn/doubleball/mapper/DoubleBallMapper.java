package com.mn.doubleball.mapper;
import com.mn.commonbean.tkmapper.MyMapper;
import com.mn.doubleball.entity.param.DoubleBallParam;
import com.mn.doubleball.entity.po.DoubleBall;

import java.util.List;
import java.util.Map;

/**
 * @ClassName:     DoubleBallMapper.java
 * @Description:   双色球历史记录的mapper执行类
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2020-10-21 01:22:54
 */
public interface DoubleBallMapper extends MyMapper<DoubleBall>{
    /**
     * 查询双色球历史记录记录
     * @param param
     * @return
     */
    List<DoubleBall> listDoubleBall(DoubleBallParam param);

    /**
    * 新增双色球历史记录记录
    * @param doubleBall
    */
    void insertDoubleBall(DoubleBall doubleBall);

    /**
    * 修改双色球历史记录记录
    * @param doubleBall
    */
    void updateDoubleBall(DoubleBall doubleBall);

    /**
    * 查询单条双色球历史记录记录
    * @param id
    * @return
    */
    DoubleBall getDoubleBall(Long id);

    /**
    * 批量删除双色球历史记录记录
    * @param ids
    */
    void deleteDoubleBall(List<Long> ids);

    /**
     * 获取该蓝色球下一个的各个球的个数
     * @param dbBlue
     * @return
     */
    List<Map<String,Object>> queryNextNoBlue(Integer dbBlue);
}