package com.mn.sysbusinesscode.entity.vo;

import java.io.Serializable;

/**
 * @ClassName:     SysBusinessCodeVo.java
 * @Description:   业务流水号的Vo类
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2020-08-26 23:52:45
 */
public class SysBusinessCodeVo implements Serializable{
	private static final long serialVersionUID = 1L;
	//主键
	private Long id;
	//
	private String codeTag;
	//前缀
	private String codePrefixion;
	//后缀
	private String codeSuffix;
	//当前值
	private Integer currentVal;
	//流水类型:0无,1按日流水,2按月流水,3按年流水
	private Integer codeType;
	//流水类型中文
	private String codeTypeName;
	//流水号总长度
	private Integer codeLength;
	//描述
	private String codeDesc;

	//set get method
	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public String getCodeTag(){
		return codeTag;
	}

	public void setCodeTag(String codeTag){
		this.codeTag = codeTag;
	}

	public String getCodePrefixion(){
		return codePrefixion;
	}

	public void setCodePrefixion(String codePrefixion){
		this.codePrefixion = codePrefixion;
	}

	public String getCodeSuffix(){
		return codeSuffix;
	}

	public void setCodeSuffix(String codeSuffix){
		this.codeSuffix = codeSuffix;
	}

	public Integer getCurrentVal(){
		return currentVal;
	}

	public void setCurrentVal(Integer currentVal){
		this.currentVal = currentVal;
	}

	public Integer getCodeType(){
		return codeType;
	}

	public void setCodeType(Integer codeType){
		this.codeType = codeType;
	}

	public Integer getCodeLength(){
		return codeLength;
	}

	public void setCodeLength(Integer codeLength){
		this.codeLength = codeLength;
	}

	public String getCodeDesc(){
		return codeDesc;
	}

	public void setCodeDesc(String codeDesc){
		this.codeDesc = codeDesc;
	}

	public String getCodeTypeName() {
		return codeTypeName;
	}

	public void setCodeTypeName(String codeTypeName) {
		this.codeTypeName = codeTypeName;
	}
}