package demomaster.service;


import java.util.List;

import demomaster.vo.TMapperVo;
import demomaster.vo.plugin.TMapperPriVo;
import demomaster.vo.plugin.TMapperNoPriVo;
import demomaster.vo.TMapperMultiTermVo;


/**
 * 表名称      :t_mapper
 * 表类型      :BASE TABLE
 * 表引擎      :InnoDB
 * 表版本      :10
 * 行格式      :Compact
 * 表创建      :2020-6-6
 * 字符集      :utf8_bin
 * 表注释      :
 */
public interface TMapperService {

    /**
     * insert
     */
    boolean insert(TMapperVo vo);


    /**
     * insert vos 批量插入
     */
    boolean insert(List<TMapperVo> vos);


    /**
     * 查询base
     */
    List<TMapperVo> queryBase(TMapperVo query);

    /**
     * 查询base 多维条件
     */
    List<TMapperVo> queryMultiTerm(TMapperMultiTermVo query);

    /**
     * update base (exclude value is null or "")
     */
    boolean updateBase(TMapperVo source, TMapperVo target);

    /**
     * update base (include value is null or "")
     */
    boolean updateBaseIncludeNull(TMapperVo source, TMapperVo target);

    /**
     * 删除base
     */
    boolean deleteBase(TMapperVo vo);


    /**
     * 根据PrimaryKey查询
     * <p>
     * dbName : 库名<p>
     * dbTableName : 表名
     */
     TMapperVo queryByPrimaryKey(String dbName,String dbTableName);

    /**
     * 根据PrimaryKey删除
     * <p>
     * dbName : 库名<p>
     * dbTableName : 表名
     * @param dbName
     * @param dbTableName
     * 
     */
    boolean deleteByPrimaryKey(String dbName,String dbTableName);

    /**
     * 根据PrimaryKey更新，会根据主键去更新其他的值(空值不覆盖有值) -> 主键不更新
     * <p>
     * dbName : 库名<p>
     * dbTableName : 表名
     * @param dbName
     * @param dbTableName
     * 
     */
    boolean updateByPrimaryKey(TMapperNoPriVo source,TMapperPriVo target);



}
