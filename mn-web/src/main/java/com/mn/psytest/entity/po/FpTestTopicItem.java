package com.mn.psytest.entity.po;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * @ClassName:     FpTestTopicItem.java
 * @Description:   测试题_题目明细的实体Bean
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2019-03-11 22:50:43
 */
@ApiModel(value="测试题_题目明细Model",description="测试题_题目明细对象数据模型")
@Table(name="fp_test_topic_item")
public class FpTestTopicItem implements Serializable {
	private static final long serialVersionUID = 1L;
	//主键
	@ApiModelProperty(notes = "主键")
	@Column(name="ID")
	private Long id;
	//题干ID
	@ApiModelProperty(notes = "题干ID")
	@Column(name="TOPIC_ID")
	private Long topicId;
	//题目类型 1单选题 2多选题  
	@ApiModelProperty(notes = "题目类型 1单选题 2多选题  ")
	@Column(name="ITEM_TYPE")
	private Integer itemType;
	//题目内容
	@ApiModelProperty(notes = "题目内容")
	@Column(name="ITEM_CONTENT")
	private String itemContent;
	//题目答案
	@ApiModelProperty(notes = "题目答案")
	@Column(name="ITEM_ANSWER")
	private String itemAnswer;
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
	
	public Integer getItemType(){
		return itemType;
	}
	
	public void setItemType(Integer itemType){
		this.itemType = itemType;
	}
	
	public String getItemContent(){
		return itemContent;
	}
	
	public void setItemContent(String itemContent){
		this.itemContent = itemContent;
	}
	
	public String getItemAnswer(){
		return itemAnswer;
	}
	
	public void setItemAnswer(String itemAnswer){
		this.itemAnswer = itemAnswer;
	}
	
}