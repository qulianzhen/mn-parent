package com.mn.psytest.entity.po;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * @ClassName:     FpTestUserTopic.java
 * @Description:   测试题_用户_题干的实体Bean
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2019-03-11 22:50:43
 */
@ApiModel(value="测试题_用户_题干Model",description="测试题_用户_题干对象数据模型")
@Table(name="fp_test_user_topic")
public class FpTestUserTopic implements Serializable {
	private static final long serialVersionUID = 1L;
	//主键
	@ApiModelProperty(notes = "主键")
	@Column(name="ID")
	private Long id;
	//题干ID
	@ApiModelProperty(notes = "题干ID")
	@Column(name="TOPIC_ID")
	private Long topicId;
	//用户标识
	@ApiModelProperty(notes = "用户标识")
	@Column(name="USER_ID")
	private Long userId;
	//开始时间
	@ApiModelProperty(notes = "开始时间")
	@Column(name="BEGIN_TIME")
	private Date beginTime;
	//结束时间
	@ApiModelProperty(notes = "结束时间")
	@Column(name="END_TIME")
	private Date endTime;
	//最终结果
	@ApiModelProperty(notes = "最终结果")
	@Column(name="FINAL_RESULT")
	private String finalResult;
	//set get method
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getTopicId(){
		return topicId;
	}
	
	public void setTopicId(Long topicId){
		this.topicId = topicId;
	}
	
	public Long getUserId(){
		return userId;
	}
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	
	public Date getBeginTime(){
		return beginTime;
	}
	
	public void setBeginTime(Date beginTime){
		this.beginTime = beginTime;
	}
	
	public Date getEndTime(){
		return endTime;
	}
	
	public void setEndTime(Date endTime){
		this.endTime = endTime;
	}
	
	public String getFinalResult(){
		return finalResult;
	}
	
	public void setFinalResult(String finalResult){
		this.finalResult = finalResult;
	}
	
}