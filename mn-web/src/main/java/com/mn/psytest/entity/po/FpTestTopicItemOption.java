package com.mn.psytest.entity.po;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * @ClassName:     FpTestTopicItemOption.java
 * @Description:   测试题_题目明细_选项的实体Bean
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2019-03-11 22:50:43
 */
@ApiModel(value="测试题_题目明细_选项Model",description="测试题_题目明细_选项对象数据模型")
@Table(name="fp_test_topic_item_option")
public class FpTestTopicItemOption implements Serializable {
	private static final long serialVersionUID = 1L;
	//主键
	@ApiModelProperty(notes = "主键")
	@Column(name="ID")
	private Long id;
	//题目明细ID
	@ApiModelProperty(notes = "题目明细ID")
	@Column(name="TOPIC_ITEM_ID")
	private Long topicItemId;
	//选项内容
	@ApiModelProperty(notes = "选项内容")
	@Column(name="OPTION_CONTENT")
	private String optionContent;
	//set get method
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getTopicItemId(){
		return topicItemId;
	}
	
	public void setTopicItemId(Long topicItemId){
		this.topicItemId = topicItemId;
	}
	
	public String getOptionContent(){
		return optionContent;
	}
	
	public void setOptionContent(String optionContent){
		this.optionContent = optionContent;
	}
	
}