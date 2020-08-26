package com.mn.${entityName?lower_case}.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mn.commonbean.exception.BusinessException;
import com.mn.${entityName?lower_case}.entity.param.${entityName}Param;
import com.mn.${entityName?lower_case}.entity.po.${entityName};
import com.mn.${entityName?lower_case}.mapper.${entityName}Mapper;
import com.mn.${entityName?lower_case}.service.${entityName}Service;
import com.mn.mnutil.PojoConvertUtil;
import com.mn.module.page.PageQuerier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @ClassName:     ${entityName}ServiceImpl.java
 * @Description:   ${tableDesc}的服务实现
 * 
 * @author         ${author}
 * @version        ${version}
 * @Date           ${currentTime}
 */

@Service("${entityName?uncap_first}Service")
public class ${entityName}ServiceImpl implements ${entityName}Service{
 
	@Autowired
	private ${entityName}Mapper ${entityName?uncap_first}Mapper;
	 
	@Override
	public PageInfo<${entityName}> listPage(PageQuerier<${entityName}Param> pageQuerierParam) {
		PageHelper.startPage(pageQuerierParam.getPage(),pageQuerierParam.getRows());
        return new PageInfo<${entityName}>(${entityName?uncap_first}Mapper.list${entityName}(pageQuerierParam.getSearch()));
	}

	@Override
    public List<${entityName}> list(${entityName}Param param) {
    	return ${entityName?uncap_first}Mapper.list${entityName}(param);
    }

	@Override
    @Transactional
    public void insert(${entityName}Param param) {
		${entityName} ${entityName?uncap_first} = PojoConvertUtil.convertPojo(param,${entityName}.class);
		${entityName?uncap_first}Mapper.insert${entityName}(${entityName?uncap_first});
    }

	@Override
    @Transactional
    public void update(${entityName}Param param) {
		${entityName?uncap_first}Mapper.update${entityName}(PojoConvertUtil.convertPojo(param,${entityName}.class));
    }

		
	@Override
	public ${entityName} get(${pkField.simpleJavaType} ${pkField.fieldName}) {
	
		if (StringUtils.isEmpty(${pkField.fieldName})) {
			return null;
		}
		return ${entityName?uncap_first}Mapper.get${entityName}(${pkField.fieldName});
	}
		
	@Override
	public void delete(List<${pkField.simpleJavaType}> ids) {
	
		if(ids == null || ids.isEmpty()) {
			throw new BusinessInvalidParamException("参数有误");
		}
		${entityName?uncap_first}Mapper.delete${entityName}(ids);
	}
}