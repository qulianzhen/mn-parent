package ${rootPath}.service;

import com.jfb.model.${businesspckPath}.${entityName};
import com.jfb.search.${businesspckPath}.${entityName}Search;
import com.jfb.utils.page.Page;
import com.jfb.utils.page.Pageable;

/**
 * @ClassName:     ${entityName}Service.java
 * @Description:   ${tableDesc}的服务接口
 * 
 * @author         ${author}
 * @version        V1.0  
 * @Date           ${currentTime}
 */
public interface ${entityName}Service {
	
	/**
	* @description 分页查询${tableDesc}
	* @param pageable 分页信息
	* @param search 查询条件
	* @return 对象集合
	* @author ${author}
	*/
	Page<${entityName}> findPage(Pageable pageable,${entityName}Search search);
	
	/**
	* @description 新增${tableDesc}
	* @param ${entityName?uncap_first} ${tableDesc}对象
	* @author ${author}
	*/
	void save${entityName}(${entityName} ${entityName?uncap_first});
	
	/**
	* @description 根据ID查找${tableDesc}
	* @param ${pkField.fieldName}  ${tableDesc}的id 	
	* @return ${tableDesc}对象
	* @author ${author}
	*/
	${entityName} get${entityName}(${pkField.simpleJavaType} ${pkField.fieldName});
	
	
	/**
	* @description 删除${tableDesc}
	* @param ids  id集合 	
	* @author ${author}
	*/
	void delete${entityName}(${pkField.simpleJavaType}[] ids);
	
	
	/**
	* @description 修改${tableDesc}
	* @param ${entityName?uncap_first} ${tableDesc}对象
	* @author ${author}
	*/
	void update${entityName}(${entityName} ${entityName?uncap_first});
}