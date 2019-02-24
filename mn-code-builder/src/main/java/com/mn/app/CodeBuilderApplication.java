package com.mn.app;

import com.mn.entity.BuilderConfig;
import com.mn.entity.ColumnTransformTypeEnum;
import com.mn.entity.DbTypeEnum;
import com.mn.server.TableService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class CodeBuilderApplication {
	public static void main(String[] args) {

		BuilderConfig config = new BuilderConfig();
		/*String packagePath = "com.jfb.businesscard";//com.jgj.业务模块名.子模块(可以没有)
		String[] ignoreBeanProperty  = {};//忽略的bean属性（即实体类中忽略掉） "creater","modifier","isDeleted"
		//swagger的数据Model中隐藏的字段,比如creater，会在生成的Bean的creater字段上加上@ApiModelProperty(hidden=true)注解
		String[] swaggerDataModelIgnoreProp={"creater","modifier","createDate","updateDate","isDeleted"};
		String[] tableNames = {"sys_dict_item"};//表名
		String[] entityNames = {"SysDictItem"};//实体类名字
		
		String toPlace = "D:\\saveMoon\\";//保存的位置
		String author = "qlz";//作者
		String version = "v1.0";//版本*/


		String modelName = "dict";

		Map<String,String> ftlVsFilePath = new HashMap<>();
		//ftlVsFilePath.put("Controller[java].ftl","com.mn."+modelName+".controller");
		//ftlVsFilePath.put("Service[java].ftl","com.mn."+modelName+".service");
		//ftlVsFilePath.put("ServiceImpl[java].ftl","com.mn."+modelName+".service.impl");
		ftlVsFilePath.put("[java].ftl","com.mn."+modelName+".entity.po");
		//ftlVsFilePath.put("Search[java].ftl","com.mn."+modelName+".entity.param");
		//ftlVsFilePath.put("[java].ftl","com.mn."+modelName+".mapper");
		ftlVsFilePath.put("Mapper[xml].ftl","mapper/"+modelName);
		ftlVsFilePath.put("Mapper[java].ftl","com.mn."+modelName+".mapper");
		//config.setFilePathForHtml("");
		config.setFtlVsFilePathMap(ftlVsFilePath);


		config.setTableNames(new String[]{"sys_dict_item"});
		config.setEntityNames(new String[]{"SysDictItem"});
		config.setIgnoreBeanProperty(new String[]{});
		config.setSearchColumns(new String[]{});


		config.setToPlace("D:\\saveMoon\\");
		config.setAuthor("qlz");
		config.setVersion("1.0");

		config.setGenerateLombokAnnotation(false);
		config.setGenerateSwaggerAnnotation(true);
		config.setSwaggerDataModelIgnoreProp(new String[]{"creater","modifier","createDate","updateDate","isDeleted"});
		config.setDbType(DbTypeEnum.mysql);
		config.setColumnTrans(ColumnTransformTypeEnum.tf);
		config.setFtlLocationPath("codeTemplate/commonMapper");


		
		
		ApplicationContext ac = new FileSystemXmlApplicationContext("classpath:config/spring-context.xml");
		TableService tableService = (TableService) ac.getBean("tableService");
		System.out.println("************************开始生成!********************************************");

		tableService.bulideCodes(config);

		/*//表名数量必须与实体类数量一致
		if(config.getTableNames().length == config.getEntityNames().length){
			for(int i=0,len=tableNames.length;i<len;i++){
				tableService.bulideCodes(config.getTableNames()[i], config.getEntityNames()[i], config.getToPlace(),config.getAuthor(),ignoreBeanProperty,packagePath,swaggerDataModelIgnoreProp);
			}
		}*/
		System.out.println("************************生成结束*********************************************");
	}
	
}
