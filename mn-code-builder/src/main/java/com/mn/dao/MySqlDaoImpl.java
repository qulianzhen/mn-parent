package com.mn.dao;

import com.mn.entity.BuliderColumn;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName:     MySqlDaoImpl.java
 * @Description:   查询mysql数据库得到表信息
 * 
 * @author         qlz
 * @version        V1.0  
 * @Date           2016年6月13日 上午11:12:11 
 */
@Repository("mySqlDao")
public class MySqlDaoImpl extends BaseDaoImpl<BuliderColumn> implements TableDao{

	
	@Override
	public List<BuliderColumn> getColumnMessByTabelName(String tableName, String schemaName) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sbd = new StringBuilder();
		/*mysql的查询方法*/
		sbd.append(" SELECT                                   ");
		sbd.append(" 	column_name,                          ");
		sbd.append(" 	data_type,                            ");
		sbd.append(" 	CHARACTER_MAXIMUM_LENGTH data_length, ");
		sbd.append(" 	COLUMN_COMMENT comments,              ");
		sbd.append(" 	IS_NULLABLE nullable                  ");
		sbd.append(" FROM                                     ");
		sbd.append(" 	information_schema.COLUMNS            ");
		sbd.append(" WHERE                                    ");
		sbd.append(" 	table_name = ?  and TABLE_SCHEMA=?    ");
		sbd.append(" 	order by ORDINAL_POSITION             ");//按照字段原始顺序排序

		return this.finds(sbd.toString(),new BuliderColumn(),tableName,schemaName);
	}

	@Override
	public String getPkByTableName(String tableName, String schemaName)throws Exception {
		
		StringBuilder sbd = new StringBuilder();
		sbd.append(" SELECT                        ");
		sbd.append(" 	column_name                ");
		sbd.append(" FROM                          ");
		sbd.append(" 	information_schema.COLUMNS ");
		sbd.append(" WHERE                         ");
		sbd.append(" 	table_name = ?             ");
		sbd.append("   and COLUMN_KEY = 'PRI'      ");
		sbd.append("   and TABLE_SCHEMA=?          ");
		return this.getJdbcTemplate().queryForObject(sbd.toString(), String.class, tableName,schemaName);
	}

	@Override
	public String getTableDesc(String tableName,String schemaName) {
		String sql = "select table_comment comments from information_schema.tables  where  table_name =? and TABLE_SCHEMA=? ";
		return this.getJdbcTemplate().queryForObject(sql, String.class, tableName,schemaName);
	}

}
