<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.mn.${entityName?lower_case}.mapper.${entityName}Mapper">

  <sql id="tbl_columns">
  	<#list columnList as table>
		${table.columnName}<#if table_has_next>,</#if>
	</#list>
  </sql>


    <!-- 查询${tableDesc}列表  -->
    <select id="list${entityName}" resultType="${entityName}" parameterType="${entityName}Param">
        select
        <include refid="tbl_columns"/>
        from ${tableName}
        where 1=1
    </select>

    <!-- 获取单条${tableDesc}记录 -->
    <select id="get${entityName}" resultType="${entityName}" parameterType="${pkField.simpleJavaType}">
        select
        <include refid="tbl_columns"/>
        from ${tableName}
        where ${pkField.columnName} = #${r"{"}${pkField.fieldName}${r"}"}
    </select>


    <!-- 新增${tableDesc}记录 -->
    <insert id="insert${entityName}" parameterType="${entityName}">
        insert into ${tableName}(<include refid="tbl_columns"/>) values(
        <#list columnList as table>
    	    #${r"{"}${table.fieldName}${r"}"}<#if table_has_next>,</#if>
        </#list>
        )
    </insert>

    <!-- 修改${tableDesc}记录 -->
    <insert id="update${entityName}" parameterType="${entityName}">
        update ${tableName}
        <set>
        <#list columnList as table>
          <if test="${table.fieldName} != null">
              ${table.columnName} = #${r"{"}${table.fieldName}${r"}"},
          </if>
        </#list>
        </set>
        where ${pkField.columnName} = #${r"{"}${pkField.fieldName}${r"}"}
    </insert>

    <!-- 删除${tableDesc}记录 -->
    <delete id="delete${entityName}" parameterType="java.util.List">
        delete from  ${tableName}
        where ${pkField.columnName} in
        <foreach collection="list" item="item" open="(" close=")" separator=",">
        #${r"{"}item${r"}"}
        </foreach>
    </delete>
</mapper>