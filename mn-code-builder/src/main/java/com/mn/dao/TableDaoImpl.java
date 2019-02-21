package com.mn.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mn.entity.BuliderColumn;
import com.mn.mnutils.PropertiesTools2;
import com.mn.utils.PropertiesUtil;


/**
 * @ClassName:     TableDaoImpl.java
 * @Description:   TODO(用一句话描述该文件做什么) 
 * 
 * @author         qlz
 * @version        V1.0  
 * @Date           2016年6月13日 上午11:12:11 
 */
@Repository("tableDao")
public class TableDaoImpl extends BaseDaoImpl<BuliderColumn> implements TableDao{

	
	@Override
	public List<BuliderColumn> getColumnMessByTabelName(String tableName,String owner,String schemaName) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sbd = new StringBuilder();
		/*oracle查询方法*/
		/*sbd.append("select column_name,                            ");
		sbd.append("       data_type,                              ");
		sbd.append("       data_length,                            ");
		sbd.append("       (select t_s.comments                    ");
		sbd.append("          from all_col_comments t_s            ");
		sbd.append("         where t_s.column_name = t.column_name ");
		sbd.append("           and t_s.table_name = upper(?)       ");
		sbd.append("           and t_s.owner = upper(?) ) comments ");
		sbd.append("  from all_tab_columns t                       ");
		sbd.append(" where table_name = upper(?)                   ");
		sbd.append("   and owner = upper(?)                        ");
		sbd.append(" order by column_id                            ");*/
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

		
		//String sql = "select COLUMN_NAME,DATA_TYPE from user_tab_columns where table_name = ?";
		/*oracle*/
		//return this.finds(sbd.toString(),new Table(),tableName,owner,tableName,owner);
		/*mysql*/
		return this.finds(sbd.toString(),new BuliderColumn(),tableName,schemaName);
	}

	@Override
	public String getPkByTableName(String tableName, String owner,String schemaName)
			throws Exception {
		
		StringBuilder sbd = new StringBuilder();
		/*oracle查询方法*/
		/*sbd.append("select a.column_name                         ");
		sbd.append("  from all_cons_columns a, all_constraints b ");
		sbd.append(" where a.constraint_name = b.constraint_name ");
		sbd.append("   and b.constraint_type = 'P'               ");
		sbd.append("   and a.table_name = ?                      ");
		sbd.append("   and a.OWNER = ?                           ");
		sbd.append("   and b.OWNER = ?                           ");*/
		/*mysql查询方法*/
		sbd.append(" SELECT                        ");
		sbd.append(" 	column_name                ");
		sbd.append(" FROM                          ");
		sbd.append(" 	information_schema.COLUMNS ");
		sbd.append(" WHERE                         ");
		sbd.append(" 	table_name = ?             ");
		sbd.append("   and COLUMN_KEY = 'PRI'      ");
		sbd.append("   and TABLE_SCHEMA=?          ");
		/*oracle*/
		//return this.getJdbcTemplate().queryForObject(sbd.toString(), String.class, tableName,owner,owner);
		/*mysql*/
		return this.getJdbcTemplate().queryForObject(sbd.toString(), String.class, tableName,schemaName);
	}

	@Override
	public String getTableDesc(String tableName, String owner,String schemaName) {
		// TODO Auto-generated method stub
		/*oracle*/
		//String sql = "select comments from all_tab_comments where table_name = upper(?) and owner = ?";
		/*mysql*/
		String sql = "select table_comment comments from information_schema.tables  where  table_name =? and TABLE_SCHEMA=? ";
		/*oracle*/
		//return this.getJdbcTemplate().queryForObject(sql, String.class, tableName,owner);
		/*mysql*/
		return this.getJdbcTemplate().queryForObject(sql, String.class, tableName,schemaName);
	}

}
