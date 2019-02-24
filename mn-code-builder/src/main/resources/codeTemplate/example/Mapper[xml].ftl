<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${entityName}Mapper">
  <resultMap id="${entityName?uncap_first}ResultMap" type="${entityName}">
    <#list columnList as table>
    <#if table.shiPk>
    <id column="${pkField.columnName}"  property="${pkField.fieldName}" />
    <#else> 
	<result column="${table.columnName}"  property="${table.fieldName}" />
    </#if>
	</#list>
  </resultMap>
  <sql id="allColumns">
  	<#list columnList as table>
		${table.columnName}<#if table_has_next>,</#if>
	</#list>
  </sql>
  
  <!-- 新增${tableDesc}的记录 -->
  <insert id="save${entityName}" parameterType="${entityName?uncap_first}">
    insert into ${tableName} (<include refid="allColumns" />)
    values (
    <#list columnList as table>
    	#${r"{"}${table.fieldName}${r"}"}<#if table_has_next>,</#if>
    </#list>
    )
  </insert>
  <!-- 批量新增 -->
  <insert id="saveBatch${entityName}" parameterType="java.util.List">
    insert into ${tableName} (<include refid="allColumns" />)
    values
    <foreach collection="list" item="item" separator=",">
    (
    <#list columnList as table>
    	#${r"{item."}${table.fieldName}${r"}"}<#if table_has_next>,</#if>
    </#list>
    )
    </foreach>
  </insert>
  
  <!--根据主键删除${tableDesc}的记录-->
  <delete id="delete${entityName}" parameterType="${pkField.simpleJavaType}">
    delete from ${tableName}
   	where ${pkField.columnName} = #${r"{"}${pkField.fieldName}${r"}"}
  </delete>
  <!-- 批量删除 -->
  <delete id="deleteBatch${entityName}" parameterType="java.util.List">
  	delete from ${tableName} where ${pkField.columnName} in 
		<foreach collection="list" index="index" item="item" open="(" separator="," close=")">
			#${r"{"}item${r"}"} 
		</foreach>
  </delete>
  
  <!-- 更新${tableDesc}的记录，空字段忽略 -->
  <update id="update${entityName}" parameterType="${entityName}">
    update ${tableName}
    <set>
      <#list columnList as table>
	      <if test="${table.fieldName} != null">
	        ${table.columnName} = #${r"{"}${table.fieldName}${r"}"},
	      </if>
      </#list>
    </set>
    where ${pkField.columnName} = #${r"{"}${pkField.fieldName}${r"}"}
  </update>
  
  <!--根据主键查询${tableDesc}的记录-->
  <select id="get${entityName?cap_first}" parameterType="${pkField.simpleJavaType}" resultMap="${entityName?uncap_first}ResultMap">
    select 
	    <include refid="allColumns" />
	    from ${tableName}
	    where ${pkField.columnName} = #${r"{"}${pkField.fieldName}${r"}"}
  </select>
  <!-- 分页 -->
  <select id="listResults${entityName}" parameterType="${entityName}Search" resultMap="${entityName?uncap_first}ResultMap">
    select 
      <include refid="allColumns" />
      from ${tableName}
      <if test="startIndex !=null and pageSize!=null">
		limit #${r"{"}startIndex${r"}"},#${r"{"}pageSize${r"}"}
	  </if>
  </select>
  <!-- 分页记录数 -->
  <select id="countResult${entityName}" parameterType="${entityName}Search" resultType="int">
    select count(${pkField.columnName}) from ${tableName}
  </select>
</mapper>