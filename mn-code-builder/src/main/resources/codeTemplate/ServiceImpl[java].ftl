package ${rootPath}.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ${rootPath}.service.${entityName}Service;
import com.jfb.dao.BasicDAO;
import com.jfb.model.${businesspckPath}.${entityName};
import com.jfb.search.${businesspckPath}.${entityName}Search;
import com.jfb.utils.page.Page;
import com.jfb.utils.page.Pageable;
import com.jfb.sys.exception.BusinessInvalidParamException;
import com.jfb.config.IdWorker;
import com.jfb.sys.service.common.BaseService;

/**
 * @ClassName:     ${entityName}ServiceImpl.java
 * @Description:   ${tableDesc}的服务实现
 * 
 * @author         ${author}
 * @version        V1.0  
 * @Date           ${currentTime}
 */
 
@Transactional
@Service("${entityName?uncap_first}Service")
public class ${entityName}ServiceImpl extends BaseService implements ${entityName}Service{
 
	@Resource
	private IdWorker idWorker;//主键生成器
	@Resource
	private BasicDAO<${entityName},${entityName}Search> ${entityName?uncap_first}Dao;
	 
	@Override
	public Page<${entityName}> findPage(Pageable pageable,${entityName}Search search) {
	
		long count = ${entityName?uncap_first}Dao.countResult(${entityName}.class, search);
		List<${entityName}> list = null;
		if(count == 0){
			list = new ArrayList<>();
		}else{
			list = ${entityName?uncap_first}Dao.listResults(${entityName}.class,search);
		}
		return new Page<>(list, count, pageable);
	}
		
	@Override
	public void save${entityName}(${entityName} ${entityName?uncap_first}) {
	
		if(${entityName?uncap_first} == null) {
			throw new BusinessInvalidParamException("参数有误");
		}
		${entityName?uncap_first}.set${pkField.fieldName?cap_first}(idWorker.nextId("业务前缀!"));
		${entityName?uncap_first}Dao.save(${entityName?uncap_first});
	}
		
	@Override
	public ${entityName} get${entityName}(${pkField.simpleJavaType} ${pkField.fieldName}) {
	
		if (StringUtils.isEmpty(${pkField.fieldName})) {
			return null;
		}
		return ${entityName?uncap_first}Dao.get(${pkField.fieldName}, ${entityName}.class);
	}
		
	@Override
	public void delete${entityName}(${pkField.simpleJavaType}[] ids) {
	
		if(ids == null || ids.length == 0) {
			throw new BusinessInvalidParamException("参数有误");
		}
		${entityName?uncap_first}Dao.deleteBatch(Arrays.asList(ids), ${entityName}.class);
	}
		
	@Override
	public void update${entityName}(${entityName} ${entityName?uncap_first}) {
		if(${entityName?uncap_first}== null) {
			throw new BusinessInvalidParamException("参数有误");
		}
		${entityName?uncap_first}Dao.update(${entityName?uncap_first});
	}
}