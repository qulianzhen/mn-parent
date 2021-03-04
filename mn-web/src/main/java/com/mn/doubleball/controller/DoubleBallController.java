package com.mn.doubleball.controller;

import com.mn.commonbean.restful.Message;
import com.mn.doubleball.entity.param.DoubleBallParam;
import com.mn.doubleball.entity.po.DoubleBall;
import com.mn.doubleball.service.DoubleBallService;
import com.mn.mnutil.MessageUtil;
import com.mn.mnutil.SnowFlake;
import com.mn.module.page.PageQuerier;
import com.mn.util.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName:     DoubleBallController.java
 * @Description:   双色球历史记录的Controller层
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2020-10-21 01:22:54
 */
@RestController
@RequestMapping("/api/doubleBall")
public class DoubleBallController{
	private static final Logger LOGGER = LoggerFactory.getLogger(DoubleBallController.class);
	
	@Autowired
	private DoubleBallService doubleBallService;
	@Autowired
    private SnowFlake snowFlake;
	
	/**
	 * @description 查询双色球历史记录列表-分页
	 * @author qlz
     * @param param 分页查询参数
	 * @return 双色球历史记录列表
	 */
	@PostMapping(value = "/listPage")
	public Message doubleBallList(@RequestBody PageQuerier<DoubleBallParam> param) {
	
		if(param == null){
			param = new PageQuerier<>();
		}
        return PageUtil.easyUIPageSuccessMsg(doubleBallService.listPage(param));
	}

	/**
	 * @description 查询双色球历史记录列表-不分页
	 * @author qlz
     * @param param 查询参数
	 * @return 双色球历史记录列表
	 */
	@PostMapping(value = "/list")
	public Message doubleBallList(@RequestBody DoubleBallParam param) {
		if(param == null){
			param = new DoubleBallParam();
		}
        return MessageUtil.successMsg(doubleBallService.list(param));
	}
	
	/**
	* @description 保存(新增/修改)[双色球历史记录]
	* @author qlz
    * @param param 保存参数
	* @return 响应对象
	*/
	@PostMapping(value = "/save")
	public Message saveDoubleBall(@RequestBody DoubleBallParam param) {
		if(param!=null &&(param.getId()==null || "".equals(param.getId()))){
            param.setId(snowFlake.nextId());
			doubleBallService.insert(param);
        }else{
			doubleBallService.update(param);
        }
        return MessageUtil.successMsg(param.getId());
	}
		
	
	/**
	* @description 批量删除双色球历史记录
	* @author qlz
	* @param ids 参数:主键集合
	* @return 响应对象
	*/
	@PostMapping(value = "/delete")
	public Message deleteDoubleBall(@RequestBody List<Long> ids) {
		doubleBallService.delete(ids);
		return MessageUtil.successMsg();
	}
	
	
	 /**
	* @description 获取单个双色球历史记录
	* @author qlz
	* @param id 参数:主键
	* @return 响应对象
	*/
	@GetMapping(value = "/get")
	public Message getDoubleBall(Long id) {
		DoubleBall doubleBall = doubleBallService.get(id);
		return MessageUtil.successMsg(doubleBall);
	}

	 /**
	 * @description 获取该蓝色球下一个的各个球的几率
	 * @author qlz
	 * @param dbBlue 参数:蓝球号
	 * @return 响应对象
	 */
	@GetMapping(value = "/singleBallAnalyse")
	public Message singleBallAnalyse(Integer dbBlue) {
		List<Map<Integer, Object>> resultList = doubleBallService.singleBallAnalyse(dbBlue);
		return MessageUtil.successMsg(resultList);
	}
	/**
	 * @description 求相似曲线
	 * @author qlz
	 * @param range 几个球
	 * @return 响应对象
	 */
	@GetMapping(value = "/getSimilarity")
	public Message getSimilarity(Integer range) {
		Map<String,Object> resultMap = doubleBallService.getSimilarity(range);
		return MessageUtil.successMsg(resultMap);
	}
}