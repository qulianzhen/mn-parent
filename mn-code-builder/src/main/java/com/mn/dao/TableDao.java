package com.mn.dao;

import com.mn.entity.BuliderColumn;

import java.util.List;


/**
 * @ClassName:     TabelDao.java
 * @Description:   TODO(用一句话描述该文件做什么) 
 * 
 * @author         33107
 * @version        V1.0  
 * @Date           2016年6月13日 上午10:53:48 
 */
public interface TableDao extends BaseDao<BuliderColumn>{
	/**
	 * 根据数据库名得到数据库字段和类型信息（orcl）
	 * @param tableName
	 * @param owner
	 * @param schemaName
	 * @return
	 */
	List<BuliderColumn> getColumnMessByTabelName(String tableName, String owner, String schemaName) throws Exception;

	/**
	 * 通过表名和所有者得到表的主键
	 * @param tableName
	 * @param owner
	 * @param schemaName
	 * @return
	 */
	String getPkByTableName(String tableName, String owner, String schemaName)throws Exception;

	/**
	 * 通过表名和所有者得到表描述信息
	 * @param tableName
	 * @param owner
	 * @param schemaName
	 * @return
	 */
	String getTableDesc(String tableName, String owner, String schemaName);

}
