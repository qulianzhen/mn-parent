package com.mn.commonbean.tkmapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 自定义接口，选择性继承通用Mapper的接口：
 * Mapper接口：基本的增、删、改、查方法
 * MySqlMapper：针对MySQL的额外补充接口，支持批量插入
 * 该接口跟普通的业务Mapper.java类不一样，不能被扫描，配置注解@MapperScan(basePackages = {"yiche.com.mapper"})时要注意
 * @param <T>
 */
public interface MyMapper<T> extends Mapper<T>,MySqlMapper<T> {
}
