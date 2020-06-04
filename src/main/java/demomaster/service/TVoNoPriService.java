package demomaster.service;


import java.util.List;

import demomaster.vo.TVoNoPriVo;
import demomaster.vo.plugin.TVoNoPriPriVo;
import demomaster.vo.plugin.TVoNoPriNoPriVo;
import demomaster.vo.TVoNoPriMultiTermVo;


/**
 * 表名称      :t_vo_no_pri
 * 表类型      :BASE TABLE
 * 表引擎      :InnoDB
 * 表版本      :10
 * 行格式      :Compact
 * 表创建      :2020-6-5
 * 字符集      :utf8_bin
 * 表注释      :
 */
public interface TVoNoPriService {

    /**
     * insert
     */
    boolean insert(TVoNoPriVo vo);


    /**
     * insert vos 批量插入
     */
    boolean insert(List<TVoNoPriVo> vos);


    /**
     * 查询base
     */
    List<TVoNoPriVo> queryBase(TVoNoPriVo query);

    /**
     * 查询base 多维条件
     */
    List<TVoNoPriVo> queryMultiTerm(TVoNoPriMultiTermVo query);

    /**
     * update base (exclude value is null or "")
     */
    boolean updateBase(TVoNoPriVo source, TVoNoPriVo target);

    /**
     * update base (include value is null or "")
     */
    boolean updateBaseIncludeNull(TVoNoPriVo source, TVoNoPriVo target);

    /**
     * 删除base
     */
    boolean deleteBase(TVoNoPriVo vo);


    /**
     * 根据PrimaryKey查询
     * <p>
     * dbName : 库名
     */
     TVoNoPriVo queryByPrimaryKey(String dbName);

    /**
     * 根据PrimaryKey删除
     * <p>
     * dbName : 库名
     * @param dbName
     * 
     */
    boolean deleteByPrimaryKey(String dbName);

    /**
     * 根据PrimaryKey更新，会根据主键去更新其他的值(空值不覆盖有值) -> 主键不更新
     * <p>
     * dbName : 库名
     * @param dbName
     * 
     */
    boolean updateByPrimaryKey(TVoNoPriNoPriVo source, TVoNoPriPriVo target);



}