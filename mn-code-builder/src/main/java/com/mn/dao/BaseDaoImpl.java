package com.mn.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.orm.hibernate4.HibernateTemplate;

import javax.annotation.Resource;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class BaseDaoImpl<T> implements BaseDao<T> {
    /**
     * 设置一些操作的常量
     */
    public static final String SQL_INSERT = "insert";
    public static final String SQL_UPDATE = "update";
    public static final String SQL_DELETE = "delete";

    @Resource
    private HibernateTemplate hibernateTemplate;

    @Resource
    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private Class<T> entityClass;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    private void setNamedParameterJdbcTemplate() {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
    }

    public BaseDaoImpl() {
        ParameterizedType type = (ParameterizedType) getClass()
                .getGenericSuperclass();
        entityClass = (Class<T>) type.getActualTypeArguments()[0];
    }


    public Serializable save(T entity) throws Exception {
        return this.hibernateTemplate.save(entity);
    }


    public void update(T entity) throws Exception {
        hibernateTemplate.update(entity);
    }


    public void merge(T entity) throws Exception {
        hibernateTemplate.merge(entity);
    }


    public void saveOrUpdate(T entity) throws Exception {
        hibernateTemplate.saveOrUpdate(entity);
    }


    public void delete(T entity) throws Exception {
        hibernateTemplate.delete(entity);
    }


    public void delete(Serializable id) throws Exception {
        hibernateTemplate.delete(get(id));
    }


    public void delete(List<T> list) throws Exception {
        hibernateTemplate.deleteAll(list);
    }


    public void deleteAll() throws Exception {
        String sql = " TRUNCATE TABLE " + entityClass.getAnnotation(Entity.class).name();
        jdbcTemplate.update(sql);
    }


    public T get(Serializable id) throws Exception {
        return hibernateTemplate.get(entityClass, id);
    }


    public <T> T get(T T, Serializable id) throws Exception {
        String sql = "SELECT * FROM " + entityClass.getAnnotation(Entity.class).name()
                + " WHERE id=?";
        return (T) jdbcTemplate.queryForObject(sql,
                BeanPropertyRowMapper.newInstance(T.getClass()), id);
    }


    public <T> T get(String sql, T T, Object... param) throws Exception {
        Object[] obj = new Object[param.length];
        for (int i = 0; i < param.length; i++) {
            obj[i] = param[i];
        }
        List<T> list = (List<T>) jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(T.getClass()), obj);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public <T> List<T> finds(String sql, T T) throws Exception {
        RowMapper<T> rowMapper = (RowMapper<T>) BeanPropertyRowMapper
                .newInstance(T.getClass());
        return jdbcTemplate.query(sql.toString(), rowMapper);
    }


    public <T> List<T> finds(String sql, T T, Object... param) throws Exception {
        Object[] args = new Object[param.length];
        for (int i = 0; i < args.length; i++) {
            args[i] = param[i];
        }
        RowMapper<T> rowMapper = (RowMapper<T>) BeanPropertyRowMapper
                .newInstance(T.getClass());
        return jdbcTemplate.query(sql.toString(), args, rowMapper);
    }


    public void batchSaveOrUpdate(List<T> list) throws Exception {
        SessionFactory sf = hibernateTemplate.getSessionFactory();
        Session session = sf.openSession();
        session.getTransaction().begin(); // 开启事务
        for (int i = 0; i < list.size(); i++) {
            T t = list.get(i);
            session.saveOrUpdate(t);
            if (i % 50 == 0) {
                session.flush();
                session.clear();
            }
        }
        session.getTransaction().commit(); // 提交事务
    }

    public void batchSave(List<T> list) throws Exception {
        SessionFactory sf = hibernateTemplate.getSessionFactory();
        Session session = sf.openSession();
        session.getTransaction().begin(); // 开启事务
        for (int i = 0; i < list.size(); i++) {
            T t = list.get(i);
            session.save(t);
            if (i % 50 == 0) {
                session.flush();
                session.clear();
            }
        }
        session.getTransaction().commit(); // 提交事务
    }

    public int[] batchJdbcSave(List<T> list) throws Exception {
        String sql = this.makeSql(SQL_INSERT);
        if (namedParameterJdbcTemplate == null) {
            setNamedParameterJdbcTemplate();
        }
        return namedParameterJdbcTemplate.batchUpdate(sql, SqlParameterSourceUtils.createBatch(list.toArray()));
    }

    public int[] batchJdbcUpdate(List<T> list) throws Exception {
        String sql = this.makeSql(SQL_UPDATE);
        if (namedParameterJdbcTemplate == null) {
            setNamedParameterJdbcTemplate();
        }
        return namedParameterJdbcTemplate.batchUpdate(sql, SqlParameterSourceUtils.createBatch(list.toArray()));
    }


    public int count(String sql, Object... param) throws Exception {
        Integer count = Integer.valueOf(hibernateTemplate.find(sql, param).listIterator().next().toString());
        return count.intValue();
    }


    public int count() throws Exception {
        String sql = "SELECT COUNT(*) FROM " + entityClass.getAnnotation(Entity.class).name();
        Integer count = Integer.valueOf(hibernateTemplate.find(sql).listIterator().next().toString());
        return count.intValue();
    }

    /**
     * 带条件的 纯sql 获取 总数
     */
    public int getCount(String sql, Object... param) throws Exception {
        int count = jdbcTemplate.queryForInt(sql, param);
        return count;
    }


    /**
     * 组装SQL
     *
     * @param sqlFlag 类别（属于insert update delete）
     * @return
     * @throws Exception
     */
    private String makeSql(String sqlFlag) throws Exception {
        StringBuffer sql = new StringBuffer();
        Field[] fields = entityClass.getDeclaredFields();
        String entityName = entityClass.getAnnotation(Entity.class).name();
        if (sqlFlag.equals(SQL_INSERT)) {
            sql.append(" INSERT INTO " + entityName);
            sql.append("(");
            int number = 0;
            for (int i = 0; fields != null && i < fields.length; i++) {
                fields[i].setAccessible(true); // 暴力反射
                String column = fields[i].getName();
                if (column.equals("id")) {
                    number = i;
                    sql.append(column).append(",");
                } else {
                    sql.append(fields[i].getAnnotation(Column.class).name()).append(",");
                }
            }
            sql = sql.deleteCharAt(sql.length() - 1);
            sql.append(") VALUES (");
            for (int i = 0; fields != null && i < fields.length; i++) {
                fields[i].setAccessible(true); // 暴力反射
                if (number == i) {
                    String seqName = entityClass.getAnnotation(SequenceGenerator.class).name();
                    sql.append(seqName.toUpperCase() + ".Nextval");
                } else {
                	//fields[i].getGenericType().getTypeName().toLowerCase().indexOf("boolean") != -1    java8写法
                    if (fields[i].getGenericType().toString().toLowerCase().indexOf("boolean") != -1) {
                        sql.append("1");
                    } else {
                        String column = fields[i].getName();
                        sql.append(":").append(column);
                    }
                }
                sql.append(",");
            }
            sql = sql.deleteCharAt(sql.length() - 1);
            sql.append(")");
        } else if (sqlFlag.equals(SQL_UPDATE)) {
            sql.append(" UPDATE " + entityName + " SET ");
            for (int i = 0; fields != null && i < fields.length; i++) {
                fields[i].setAccessible(true); // 暴力反射
                String column = fields[i].getName();
                if (column.equals("id")) { // id 代表主键
                    continue;
                }
                //fields[i].getGenericType().getTypeName().toLowerCase().indexOf("boolean") != -1    java8写法
                if (fields[i].getGenericType().toString().toLowerCase().indexOf("boolean") == -1) {
                    sql.append(fields[i].getAnnotation(Column.class).name()).append("=").append(":").append(column);
                    sql.append(",");
                }
            }
            sql = sql.deleteCharAt(sql.length() - 1);
            sql.append(" WHERE id=:id");
        } else if (sqlFlag.equals(SQL_DELETE)) {
            sql.append(" DELETE FROM " + entityName + " WHERE id=?");
        }
        return sql.toString();

    }


}