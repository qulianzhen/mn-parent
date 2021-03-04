package com.mn.goods.mapper;
import com.mn.goods.entity.po.Goods;
import java.io.Serializable;

import com.mn.commonbean.tkmapper.MyMapper;
import com.mn.goods.entity.param.GoodsParam;
import com.mn.goods.entity.po.Goods;
import java.util.List;

/**
 * @ClassName:     GoodsMapper.java
 * @Description:   商品管理的mapper执行类
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2021-03-05 00:18:38
 */
public interface GoodsMapper extends MyMapper<Goods>{
    /**
     * 查询商品管理记录
     * @param param
     * @return
     */
    List<Goods> listGoods(GoodsParam param);

    /**
    * 新增商品管理记录
    * @param goods
    */
    void insertGoods(Goods goods);

    /**
    * 修改商品管理记录
    * @param goods
    */
    void updateGoods(Goods goods);

    /**
    * 查询单条商品管理记录
    * @param id
    * @return
    */
    Goods getGoods(Long id);

    /**
    * 批量删除商品管理记录
    * @param ids
    */
    void deleteGoods(List<Long> ids);
}