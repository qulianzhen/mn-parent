package com.mn.sysbusinesscode.service;

import com.github.pagehelper.PageInfo;
import com.mn.module.page.PageQuerier;
import com.mn.sysbusinesscode.entity.param.SysBusinessCodeParam;
import com.mn.sysbusinesscode.entity.po.SysBusinessCode;
import com.mn.sysbusinesscode.entity.vo.SysBusinessCodeVo;

import java.util.List;

/**
 * @ClassName:     SysBusinessCodeService.java
 * @Description:   业务流水号的服务接口
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2020-08-26 23:52:45
 */
public interface SysBusinessCodeService {
	
	/**
	* @description 分页查询业务流水号
	* @param pageQuerierParam 分页查询条件
	* @return
	* @author qlz
	*/
	public PageInfo<SysBusinessCodeVo> listPage(PageQuerier<SysBusinessCodeParam> pageQuerierParam);

	/**
	* @description 非分页查询业务流水号
	* @param param 查询条件
	* @return 对象集合
	* @author qlz
	*/
	public List<SysBusinessCode> list(SysBusinessCodeParam param);
	
	/**
	* @description 新增业务流水号
	* @param param 业务流水号参数对象
	* @author qlz
	*/
	public void insert(SysBusinessCodeParam param);

	/**
	* @description 修改业务流水号
	* @param param 业务流水号参数对象
	* @author qlz
	*/
	public void update(SysBusinessCodeParam param);
	
	/**
	* @description 根据ID查找业务流水号
	* @param id  业务流水号的id 	
	* @return 业务流水号对象
	* @author qlz
	*/
	public SysBusinessCode get(Long id);
	
	
	/**
	* @description 删除业务流水号
	* @param ids  id集合 	
	* @author qlz
	*/
	public void delete(List<Long> ids);


	/**
	 * 获取下一个业务号
	 * @param codeTag 业务号标志
	 * @return
	 */
	public String getNextCode(String codeTag);
}