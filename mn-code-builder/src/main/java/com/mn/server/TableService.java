package com.mn.server;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mn.dao.TableDao;
import com.mn.entity.BuliderColumn;
import com.mn.utils.ComputerInfoUtil;
import com.mn.utils.FileUtil;
import com.mn.utils.FreeMarkerUtils;
import com.mn.utils.PropertiesTools2;
import com.mn.utils.PropertiesUtil;
import com.mn.utils.StringTools;

/**
 * @ClassName:     TableService.java
 * @Description:   TODO(用一句话描述该文件做什么) 
 * 
 * @author         33107
 * @version        V1.0  
 * @Date           2016年6月13日 上午10:53:21 
 */
@Service
public class TableService {
	@Resource(name = "tableDao")
	private TableDao tableDao;
	
	/**
	 * 生成代码服务
	 * @param tableName  表名
	 * @param entityName 实体类名
	 * @param toPlace    目标位置
	 * @param author     作者
	 * @param ignoreBeanProperty 忽略的字段
	 * @param packagePath 包路径
	 */
	public void bulideCodes(String tableName,String entityName,String toPlace,String author,String[] ignoreBeanProperty,String packagePath,String[] swaggerDataModelIgnoreProp){
		String dbUrl = PropertiesTools2.getFileIO("url", "/dbconfig.properties");
		String tblSchema = dbUrl.substring(dbUrl.lastIndexOf("/")+1, dbUrl.indexOf("?"));//方案名--对应mysql的数据库名
		
		String initPath = "codeTemplate";//FTL模板所在主目录
		//构造数据
		Map<String,Object> m = new HashMap<String,Object>();
		
		Set<String> needImportPacageSet = new HashSet<String>();//需要导入的包
		List<BuliderColumn> l = getColumnMessByTabelName(tableName,null,tblSchema);//属性-列名  集合
		String pk = getPkByTableName(tableName,null,tblSchema);
		BuliderColumn pkField = null;//主键信息
		for(int i=0,len=l.size();i<len;i++){
			BuliderColumn t = l.get(i);
			if(!StringUtils.isEmpty(pk) && t.getColumnName().equals(pk)){
				t.setShiPk(true);//设置主键
				pkField = t;
			}
			//得到驼峰写法的成员变量名
			//t.setFieldName(StringTools.getTF(t.getColumnName()));
			//字段原名
			t.setFieldName(t.getColumnName());
			//得到java类型
			t.setJavaType(getJavaTypeByColumnType(t.isShiPk(),t.getDataType()));
			//得到java短类型
			t.setSimpleJavaType(StringTools.getSimpleName(t.getJavaType()));
			//java.lang.包不需要引入
			if(!t.getJavaType().startsWith("java.lang.")){
				needImportPacageSet.add(t.getJavaType());
			}
			t.setJdbcType(getJdbcTypeByJavaType(t.getSimpleJavaType()));
		}
		
		List<BuliderColumn> entityproPertyIgnoreDelList = getListIgnoreDel(l,ignoreBeanProperty);
		
		m.put("tableName", tableName);
		m.put("columnList", l);
		m.put("entityName", entityName);
		m.put("userName",ComputerInfoUtil.getPcName());
		m.put("currentTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		m.put("needImportPacageSet", needImportPacageSet);
		m.put("pkField", pkField);
		m.put("tableDesc", getTableDesc(tableName,"",tblSchema));
		m.put("author", author);
		m.put("entityproPertyIgnoreDel", entityproPertyIgnoreDelList);
		m.put("rootPath", packagePath); // "com.jfb.active.kj";
		m.put("businesspckPath", packagePath.replace("com.jfb.", ""));  //"active.kj";
		m.put("businesspckPathX", packagePath.replace("com.jfb.", "").replace(".", "/"));  //"active/kj";
		List<String> swaggerDataModelIgnorePropList = new ArrayList<>();
		if(swaggerDataModelIgnoreProp != null && swaggerDataModelIgnoreProp.length!=0){
			swaggerDataModelIgnorePropList = Arrays.asList(swaggerDataModelIgnoreProp);
		}
		m.put("swaggerProIgnore", swaggerDataModelIgnorePropList);
		
		
		//初始化Freemarker工具类，指定模板文件base路径
		FreeMarkerUtils.initFreeMarker(initPath);
		List<String> pathList = FileUtil.getAllPath(null,null,initPath);
		String[] pathArry = null;
		for(String path : pathList){
			pathArry = path.split(";");
			FreeMarkerUtils.crateFile(m, pathArry[1], toPlace + getCodePath(packagePath,entityName,"",getSuffix(pathArry[1]),pathArry[1]), true);
		}
		
		
		//开始生成
		/*-------------------------------------------------------------------------------------------*/
		/*-------------------------------------生成代码Start------------------------------------------*/
		//FreeMarkerUtils.crateFile(m, "/entity/entity.ftl", toPlace + getCodePath(rootPath,entityName,"entity",".java"), true);
		/*FreeMarkerUtils.crateFile(m, "/dao/mapper.ftl", toPlace  + getCodePath(rootPath,entityName,"dao","Mapper.java"), true);
		FreeMarkerUtils.crateFile(m, "/controller/controller.ftl", toPlace + getCodePath(rootPath,entityName,"controller","Controller.java"), true);
		FreeMarkerUtils.crateFile(m, "/service/service.ftl", toPlace + getCodePath(rootPath,entityName,"service","Service.java"), true);
		FreeMarkerUtils.crateFile(m, "/service/serviceImpl.ftl", toPlace + getCodePath(rootPath,entityName,"service/impl","ServiceImpl.java"), true);
		FreeMarkerUtils.crateFile(m, "/mybatis/mybatis.ftl", toPlace + getCodePath(rootPath,entityName,"mybatis","Mapper.xml"), true);
		FreeMarkerUtils.crateFile(m, "/vo/queryVo.ftl", toPlace + getCodePath(rootPath,entityName,"vo","QueryVo.java"), true);*/
	}



	/**
	 * 去掉忽略的Bean属性
	 * @param l
	 * @param ignoreColumns
	 * @return
	 */
	private List<BuliderColumn> getListIgnoreDel(List<BuliderColumn> l, String[] ignoreColumns) {
		if(ignoreColumns == null || ignoreColumns.length == 0){
			return l;
		}
		List<BuliderColumn> retuList = new ArrayList<BuliderColumn>();
		for(BuliderColumn buliderColumn : l){
			boolean isHaveIt = true;
			for(int i=0,len=ignoreColumns.length;i<len;i++){
				if(ignoreColumns[i].equals(buliderColumn.getFieldName())){
					isHaveIt = false;
					break;
				}
			}
			if(isHaveIt){
				retuList.add(buliderColumn);
			}
		}
		return retuList;
	}



	/**
	 * 获取后缀
	 * @param path
	 * @return
	 */
	private String getSuffix(String path) {
		if(path.indexOf("[")==-1){
			return "";
		}
		return "."+path.substring(path.indexOf("[")+1, path.indexOf("]"));
	}
	
	
	/**
	 * 通过表名和所有者得到表所有字段信息
	 * @param tableName
	 * @param owner 所有者
	 * @param schemaName 方案名
	 * @return
	 */
	public List<BuliderColumn> getColumnMessByTabelName(String tableName,String owner,String schemaName){
		List<BuliderColumn> l = null;
		try{
			l = tableDao.getColumnMessByTabelName(tableName,owner,schemaName);
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("通过表名和所有者得到表所有字段信息执行查询出错！"+e.getMessage());
		}
		return l;
	}

	/**
	 * 通过表名和所有者得到表的主键
	 * @param tableName
	 * @param owner
	 * @param schemaName
	 * @return
	 */
	public String getPkByTableName(String tableName,String owner,String schemaName){
		String pk = "";
		try{
			pk = tableDao.getPkByTableName(tableName,owner,schemaName);
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("通过表名和所有者得到表的主键执行查询出错！"+e.getMessage());
		}
		return pk;
	}

	/**
	 * 得到表描述信息
	 * @param tableName
	 * @param owner
	 * @param schemaName
	 * @return
	 */
	public String getTableDesc(String tableName, String owner,String schemaName) {
		String desc = "";
		try{
			desc = tableDao.getTableDesc(tableName,owner,schemaName);
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("通过表名和所有者得到表的描述信息出错！"+e.getMessage());
		}
		return desc;
	}
	
	/**
	 * 根据数据库类型得到实体类属性的类型
	 * @param isShiPk 是否是主键
	 * @param dataType 数据库中的类型
	 * @return
	 */
	private  String getJavaTypeByColumnType(boolean isShiPk,String dataType) {
		// TODO Auto-generated method stub
		if(isShiPk){
			dataType = "PK";
		}
		String fieldType = PropertiesUtil.get(dataType.toUpperCase());
		if(fieldType == null){
			fieldType = PropertiesUtil.get("DEFAULT");
		}
		return fieldType;
	}
	
	/**
	 * 通过java类型得到jdbc类型,mybatis中用
	 * @param simpleJavaType
	 * @return
	 */
	private static String getJdbcTypeByJavaType(String simpleJavaType) {
		String jdbcType = null;
		if("Long".equals(simpleJavaType)){
			jdbcType = "BIGINT";
		}else if("String".equals(simpleJavaType)){
			jdbcType = "VARCHAR";
		}else if("BigDecimal".equals(simpleJavaType)){
			jdbcType = "DECIMAL";
		}else if("Date".equals(simpleJavaType)){
			jdbcType = "DATE";
		}else if("Integer".equals(simpleJavaType)){
			jdbcType = "INTEGER";
		}
		return jdbcType;
	}
	
	/**
	 * 计算最后的生成路径【需要根据项目结构自定义】
	 * @param rootPath   
	 * @param entityName 实体类名
	 * @param subPath    
	 * @param houzhui
	 * @return
	 */
	private String getCodePath(String rootPath, String entityName,String subPath,String houzhui,String fileSource) {
		fileSource = fileSource.replace(".ftl", "");
		if(fileSource.indexOf("[")!=-1 && fileSource.indexOf("]")!=-1){
			fileSource = fileSource.substring(0,fileSource.indexOf("[")) + fileSource.substring(fileSource.indexOf("]")+1, fileSource.length());
		}
		
		int subStr = fileSource.lastIndexOf("/");
		if(subStr != -1){
			fileSource = fileSource.substring(subStr+1 , fileSource.length());
		}
		
		String entityName2 = (entityName.charAt(0)+"").toLowerCase() + entityName.substring(1, entityName.length());//首字母小写
		if(subPath!=null && !"".equals(subPath)){
			subPath = subPath+ File.separator;
		}else{
			subPath = "";
		}
		
		if(fileSource.equals("")){//实体类Entity
			rootPath = "model" +File.separator + rootPath.replace("com.jfb.", "").replace(".", File.separator) + File.separator  + entityName+ fileSource +houzhui;
		}else if(fileSource.equals("Search")){
			rootPath = "search" +File.separator + rootPath.replace("com.jfb.", "").replace(".", File.separator) + File.separator  + entityName+ fileSource +houzhui;
		}else if(fileSource.equals("Mapper")){
			rootPath = "mapper" +File.separator + rootPath.replace("com.jfb.", "").replace(".", File.separator) + File.separator + entityName+ fileSource +houzhui;
		}else if(fileSource.equals("Controller")){
			rootPath = rootPath.replace(".", File.separator) + File.separator +  "controllers" +File.separator+ entityName+fileSource +houzhui;
		}else if(fileSource.equals("Service")){
			rootPath = rootPath.replace(".", File.separator) + File.separator +  "service" + File.separator+ entityName+fileSource +houzhui;
		}else if(fileSource.equals("ServiceImpl")){
			rootPath = rootPath.replace(".", File.separator) + File.separator +  "service"+File.separator+"impl" + File.separator+ entityName+ fileSource +houzhui;
		}else{
			rootPath = rootPath.replace(".", File.separator) +File.separator+  fileSource +houzhui;
		}
		
		//rootPath = rootPath.replace(".", File.separator) +File.separator + subPath + entityName2 + File.separator + entityName+  fileSource +houzhui;
		return rootPath;
	}
}
