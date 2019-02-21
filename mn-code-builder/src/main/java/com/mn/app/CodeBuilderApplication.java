package com.mn.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import com.mn.server.TableService;

public class CodeBuilderApplication {
	public static void main(String[] args) {
		
		String packagePath = "com.jfb.businesscard";//com.jgj.业务模块名.子模块(可以没有)
		String[] ignoreBeanProperty  = {};//忽略的bean属性（即实体类中忽略掉） "creater","modifier","isDeleted"
		//swagger的数据Model中隐藏的字段,比如creater，会在生成的Bean的creater字段上加上@ApiModelProperty(hidden=true)注解
		String[] swaggerDataModelIgnoreProp={"creater","modifier","createDate","updateDate","isDeleted"};
		String[] tableNames = {"t_label_classify","t_label","t_member_label"};//表名
		String[] entityNames = {"LabelClassify","Label","MemberLabel"};//实体类名字
		
		String toPlace = "D:\\saveMoon\\";//保存的位置
		String author = "qlz";//作者
		
		
		ApplicationContext ac = new FileSystemXmlApplicationContext("config/spring-context.xml");
		TableService tableService = (TableService) ac.getBean("tableService");
		System.out.println("************************开始生成!********************************************");
		
		if(tableNames.length == entityNames.length){
			for(int i=0,len=tableNames.length;i<len;i++){
				tableService.bulideCodes(tableNames[i], entityNames[i], toPlace,author,ignoreBeanProperty,packagePath,swaggerDataModelIgnoreProp);
			}
		}
		System.out.println("************************生成结束*********************************************");
	}
	
}
