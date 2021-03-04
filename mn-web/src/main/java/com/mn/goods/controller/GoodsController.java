package com.mn.goods.controller;

import com.mn.commonbean.restful.Message;
import com.mn.goods.entity.param.GoodsParam;
import com.mn.goods.entity.po.Goods;
import com.mn.goods.service.GoodsService;
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
 * @ClassName:     GoodsController.java
 * @Description:   商品管理的Controller层
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2021-03-05 00:18:38
 */
@RestController
@RequestMapping("/api/goods")
public class GoodsController{
	private static final Logger LOGGER = LoggerFactory.getLogger(GoodsController.class);
	
	@Autowired
	private GoodsService goodsService;
	@Autowired
    private SnowFlake snowFlake;
	
	/**
	 * @description 查询商品管理列表-分页
	 * @author qlz
     * @param param 分页查询参数
	 * @return 商品管理列表
	 */
	@PostMapping(value = "/listPage")
	public Message goodsList(@RequestBody PageQuerier<GoodsParam> param) {
	
		if(param == null){
			param = new PageQuerier<>();
		}
        return PageUtil.easyUIPageSuccessMsg(goodsService.listPage(param));
	}

	/**
	 * @description 查询商品管理列表-不分页
	 * @author qlz
     * @param param 查询参数
	 * @return 商品管理列表
	 */
	@PostMapping(value = "/list")
	public Message goodsList(@RequestBody GoodsParam param) {
		if(param == null){
			param = new GoodsParam();
		}
        return MessageUtil.successMsg(goodsService.list(param));
	}
	
	/**
	* @description 保存(新增/修改)[商品管理]
	* @author qlz
    * @param param 保存参数
	* @return 响应对象
	*/
	@PostMapping(value = "/save")
	public Message saveGoods(@RequestBody GoodsParam param) {
		if(param!=null &&(param.getId()==null || "".equals(param.getId()))){
            param.setId(snowFlake.nextId());
			goodsService.insert(param);
        }else{
			goodsService.update(param);
        }
        return MessageUtil.successMsg(param.getId());
	}
		
	
	/**
	* @description 批量删除商品管理
	* @author qlz
	* @param ids 参数:主键集合
	* @return 响应对象
	*/
	@PostMapping(value = "/delete")
	public Message deleteGoods(@RequestBody List<Long> ids) {
		goodsService.delete(ids);
		return MessageUtil.successMsg();
	}
	
	
	 /**
	* @description 获取单个商品管理
	* @author qlz
	* @param id 参数:主键
	* @return 响应对象
	*/
	@GetMapping(value = "/get")
	public Message getGoods(Long id) {
		Goods goods = goodsService.get(id);
		return MessageUtil.successMsg(goods);
	}
}