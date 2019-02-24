package com.mn.entity;

import java.util.Map;

/**
 * @Description: 配置类
 * @Author:Mloong
 * @Date :create in 2019/2/24-0:57
 * @Version 1.0
 * @Modified By:
 */
public class BuilderConfig {

    /**
     * 模板.ftl 对应的生成路径是怎样的，如 [java].ftl = com.mn.dict.entity.po ; Mapper[xml].ftl=mapper/dict 等
     */
    private Map<String,String> ftlVsFilePathMap;

    /**
     * 生成实体类的时候，
     * 忽略的bean属性，如 "creater","modifier","isDeleted"
     * (因为有的时候，会把公共字段提取成父类)
     */
    private String[] ignoreBeanProperty;

    /**
     * 生成swagger注解
     */
    private boolean generateSwaggerAnnotation = true;

    /**
     * swagger的数据Model中需要隐藏的字段,比如creater，会在生成的Bean的creater字段上加
     * 上@ApiModelProperty(hidden=true)注解
     */
    private String[] swaggerDataModelIgnoreProp;

    /**
     * 是否生成lombok注解(暂时不支持)
     */
    private boolean generateLombokAnnotation = false;

    /**
     * 数据库类型:mysql oracle
     */
    private DbTypeEnum dbType;

    /**
     * 列名转Bean属性名的方式: 驼峰 or 原样
     */
    private ColumnTransformTypeEnum columnTrans;

    /**
     * 生成代码的位置
     */
    private String toPlace;

    /**
     * 作者
     */
    private String author;
    /**
     * 版本
     */
    private String version;

    /**
     * 表名-数组，与实体类名相对应
     */
    private String[] tableNames;
    /**
     * 实体名-数组，与表名相对应
     */
    private String[] entityNames;

    /**
     * 生成查询类所包含的查询字段（数据库列名）
     */
    private String[] searchColumns;

    /**
     * 模板位置
     */
    private String ftlLocationPath;


    public String[] getIgnoreBeanProperty() {
        return ignoreBeanProperty;
    }

    public void setIgnoreBeanProperty(String[] ignoreBeanProperty) {
        this.ignoreBeanProperty = ignoreBeanProperty;
    }

    public boolean isGenerateSwaggerAnnotation() {
        return generateSwaggerAnnotation;
    }

    public void setGenerateSwaggerAnnotation(boolean generateSwaggerAnnotation) {
        this.generateSwaggerAnnotation = generateSwaggerAnnotation;
    }

    public String[] getSwaggerDataModelIgnoreProp() {
        return swaggerDataModelIgnoreProp;
    }

    public void setSwaggerDataModelIgnoreProp(String[] swaggerDataModelIgnoreProp) {
        this.swaggerDataModelIgnoreProp = swaggerDataModelIgnoreProp;
    }

    public boolean isGenerateLombokAnnotation() {
        return generateLombokAnnotation;
    }

    public void setGenerateLombokAnnotation(boolean generateLombokAnnotation) {
        this.generateLombokAnnotation = generateLombokAnnotation;
    }

    public DbTypeEnum getDbType() {
        return dbType;
    }

    public void setDbType(DbTypeEnum dbType) {
        this.dbType = dbType;
    }

    public ColumnTransformTypeEnum getColumnTrans() {
        return columnTrans;
    }

    public void setColumnTrans(ColumnTransformTypeEnum columnTrans) {
        this.columnTrans = columnTrans;
    }

    public String getToPlace() {
        return toPlace;
    }

    public void setToPlace(String toPlace) {
        this.toPlace = toPlace;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String[] getTableNames() {
        return tableNames;
    }

    public void setTableNames(String[] tableNames) {
        this.tableNames = tableNames;
    }

    public String[] getEntityNames() {
        return entityNames;
    }

    public void setEntityNames(String[] entityNames) {
        this.entityNames = entityNames;
    }

    public String[] getSearchColumns() {
        return searchColumns;
    }

    public void setSearchColumns(String[] searchColumns) {
        this.searchColumns = searchColumns;
    }

    public String getFtlLocationPath() {
        return ftlLocationPath;
    }

    public void setFtlLocationPath(String ftlLocationPath) {
        this.ftlLocationPath = ftlLocationPath;
    }

    public Map<String, String> getFtlVsFilePathMap() {
        return ftlVsFilePathMap;
    }

    public void setFtlVsFilePathMap(Map<String, String> ftlVsFilePathMap) {
        this.ftlVsFilePathMap = ftlVsFilePathMap;
    }
}
