package demo.spring.boot.demospringboot.demo2.dao;


import java.util.List;

import demo.spring.boot.demospringboot.demo2.vo.THostVo;

import org.apache.ibatis.annotations.Param;

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
public interface THostDAO {

    /**
     * insert
     */
    int insert(THostVo vo);

    /**
     * insert vos 批量插入
     */
    int inserts(@Param(value = "vos") List<THostVo> vos);

    /**
     * 查询base
     */
    List<THostVo> queryBase(THostVo query);

    /**
     * update base (exclude value is null or "")
     */
    int updateBase(@Param(value = "source") THostVo source, @Param(value = "target") THostVo target);


    /**
     * update base (include value is null or "")
     */
    int updateBaseIncludeNull(@Param(value = "source") THostVo source, @Param(value = "target") THostVo target);

    /**
     * 删除base
     */
    int deleteBase(THostVo vo);


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
    int deleteByPrimaryKey(Integer id, String account);

}
