package com.mn.psytest.entity.po;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * @ClassName:     FpTestTopic.java
 * @Description:   测试题_题干的实体Bean
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2019-03-11 22:50:43
 */
@ApiModel(value="测试题_题干Model",description="测试题_题干对象数据模型")
@Table(name="fp_test_topic")
public class FpTestTopic implements Serializable {
	private static final long serialVersionUID = 1L;
	//主键
	@ApiModelProperty(notes = "主键")
	@Column(name="ID")
	private Long id;
	//题目类型: 1心理测试 2智商测试 3其他
	@ApiModelProperty(notes = "题目类型: 1心理测试 2智商测试 3其他")
	@Column(name="TOPIC_TYPE")
	private Integer topicType;
	//题目描述
	@ApiModelProperty(notes = "题目描述")
	@Column(name="TOPIC_DESC")
	private String topicDesc;
	//题目总数
	@ApiModelProperty(notes = "题目总数")
	@Column(name="TOPIC_ITEM_COUNT")
	private Integer topicItemCount;
	//答案计算方法
	@ApiModelProperty(notes = "答案计算方法")
	@Column(name="TOPIC_ANSWER_TYPE")
	private Integer topicAnswerType;
	//题目图片
	@ApiModelProperty(notes = "题目图片")
	@Column(name="TOPIC_IMAGE")
	private String topicImage;
	//set get method
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	public Integer getTopicType(){
		return topicType;
	}
	
	public void setTopicType(Integer topicType){
		this.topicType = topicType;
	}
	
	public String getTopicDesc(){
		return topicDesc;
	}
	
	public void setTopicDesc(String topicDesc){
		this.topicDesc = topicDesc;
	}
	
	public Integer getTopicItemCount(){
		return topicItemCount;
	}
	
	public void setTopicItemCount(Integer topicItemCount){
		this.topicItemCount = topicItemCount;
	}
	
	public Integer getTopicAnswerType(){
		return topicAnswerType;
	}
	
	public void setTopicAnswerType(Integer topicAnswerType){
		this.topicAnswerType = topicAnswerType;
	}
	
	public String getTopicImage(){
		return topicImage;
	}
	
	public void setTopicImage(String topicImage){
		this.topicImage = topicImage;
	}
	
}