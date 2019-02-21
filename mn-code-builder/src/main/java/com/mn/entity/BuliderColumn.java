package com.mn.entity;
/**
 * @ClassName:     BuliderColumn.java
 * @Description:   TODO(用一句话描述该文件做什么) 
 * 
 * @author         33107
 * @version        V1.0  
 * @Date           2016年6月13日 上午10:54:55 
 */
public class BuliderColumn {
	//数据库字段名
	private String columnName;
	//数据库字段类型
	private String dataType;
	//数据库字段注释
	private String comments;
	//数据库字段长度
	private Integer dataLength;
	
	//是否是主键
	private boolean shiPk;
	//实体类中的属性名
	private String fieldName;
	//实体类中的属性的类型，如java.lang.Long
	private String javaType;
	//实体类中的属性的类型的简称,如Long
	private String simpleJavaType;
	//实体类中的属性的JDBC类型(mybatis xml文件中用),如NUMERIC,这些类型定义在java.sql.JDBCType中
	private String jdbcType;
	private String nullable;//是否可以为null： NO 不能为空  YES  可以为空
	
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Integer getDataLength() {
		return dataLength;
	}
	public void setDataLength(Integer dataLength) {
		this.dataLength = dataLength;
	}
	
	
	
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getJavaType() {
		return javaType;
	}
	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}
	public boolean isShiPk() {
		return shiPk;
	}
	public void setShiPk(boolean shiPk) {
		this.shiPk = shiPk;
	}
	public String getSimpleJavaType() {
		return simpleJavaType;
	}
	public void setSimpleJavaType(String simpleJavaType) {
		this.simpleJavaType = simpleJavaType;
	}
	
	public String getJdbcType() {
		return jdbcType;
	}
	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}
	@Override
	public String toString() {
		return "Table [columnName=" + columnName + ", dataType=" + dataType
				+ ", comments=" + comments + ", dataLength=" + dataLength
				+ ", shiPk=" + shiPk + ", fieldName=" + fieldName
				+ ", javaType=" + javaType + ", simpleJavaType="
				+ simpleJavaType + ", jdbcType=" + jdbcType + "]";
	}
	public String getNullable() {
		return nullable;
	}
	public void setNullable(String nullable) {
		this.nullable = nullable;
	}
	
	
}
