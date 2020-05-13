package com.mn.psytest.entity.po;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * @ClassName:     FpTestUserAnswers.java
 * @Description:   测试题_用户_题目答案的实体Bean
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2019-03-11 22:50:43
 */
@ApiModel(value="测试题_用户_题目答案Model",description="测试题_用户_题目答案对象数据模型")
@Table(name="fp_test_user_answers")
public class FpTestUserAnswers implements Serializable {
	private static final long serialVersionUID = 1L;
	//主键
	@ApiModelProperty(notes = "主键")
	@Column(name="ID")
	private Long id;
	//用户_题干_ID
	@ApiModelProperty(notes = "用户_题干_ID")
	@Column(name="USER_TOPIC_ID")
	private Long userTopicId;
	//用户_题干_题目_ID
	@ApiModelProperty(notes = "用户_题干_题目_ID")
	@Column(name="USER_TOPIC_ITEM_ID")
	private Long userTopicItemId;
	//用户_题干_题目_答案
	@ApiModelProperty(notes = "用户_题干_题目_答案")
	@Column(name="USER_TOPIC_ITEM_ANSWER")
	private String userTopicItemAnswer;
	//set get method
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getUserTopicId(){
		return userTopicId;
	}
	
	public void setUserTopicId(Long userTopicId){
		this.userTopicId = userTopicId;
	}
	
	public Long getUserTopicItemId(){
		return userTopicItemId;
	}
	
	public void setUserTopicItemId(Long userTopicItemId){
		this.userTopicItemId = userTopicItemId;
	}
	
	public String getUserTopicItemAnswer(){
		return userTopicItemAnswer;
	}
	
	public void setUserTopicItemAnswer(String userTopicItemAnswer){
		this.userTopicItemAnswer = userTopicItemAnswer;
	}
	
}