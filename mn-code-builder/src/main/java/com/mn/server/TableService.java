package com.mn.server;

import com.mn.dao.TableDao;
import com.mn.entity.BuilderConfig;
import com.mn.entity.BuliderColumn;
import com.mn.entity.ColumnTransformTypeEnum;
import com.mn.entity.DbTypeEnum;
import com.mn.mnutil.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

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
	private String dbConfigFilePath = "/config/dbconfig.properties";//数据库配置文件
	private String filedTypeCfgPath = "/config/fieldTypeCfg.properties";//字段类型与bean属性类型对应关系配置

	@Resource(name = "mySqlDao")
	private TableDao mySqlDao;
	@Resource(name = "orclDao")
	private TableDao orclDao;

	/**
	 * 生成代码服务
	 * @param tableName  表名
	 * @param entityName 实体类名
	 * @param toPlace    目标位置
	 * @param author     作者
	 * @param ignoreBeanProperty 忽略的字段
	 * @param packagePath 包路径
	 *//*
	public void bulideCodes(String tableName,String entityName,String toPlace,String author,String[] ignoreBeanProperty,String packagePath,String[] swaggerDataModelIgnoreProp){
		String dbUrl = PropertiesUtil.getFileIO("url", dbConfigFilePath);
		String initPath = "codeTemplate/example";
		String tblSchema = dbUrl.substring(dbUrl.lastIndexOf("/")+1, dbUrl.indexOf("?"));//方案名--对应mysql的数据库名
		
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
			t.setFieldName(StringUtil.getTF(t.getColumnName()));
			//字段原名
			//t.setFieldName(t.getColumnName());
			//得到java类型
			t.setJavaType(getJavaTypeByColumnType(t.isShiPk(),t.getDataType()));
			//得到java短类型
			t.setSimpleJavaType(StringUtil.getSimpleName(t.getJavaType()));
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
		String realInitPath = TableService.class.getClassLoader().getResource(initPath).getPath();
		FreeMarkerUtils.initFreeMarker(realInitPath);
		List<String> pathList = FileUtil.getAllPath(null,null,realInitPath);
		String[] pathArry = null;
		for(String path : pathList){
			pathArry = path.split(";");
			FreeMarkerUtils.crateFile(m, pathArry[1], toPlace + getCodePath(packagePath,entityName,"",getSuffix(pathArry[1]),pathArry[1]), true);
		}
		
		
		//开始生成
		*//*-------------------------------------------------------------------------------------------*//*
		*//*-------------------------------------生成代码Start------------------------------------------*//*
		//FreeMarkerUtils.crateFile(m, "/entity/entity.ftl", toPlace + getCodePath(rootPath,entityName,"entity",".java"), true);
		*//*FreeMarkerUtils.crateFile(m, "/dao/mapper.ftl", toPlace  + getCodePath(rootPath,entityName,"dao","Mapper.java"), true);
		FreeMarkerUtils.crateFile(m, "/controller/controller.ftl", toPlace + getCodePath(rootPath,entityName,"controller","Controller.java"), true);
		FreeMarkerUtils.crateFile(m, "/service/service.ftl", toPlace + getCodePath(rootPath,entityName,"service","Service.java"), true);
		FreeMarkerUtils.crateFile(m, "/service/serviceImpl.ftl", toPlace + getCodePath(rootPath,entityName,"service/impl","ServiceImpl.java"), true);
		FreeMarkerUtils.crateFile(m, "/mybatis/mybatis.ftl", toPlace + getCodePath(rootPath,entityName,"mybatis","Mapper.xml"), true);
		FreeMarkerUtils.crateFile(m, "/vo/queryVo.ftl", toPlace + getCodePath(rootPath,entityName,"vo","QueryVo.java"), true);*//*
	}*/


	/**
	 * 生成代码服务
	 * @param config  配置类
	 */
	public void bulideCodes(BuilderConfig config){
		//模板所在路径
		String initPath = config.getFtlLocationPath();
		//查询数据库表信息的时候，有时候为了避免跟其他“方案schema”冲突，所以需要这么一个标识条件
		//在mysql中可以用数据库的名称来充当条件，在oracle中可以通过登录用户名来充当（user和schema的关系请自行查询）
		String tblSchema = "";
		TableDao tableDao = null;
		if(config.getDbType().equals(DbTypeEnum.mysql)){
			String dbUrl = PropertiesUtil.getFileIO("url", dbConfigFilePath);
			tblSchema = dbUrl.substring(dbUrl.lastIndexOf("/")+1, dbUrl.indexOf("?"));//方案名--对应mysql的数据库名
			tableDao = mySqlDao;
		}else if(config.getDbType().equals(DbTypeEnum.oracle)){
			tblSchema = PropertiesUtil.getFileIO("username",dbConfigFilePath);
			tableDao = orclDao;
		}else{
			throw new RuntimeException("未支持的数据库，请检查config中的dbType");
		}

		String[] tableNames = config.getTableNames();
		String[] entityNames = config.getEntityNames();
		if(tableNames.length != entityNames.length){
			throw new RuntimeException("表名的数量要和实体类的数量一致!");
		}
		for(int i=0,len = tableNames.length;i<len;i++){
			String tableName = tableNames[i];
			String entityName = entityNames[i];

			//构造数据
			Map<String,Object> ftlDataMap = new HashMap<>();

			Set<String> needImportPacageSet = new HashSet<String>();//需要导入的包
			List<BuliderColumn> allColumns = getColumnMessByTabelName(tableName,tblSchema,tableDao);//属性-列名  集合
			String pk = getPkByTableName(tableName,tblSchema,tableDao);//注意，本生成器只是支持单主键！
			BuliderColumn pkField = null;//主键信息
			for(int j=0,jLen=allColumns.size();j<jLen;j++){
				BuliderColumn currentColumn = allColumns.get(j);
				if(!StringUtils.isEmpty(pk) && currentColumn.getColumnName().equals(pk)){
					currentColumn.setShiPk(true);//设置主键
					pkField = currentColumn;
				}
				//得到驼峰写法的成员变量名 或者直接原样
				currentColumn.setFieldName(config.getColumnTrans().equals(ColumnTransformTypeEnum.tf)?StringUtil.getTF(currentColumn.getColumnName()):currentColumn.getColumnName());
				//得到java类型--在这里，主键所生成的java类型是单独制定的！
				currentColumn.setJavaType(getJavaTypeByColumnType(currentColumn.isShiPk(),currentColumn.getDataType()));
				//得到java短类型
				currentColumn.setSimpleJavaType(StringUtil.getSimpleName(currentColumn.getJavaType()));
				//java.lang.包不需要引入
				if(!currentColumn.getJavaType().startsWith("java.lang.")){
					needImportPacageSet.add(currentColumn.getJavaType());
				}
				currentColumn.setJdbcType(getJdbcTypeByJavaType(currentColumn.getSimpleJavaType()));
			}

			List<BuliderColumn> entityproPertyIgnoreDelList = getListIgnoreDel(allColumns,config.getIgnoreBeanProperty());

			ftlDataMap.put("tableName", tableName);
			ftlDataMap.put("columnList", allColumns);
			ftlDataMap.put("entityName", entityName);
			ftlDataMap.put("userName",ComputerInfoUtil.getPcName());
			ftlDataMap.put("currentTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			ftlDataMap.put("needImportPacageSet", needImportPacageSet);
			ftlDataMap.put("pkField", pkField);
			ftlDataMap.put("tableDesc", getTableDesc(tableName,tblSchema,tableDao));
			ftlDataMap.put("author", config.getAuthor());
			ftlDataMap.put("version", config.getVersion());
			ftlDataMap.put("entityproPertyIgnoreDel", entityproPertyIgnoreDelList);
			//ftlDataMap.put("rootPath", packagePath); // "com.jfb.active.kj";
			//ftlDataMap.put("businesspckPath", packagePath.replace("com.jfb.", ""));  //"active.kj";
			//ftlDataMap.put("businesspckPathX", packagePath.replace("com.jfb.", "").replace(".", "/"));  //"active/kj";
			List<String> swaggerDataModelIgnorePropList = new ArrayList<>();
			if(config.getSwaggerDataModelIgnoreProp() != null && config.getSwaggerDataModelIgnoreProp().length!=0){
				swaggerDataModelIgnorePropList = Arrays.asList(config.getSwaggerDataModelIgnoreProp());
			}
			ftlDataMap.put("generateLombokAnnotation",config.isGenerateLombokAnnotation());//是否生成Lombok注解
			ftlDataMap.put("generateSwaggerAnnotation",config.isGenerateSwaggerAnnotation());//是否生成swagger注解
			ftlDataMap.put("swaggerProIgnore", swaggerDataModelIgnorePropList);//swagger注解忽略的字段

			Map<String,String> ftlVsFilePathMap = config.getFtlVsFilePathMap();
			ftlDataMap.put("javaPackagesMap",ftlVsFilePathMap);


			//初始化Freemarker工具类，指定模板文件base路径
			String realInitPath = TableService.class.getClassLoader().getResource(initPath).getPath();
			FreeMarkerUtils.initFreeMarker(realInitPath);
			List<String> pathList = FileUtil.getAllPath(null,null,realInitPath);
			String[] pathArray = null;
			for(String path : pathList){
				pathArray = path.split(";"); //0-文件名，1-模板的实际路径
				ftlDataMap.put("currentFilePkg",ftlVsFilePathMap.get(pathArray[0]));//本文件的包路径,如 [java].ftl====> com.mn.dict.entity.po
				FreeMarkerUtils.crateFile(ftlDataMap, pathArray[1], config.getToPlace() + getCodePath(ftlVsFilePathMap.get(pathArray[0]),entityName,getSuffix(pathArray[1]),pathArray[1]), true);
			}

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
	 * @param tableName 表名
	 * @param schemaName 方案名
	 * @return
	 */
	public List<BuliderColumn> getColumnMessByTabelName(String tableName,String schemaName,TableDao tableDao){
		List<BuliderColumn> l = null;
		try{
			l = tableDao.getColumnMessByTabelName(tableName,schemaName);
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("通过表名和所有者得到表所有字段信息执行查询出错！"+e.getMessage());
		}
		return l;
	}

	/**
	 * 通过表名和方案名得到表的主键
	 * @param tableName
	 * @param schemaName
	 * @return
	 */
	public String getPkByTableName(String tableName,String schemaName,TableDao tableDao){
		String pk = "";
		try{
			pk = tableDao.getPkByTableName(tableName,schemaName);
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("通过表名和所有者得到表的主键执行查询出错！"+e.getMessage());
		}
		return pk;
	}

	/**
	 * 得到表描述信息
	 * @param tableName
	 * @param schemaName
	 * @return
	 */
	public String getTableDesc(String tableName, String schemaName,TableDao tableDao) {
		String desc = "";
		try{
			desc = tableDao.getTableDesc(tableName,schemaName);
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
		String fieldType = PropertiesUtil.getFileIO(dataType.toUpperCase(),filedTypeCfgPath);
		if(fieldType == null){
			fieldType = PropertiesUtil.getFileIO("DEFAULT",filedTypeCfgPath);
		}
		return fieldType;
	}
	
	/**
	 * 通过java类型得到jdbc类型,mybatis中用  --这里暂时没有使用配置文件，写死了！
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
	 * @param houzhui
	 * @return
	 */
	private String getCodePath(String rootPath, String entityName,String houzhui,String fileSource) {
		fileSource = fileSource.replace(".ftl", "");
		if(fileSource.indexOf("[")!=-1 && fileSource.indexOf("]")!=-1){
			fileSource = fileSource.substring(0,fileSource.indexOf("[")) + fileSource.substring(fileSource.indexOf("]")+1, fileSource.length());
		}
		int subStr = fileSource.lastIndexOf("/");
		if(subStr != -1){
			fileSource = fileSource.substring(subStr+1 , fileSource.length());
		}
		String targetPath = File.separator + rootPath.replaceAll("\\.","\\\\") + File.separator  + entityName+ fileSource +houzhui;
		return targetPath;
	}
}
