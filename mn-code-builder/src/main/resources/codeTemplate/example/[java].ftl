package com.jfb.model.${businesspckPath};
import java.io.Serializable;
<#list needImportPacageSet as importPacage>
import ${importPacage};
</#list>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * @ClassName:     ${entityName}.java
 * @Description:   ${tableDesc}的实体Bean
 * 
 * @author         ${author}
 * @version        ${version}
 * @Date           ${currentTime}
 */
@ApiModel(value="${tableDesc}Model",description="${tableDesc}对象数据模型") 
public class ${entityName} implements Serializable {
	private static final long serialVersionUID = 1L;
<#list entityproPertyIgnoreDel as table>
	//${table.comments?if_exists}
	<#if swaggerProIgnore?seq_contains(table.fieldName)>
	@ApiModelProperty(hidden=true)
	<#else>
	@ApiModelProperty(notes = "${table.comments?if_exists}")
	</#if>
	private ${table.simpleJavaType} ${table.fieldName};
</#list>
	//set get method
<#list entityproPertyIgnoreDel as table>
	public ${table.simpleJavaType} get${table.fieldName?cap_first}(){
		return ${table.fieldName};
	}
	
	public void set${table.fieldName?cap_first}(${table.simpleJavaType} ${table.fieldName}){
		this.${table.fieldName} = ${table.fieldName};
	}
	
</#list>
}