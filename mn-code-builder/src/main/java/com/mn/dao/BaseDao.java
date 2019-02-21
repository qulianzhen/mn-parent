package com.mn.dao;


import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {

    /**
     * 添加
     *
     * @param entity 实体对象
     * @throws Exception
     */
    Serializable save(T entity) throws Exception;

    /**
     * 更新
     *
     * @param entity 实体对象
     * @throws Exception
     */
    void update(T entity) throws Exception;

    /**
     * 更新
     *
     * @param entity 实体对象
     * @throws Exception
     */
    void merge(T entity) throws Exception;

    /**
     * 保存或者更新
     *
     * @param entity
     * @throws Exception
     */
    void saveOrUpdate(T entity) throws Exception;

    /**
     * 删除
     *
     * @param entity 实体对象
     * @throws Exception
     */
    void delete(T entity) throws Exception;

    /**
     * 根据ID 删除
     *
     * @param id
     * @throws Exception
     */
    void delete(Serializable id) throws Exception;

    /**
     * 批量删除
     *
     * @param list
     * @throws Exception
     */
    void delete(List<T> list) throws Exception;

    /**
     * 删除所有
     *
     * @throws Exception
     */
    void deleteAll() throws Exception;

    /**
     * 读取单个对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    T get(Serializable id) throws Exception;

    /**
     * 根据ID去取实体 需要new一个对象
     *
     * @param T  需要new一个对象
     * @param id 对象的ID
     * @return
     * @throws Exception
     */
    <T> T get(T T, Serializable id) throws Exception;

    /**
     * 传参 多个固定参数
     *
     * @param sql
     * @param T
     * @param param
     * @return
     * @throws Exception
     */
    <T> T get(String sql, T T, Object... param) throws Exception;

    /**
     * 获取继承后对象的list结果
     *
     * @param sql
     * @param param ：固定参数
     * @return
     * @throws Exception
     */
    <T> List<T> finds(String sql, T T, Object... param) throws Exception;

    /**
     * 批量保存或者修改
     *
     * @param list 要保存的对象集合
     * @throws Exception
     */
    void batchSaveOrUpdate(List<T> list) throws Exception;

    /**
     * 批量保存
     *
     * @param list 要保存的对象集合
     * @throws Exception
     */
    void batchSave(List<T> list) throws Exception;

    /**
     * jdbc批量保存
     *
     * @param list 要保存的对象集合
     * @throws Exception
     */
    int[] batchJdbcSave(List<T> list) throws Exception;

    /**
     * jdbc批量修改
     *
     * @param list 要保存的对象集合
     * @throws Exception
     */
    int[] batchJdbcUpdate(List<T> list) throws Exception;

    /**
     * 读取count数值
     *
     * @param sql
     * @param param
     * @return
     * @throws Exception
     */
    int count(String sql, Object... param) throws Exception;

    /**
     * 读取count数值
     *
     * @return
     * @throws Exception
     */
    int count() throws Exception;

    int getCount(String sql, Object... param) throws Exception;
}