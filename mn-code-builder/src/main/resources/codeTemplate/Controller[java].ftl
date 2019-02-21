package ${rootPath}.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

import com.jfb.model.${businesspckPath}.${entityName};
import com.jfb.search.${businesspckPath}.${entityName}Search;
import com.jfb.sys.controllers.BaseController;
import com.jfb.sys.log.SystemControllerLog;
import ${rootPath}.service.${entityName}Service;
import com.jfb.model.resultful.Message;
import com.jfb.utils.MessageUtil;
import com.jfb.utils.page.PageUtils;
import com.jfb.utils.page.Pageable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
/**
 * @ClassName:     ${entityName}Controller.java
 * @Description:   ${tableDesc}的Controller层
 * 
 * @author         ${author}
 * @version        V1.0
 * @Date           ${currentTime}
 */
@Api(value="${tableDesc}controller",tags={"${tableDesc}操作接口"}) 
@Controller("${entityName?uncap_first}Controller")
@RequestMapping("/admin/${businesspckPathX}")
public class ${entityName}Controller extends BaseController{
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(${entityName}Controller.class);
	
	@Resource
	private ${entityName}Service ${entityName?uncap_first}Service;
	
	//web代码······
	
	/**
	 * @description 查询${tableDesc}列表
	 * @author ${author}
	 * @return ${tableDesc}列表视图
	 */
	@RequestMapping(value="/${entityName?uncap_first}List")
	@ResponseBody
	public Message ${entityName?uncap_first}List(Pageable pageable,${entityName}Search search) {
	
		if(search==null){
			search = new ${entityName}Search();
		}
		int pageSize = pageable.getPageSize();
		search.setStartIndex(PageUtils.getStartIndexByMysql(pageable.getPageNumber(), pageSize));
		search.setPageSize(pageSize);
		return MessageUtil.successMsg(${entityName?uncap_first}Service.findPage(pageable, search));
	}
	
	/**
	* @description 新增[${tableDesc}]
	* @author ${author}
	* @return 响应对象
	*/
	@ApiOperation(value="新增${tableDesc}",notes="新增${tableDesc}")
	@SystemControllerLog(description = "新增${tableDesc}")
	@RequestMapping(value = "/save${entityName}", method = RequestMethod.POST)
	@ResponseBody
	public Message save${entityName}(@ApiParam(name="${entityName?uncap_first}",value="${tableDesc}Model",required=true)@RequestBody ${entityName} ${entityName?uncap_first}) {
		
		${entityName?uncap_first}Service.save${entityName}(${entityName?uncap_first});
		return MessageUtil.successMsg();
	}
		
	
	
	/**
	* @description 修改[${tableDesc}]
	* @author ${author}
	* @return 响应对象
	*/
	@ApiOperation(value="修改${tableDesc}",notes="修改${tableDesc}")
	@RequestMapping(value = "/update${entityName}", method = RequestMethod.POST)
	@ResponseBody
	@SystemControllerLog(description = "修改${tableDesc}")
	public Message update${entityName}(@ApiParam(name="${entityName?uncap_first}",value="${tableDesc}Model",required=true)@RequestBody ${entityName} ${entityName?uncap_first}) {
		
		${entityName?uncap_first}Service.update${entityName}(${entityName?uncap_first});
		return MessageUtil.successMsg();
	}
	
	
	/**
	* @description 批量删除${tableDesc}
	* @author ${author}
	* @return 响应对象
	*/
	@ApiOperation(value="删除${tableDesc}",notes="删除${tableDesc}")
	@RequestMapping(value = "/delete${entityName}", method = RequestMethod.POST)
	@ResponseBody
	@SystemControllerLog(description = "删除${tableDesc}")
	public Message delete${entityName}(@ApiParam(name="ids",value="主键数组[1,2,3]",required=true)@RequestBody ${pkField.simpleJavaType}[] ids) {
		${entityName?uncap_first}Service.delete${entityName}(ids);
		return MessageUtil.successMsg();
	}
	
	
	 /**
	* @description 获取单个${tableDesc}
	* @author ${author}
	* @return 响应对象
	*/
	@ApiOperation(value="get${tableDesc}数据",notes="根据${pkField.fieldName}获取${tableDesc}数据")
	@ApiImplicitParam(required = true,name="${pkField.fieldName}",value="${tableDesc}数据ID",dataType="${pkField.simpleJavaType?uncap_first}", paramType = "query")
	@RequestMapping(value = "/get${entityName}", method = RequestMethod.GET)
	@ResponseBody
	public Message get${entityName}(${pkField.simpleJavaType} ${pkField.fieldName}) {
		${entityName} ${entityName?uncap_first} = ${entityName?uncap_first}Service.get${entityName}(${pkField.fieldName});
		return MessageUtil.successMsg(${entityName?uncap_first});
	}
}