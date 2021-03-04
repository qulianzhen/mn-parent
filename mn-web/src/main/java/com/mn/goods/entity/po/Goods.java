package com.mn.goods.entity.po;
import java.io.Serializable;
/**
 * @ClassName:     Goods.java
 * @Description:   商品管理的实体Bean
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2021-03-05 00:18:38
 */
public class Goods implements Serializable {
	private static final long serialVersionUID = 1L;
	//主键
	private Long id;
	//商品编号
	private String goodsNo;
	//商品名称
	private String goodsName;
	//商品描述
	private String goodsDesc;
	//图片1
	private String goodsImg1;
	//图片2
	private String goodsImg2;
	//set get method
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	public String getGoodsNo(){
		return goodsNo;
	}
	
	public void setGoodsNo(String goodsNo){
		this.goodsNo = goodsNo;
	}
	
	public String getGoodsName(){
		return goodsName;
	}
	
	public void setGoodsName(String goodsName){
		this.goodsName = goodsName;
	}
	
	public String getGoodsDesc(){
		return goodsDesc;
	}
	
	public void setGoodsDesc(String goodsDesc){
		this.goodsDesc = goodsDesc;
	}
	
	public String getGoodsImg1(){
		return goodsImg1;
	}
	
	public void setGoodsImg1(String goodsImg1){
		this.goodsImg1 = goodsImg1;
	}
	
	public String getGoodsImg2(){
		return goodsImg2;
	}
	
	public void setGoodsImg2(String goodsImg2){
		this.goodsImg2 = goodsImg2;
	}
	
}