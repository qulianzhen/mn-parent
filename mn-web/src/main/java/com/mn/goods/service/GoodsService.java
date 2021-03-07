package com.mn.goods.service;

import com.github.pagehelper.PageInfo;
import com.mn.goods.entity.param.GoodsParam;
import com.mn.goods.entity.po.Goods;
import com.mn.module.page.PageQuerier;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @ClassName:     GoodsService.java
 * @Description:   商品管理的服务接口
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2021-03-05 00:18:38
 */
public interface GoodsService {
	
	/**
	* @description 分页查询商品管理
	* @param pageQuerierParam 分页查询条件
	* @return
	* @author qlz
	*/
	public PageInfo<Goods> listPage(PageQuerier<GoodsParam> pageQuerierParam);

	/**
	* @description 非分页查询商品管理
	* @param param 查询条件
	* @return 对象集合
	* @author qlz
	*/
	public List<Goods> list(GoodsParam param);
	
	/**
	* @description 新增商品管理
	* @param param 商品管理参数对象
	* @author qlz
	*/
	public void insert(GoodsParam param);

	/**
	* @description 修改商品管理
	* @param param 商品管理参数对象
	* @author qlz
	*/
	public void update(GoodsParam param);
	
	/**
	* @description 根据ID查找商品管理
	* @param id  商品管理的id 	
	* @return 商品管理对象
	* @author qlz
	*/
	public Goods get(Long id);
	
	
	/**
	* @description 删除商品管理
	* @param ids  id集合 	
	* @author qlz
	*/
	public void delete(List<Long> ids);

	/**
	 * 上传图片，保存商品
	 * @param multipartFiles
	 * @param param
	 * @return
	 */
    List<String> fileUpload(MultipartFile[] multipartFiles, GoodsParam param);

	/**
	 * 获取商品新的编号
	 * @return
	 */
	String getNewGoodsNo();
}