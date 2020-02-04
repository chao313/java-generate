package demo.spring.boot.demospringboot.demo2.service;


import java.util.List;

import demo.spring.boot.demospringboot.demo2.vo.THostVo;

/**
 * 表名称      :t_host
 * 表类型      :BASE TABLE
 * 表引擎      :InnoDB
 * 表版本      :10
 * 行格式      :Dynamic
 * 表创建      :2020-2-4
 * 字符集      :utf8_croatian_ci
 * 表注释      :
 */
public interface THostService {

    /**
     * insert
     */
    boolean insert(THostVo vo);


    /**
     * insert vos 批量插入
     */
    boolean insert(List<THostVo> vos);


    /**
     * 查询base
     */
    List<THostVo> queryBase(THostVo query);


    /**
     * update base (exclude value is null or "")
     */
    boolean updateBase(THostVo source, THostVo target);

    /**
     * update base (include value is null or "")
     */
    boolean updateBaseIncludeNull(THostVo source, THostVo target);

    /**
     * 删除base
     */
    boolean deleteBase(THostVo vo);


    /**
     * 根据PrimaryKey查询
     * <p>
     * id : id<p>
     * account : account
     */
     THostVo queryByPrimaryKey(Integer id, String account);

    /**
     * 根据PrimaryKey删除
     * <p>
     * id : id<p>
     * account : account
     */
    boolean deleteByPrimaryKey(Integer id, String account);


}
