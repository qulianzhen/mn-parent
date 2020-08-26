package com.mn.${entityName?lower_case}.controller;

import com.mn.commonbean.restful.Message;
import com.mn.${entityName?lower_case}.entity.param.${entityName}Param;
import com.mn.${entityName?lower_case}.service.${entityName}Service;
import com.mn.mnutil.MessageUtil;
import com.mn.mnutil.SnowFlake;
import com.mn.module.page.PageQuerier;
import com.mn.util.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @ClassName:     ${entityName}Controller.java
 * @Description:   ${tableDesc}的Controller层
 * 
 * @author         ${author}
 * @version        ${version}
 * @Date           ${currentTime}
 */
@RestController
@RequestMapping("/api/${entityName?uncap_first}")
public class ${entityName}Controller{
	private static final Logger LOGGER = LoggerFactory.getLogger(${entityName}Controller.class);
	
	@Autowired
	private ${entityName}Service ${entityName?uncap_first}Service;
	@Autowired
    private SnowFlake snowFlake;
	
	/**
	 * @description 查询${tableDesc}列表-分页
	 * @author ${author}
     * @param param 分页查询参数
	 * @return ${tableDesc}列表
	 */
	@PostMapping(value = "/listPage")
	public Message ${entityName?uncap_first}List(@RequestBody PageQuerier<${entityName}Param> param) {
	
		if(param == null){
			param = new PageQuerier<>();
		}
        return PageUtil.easyUIPageSuccessMsg(${entityName?uncap_first}Service.listPage(param));
	}

	/**
	 * @description 查询${tableDesc}列表-不分页
	 * @author ${author}
     * @param param 查询参数
	 * @return ${tableDesc}列表
	 */
	@PostMapping(value = "/list")
	public Message ${entityName?uncap_first}List(@RequestBody ${entityName}Param param) {
		if(param == null){
			param = new ${entityName}Param();
		}
        return MessageUtil.successMsg(${entityName?uncap_first}Service.list(param));
	}
	
	/**
	* @description 保存(新增/修改)[${tableDesc}]
	* @author ${author}
    * @param param 保存参数
	* @return 响应对象
	*/
	@PostMapping(value = "/save")
	public Message save${entityName}(@RequestBody ${entityName}Param param) {
		if(param!=null &&(param.getId()==null || "".equals(param.getId()))){
            param.setId(snowFlake.nextId());
			${entityName?uncap_first}Service.insert(param);
        }else{
			${entityName?uncap_first}Service.update(param);
        }
        return MessageUtil.successMsg(param.getId());
	}
		
	
	/**
	* @description 批量删除${tableDesc}
	* @author ${author}
	* @param ids 参数:主键集合
	* @return 响应对象
	*/
	@PostMapping(value = "/delete")
	public Message delete${entityName}(@RequestBody List<Long> ids) {
		${entityName?uncap_first}Service.delete(ids);
		return MessageUtil.successMsg();
	}
	
	
	 /**
	* @description 获取单个${tableDesc}
	* @author ${author}
	* @param id 参数:主键
	* @return 响应对象
	*/
	@GetMapping(value = "/get")
	public Message get${entityName}(${pkField.simpleJavaType} ${pkField.fieldName}) {
		${entityName} ${entityName?uncap_first} = ${entityName?uncap_first}Service.get(${pkField.fieldName});
		return MessageUtil.successMsg(${entityName?uncap_first});
	}
}