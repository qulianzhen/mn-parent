package com.mn.psytest.entity.po;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * @ClassName:     FpTestTopicAnswers.java
 * @Description:   测试题_题目答案的实体Bean
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2019-03-11 22:50:43
 */
@ApiModel(value="测试题_题目答案Model",description="测试题_题目答案对象数据模型")
@Table(name="fp_test_topic_answers")
public class FpTestTopicAnswers implements Serializable {
	private static final long serialVersionUID = 1L;
	//主键
	@ApiModelProperty(notes = "主键")
	@Column(name="ID")
	private Long id;
	//题干ID
	@ApiModelProperty(notes = "题干ID")
	@Column(name="TOPIC_ID")
	private Long topicId;
	//答案内容
	@ApiModelProperty(notes = "答案内容")
	@Column(name="ANSWER_CONTENT")
	private String answerContent;
	//答案描述
	@ApiModelProperty(notes = "答案描述")
	@Column(name="ANSWER_DESC")
	private String answerDesc;
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
	
	public String getAnswerContent(){
		return answerContent;
	}
	
	public void setAnswerContent(String answerContent){
		this.answerContent = answerContent;
	}
	
	public String getAnswerDesc(){
		return answerDesc;
	}
	
	public void setAnswerDesc(String answerDesc){
		this.answerDesc = answerDesc;
	}
	
}