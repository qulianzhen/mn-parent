package com.mn.${entityName?lower_case}.service;

import com.github.pagehelper.PageInfo;
import com.mn.module.page.PageQuerier;
import com.mn.${entityName?lower_case}.entity.param.${entityName}Param;
import com.mn.${entityName?lower_case}.entity.po.${entityName};
import java.util.List;

/**
 * @ClassName:     ${entityName}Service.java
 * @Description:   ${tableDesc}的服务接口
 * 
 * @author         ${author}
 * @version        ${version}
 * @Date           ${currentTime}
 */
public interface ${entityName}Service {
	
	/**
	* @description 分页查询${tableDesc}
	* @param pageQuerierParam 分页查询条件
	* @return
	* @author ${author}
	*/
	public PageInfo<${entityName}> listPage(PageQuerier<${entityName}Param> pageQuerierParam);

	/**
	* @description 非分页查询${tableDesc}
	* @param param 查询条件
	* @return 对象集合
	* @author ${author}
	*/
	public List<${entityName}> list(${entityName}Param param);
	
	/**
	* @description 新增${tableDesc}
	* @param param ${tableDesc}参数对象
	* @author ${author}
	*/
	public void insert(${entityName}Param param);

	/**
	* @description 修改${tableDesc}
	* @param param ${tableDesc}参数对象
	* @author ${author}
	*/
	public void update(${entityName}Param param);
	
	/**
	* @description 根据ID查找${tableDesc}
	* @param ${pkField.fieldName}  ${tableDesc}的id 	
	* @return ${tableDesc}对象
	* @author ${author}
	*/
	public ${entityName} get(${pkField.simpleJavaType} ${pkField.fieldName});
	
	
	/**
	* @description 删除${tableDesc}
	* @param ids  id集合 	
	* @author ${author}
	*/
	public void delete(List<${pkField.simpleJavaType}> ids);
}