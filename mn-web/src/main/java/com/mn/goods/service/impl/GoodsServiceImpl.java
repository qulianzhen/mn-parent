package com.mn.goods.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mn.commonbean.exception.BusinessInvalidParamException;
import com.mn.goods.entity.param.GoodsParam;
import com.mn.goods.entity.po.Goods;
import com.mn.goods.mapper.GoodsMapper;
import com.mn.goods.service.GoodsService;
import com.mn.mnutil.PojoConvertUtil;
import com.mn.module.page.PageQuerier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @ClassName:     GoodsServiceImpl.java
 * @Description:   商品管理的服务实现
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2021-03-05 00:18:38
 */

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService{
 
	@Autowired
	private GoodsMapper goodsMapper;
	 
	@Override
	public PageInfo<Goods> listPage(PageQuerier<GoodsParam> pageQuerierParam) {
		PageHelper.startPage(pageQuerierParam.getPage(),pageQuerierParam.getRows());
        return new PageInfo<Goods>(goodsMapper.listGoods(pageQuerierParam.getSearch()));
	}

	@Override
    public List<Goods> list(GoodsParam param) {
    	return goodsMapper.listGoods(param);
    }

	@Override
    @Transactional
    public void insert(GoodsParam param) {
		Goods goods = PojoConvertUtil.convertPojo(param,Goods.class);
		goodsMapper.insertGoods(goods);
    }

	@Override
    @Transactional
    public void update(GoodsParam param) {
		goodsMapper.updateGoods(PojoConvertUtil.convertPojo(param,Goods.class));
    }

		
	@Override
	public Goods get(Long id) {
	
		if (StringUtils.isEmpty(id)) {
			return null;
		}
		return goodsMapper.getGoods(id);
	}
		
	@Override
	public void delete(List<Long> ids) {
	
		if(ids == null || ids.isEmpty()) {
			throw new BusinessInvalidParamException("参数有误");
		}
		goodsMapper.deleteGoods(ids);
	}
}