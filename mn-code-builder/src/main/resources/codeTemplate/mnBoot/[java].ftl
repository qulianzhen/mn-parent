package com.mn.${entityName?lower_case}.entity.po;
import java.io.Serializable;
<#list needImportPacageSet as importPacage>
import ${importPacage};
</#list>
/**
 * @ClassName:     ${entityName}.java
 * @Description:   ${tableDesc}的实体Bean
 * 
 * @author         ${author}
 * @version        ${version}
 * @Date           ${currentTime}
 */
public class ${entityName} implements Serializable {
	private static final long serialVersionUID = 1L;
<#list entityproPertyIgnoreDel as table>
	//${table.comments?if_exists}
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