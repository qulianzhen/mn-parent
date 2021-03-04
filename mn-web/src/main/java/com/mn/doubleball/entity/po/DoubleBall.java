package com.mn.doubleball.entity.po;
import java.io.Serializable;
/**
 * @ClassName:     DoubleBall.java
 * @Description:   双色球历史记录的实体Bean
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2020-10-21 01:22:54
 */
public class DoubleBall implements Serializable {
	private static final long serialVersionUID = 1L;
	//主键
	private Long id;
	//期数
	private String dbNo;
	//开奖日期
	private String dbDate;
	//红球1
	private Integer dbRed1;
	//红球2
	private Integer dbRed2;
	//红球3
	private Integer dbRed3;
	//红球4
	private Integer dbRed4;
	//红球5
	private Integer dbRed5;
	//红球6
	private Integer dbRed6;
	//蓝球
	private Integer dbBlue;
	//备注
	private String dbRemark;
	//set get method
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	public String getDbNo(){
		return dbNo;
	}
	
	public void setDbNo(String dbNo){
		this.dbNo = dbNo;
	}
	
	public String getDbDate(){
		return dbDate;
	}
	
	public void setDbDate(String dbDate){
		this.dbDate = dbDate;
	}
	
	public Integer getDbRed1(){
		return dbRed1;
	}
	
	public void setDbRed1(Integer dbRed1){
		this.dbRed1 = dbRed1;
	}
	
	public Integer getDbRed2(){
		return dbRed2;
	}
	
	public void setDbRed2(Integer dbRed2){
		this.dbRed2 = dbRed2;
	}
	
	public Integer getDbRed3(){
		return dbRed3;
	}
	
	public void setDbRed3(Integer dbRed3){
		this.dbRed3 = dbRed3;
	}
	
	public Integer getDbRed4(){
		return dbRed4;
	}
	
	public void setDbRed4(Integer dbRed4){
		this.dbRed4 = dbRed4;
	}
	
	public Integer getDbRed5(){
		return dbRed5;
	}
	
	public void setDbRed5(Integer dbRed5){
		this.dbRed5 = dbRed5;
	}
	
	public Integer getDbRed6(){
		return dbRed6;
	}
	
	public void setDbRed6(Integer dbRed6){
		this.dbRed6 = dbRed6;
	}
	
	public Integer getDbBlue(){
		return dbBlue;
	}
	
	public void setDbBlue(Integer dbBlue){
		this.dbBlue = dbBlue;
	}
	
	public String getDbRemark(){
		return dbRemark;
	}
	
	public void setDbRemark(String dbRemark){
		this.dbRemark = dbRemark;
	}
	
}