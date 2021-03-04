package com.mn.doubleball.service;

import com.github.pagehelper.PageInfo;
import com.mn.module.page.PageQuerier;
import com.mn.doubleball.entity.param.DoubleBallParam;
import com.mn.doubleball.entity.po.DoubleBall;
import java.util.List;
import java.util.Map;

/**
 * @ClassName:     DoubleBallService.java
 * @Description:   双色球历史记录的服务接口
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2020-10-21 01:22:54
 */
public interface DoubleBallService {
	
	/**
	* @description 分页查询双色球历史记录
	* @param pageQuerierParam 分页查询条件
	* @return
	* @author qlz
	*/
	public PageInfo<DoubleBall> listPage(PageQuerier<DoubleBallParam> pageQuerierParam);

	/**
	* @description 非分页查询双色球历史记录
	* @param param 查询条件
	* @return 对象集合
	* @author qlz
	*/
	public List<DoubleBall> list(DoubleBallParam param);
	
	/**
	* @description 新增双色球历史记录
	* @param param 双色球历史记录参数对象
	* @author qlz
	*/
	public void insert(DoubleBallParam param);

	/**
	* @description 修改双色球历史记录
	* @param param 双色球历史记录参数对象
	* @author qlz
	*/
	public void update(DoubleBallParam param);
	
	/**
	* @description 根据ID查找双色球历史记录
	* @param id  双色球历史记录的id 	
	* @return 双色球历史记录对象
	* @author qlz
	*/
	public DoubleBall get(Long id);
	
	
	/**
	* @description 删除双色球历史记录
	* @param ids  id集合 	
	* @author qlz
	*/
	public void delete(List<Long> ids);

	/**
	 * 获取该蓝色球下一个的各个球的几率
	 * @param dbBlue 蓝球号
	 * @return
	 */
	List<Map<Integer, Object>> singleBallAnalyse(Integer dbBlue);

	/**
	 * 求相似曲线
	 * @param range 几个球
	 * @return
	 */
	Map<String,Object> getSimilarity(Integer range);
}