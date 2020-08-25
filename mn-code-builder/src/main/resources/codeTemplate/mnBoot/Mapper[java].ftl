package com.mn.${entityName?lower_case}.mapper;
import com.mn.${entityName?lower_case}.entity.po.${entityName};
import java.io.Serializable;

import com.mn.commonbean.tkmapper.MyMapper;
import com.mn.${entityName?lower_case}.entity.param.${entityName}Param;
import com.mn.${entityName?lower_case}.entity.po.${entityName};
import java.util.List;

/**
 * @ClassName:     ${entityName}Mapper.java
 * @Description:   ${tableDesc}的mapper执行类
 * 
 * @author         ${author}
 * @version        ${version}
 * @Date           ${currentTime}
 */
public interface ${entityName}Mapper extends MyMapper<${entityName}>{
    /**
     * 查询${tableDesc}记录
     * @param param
     * @return
     */
    List<${entityName}> list${entityName}(${entityName}Param param);

    /**
    * 新增${tableDesc}记录
    * @param ${entityName?uncap_first}
    */
    void insert${entityName}(${entityName} ${entityName?uncap_first});

    /**
    * 修改${tableDesc}记录
    * @param ${entityName?uncap_first}
    */
    void update${entityName}(${entityName} ${entityName?uncap_first});

    /**
    * 查询单条${tableDesc}记录
    * @param ${pkField.fieldName}
    * @return
    */
    ${entityName} get${entityName}(${pkField.simpleJavaType} ${pkField.fieldName});

    /**
    * 批量删除${tableDesc}记录
    * @param ids
    */
    void delete${entityName}(List<${pkField.simpleJavaType}> ids);
}