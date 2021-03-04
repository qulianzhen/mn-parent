package com.mn.goods.entity.param;
import java.io.Serializable;
/**
 * @ClassName:     GoodsParam.java
 * @Description:   商品管理的参数类
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2021-03-05 00:18:38
 */
public class GoodsParam implements Serializable{
	private static final long serialVersionUID = 1L;
	//查询条件...
	//主键
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}