package demomaster.serviceImpl;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import demomaster.vo.TVoPriVo;
import demomaster.dao.TVoPriDAO;
import demomaster.service.TVoPriService;
import demomaster.vo.plugin.TVoPriPriVo;
import demomaster.vo.plugin.TVoPriNoPriVo;
import demomaster.vo.TVoPriMultiTermVo;



/**
 * 表名称      :t_vo_pri
 * 表类型      :BASE TABLE
 * 表引擎      :InnoDB
 * 表版本      :10
 * 行格式      :Compact
 * 表创建      :2020-6-5
 * 字符集      :utf8_bin
 * 表注释      :
 */
@Service
public class TVoPriServiceImpl implements TVoPriService {

    @Autowired
    private TVoPriDAO dao;

    /**
     * insert
     */
    @Override
    public boolean insert(TVoPriVo vo) {

        return dao.insert(vo) > 0 ? true : false;

    }

    /**
     * insert vos 批量插入
     */
    @Override
    public boolean insert(List<TVoPriVo> vos) {

        return dao.inserts(vos) > 0 ? true : false;

    }

    /**
     * 查询base
     */
    @Override
    public List<TVoPriVo> queryBase(TVoPriVo query) {

        return dao.queryBase(query);

    }

    /**
     * 查询base 多维条件
     */
    @Override
    public List<TVoPriVo> queryMultiTerm(TVoPriMultiTermVo query) {

        return dao.queryMultiTerm(query);

    }

    /**
     * update base (exclude value is null or "")
     */
    @Override
    public boolean updateBase(TVoPriVo source, TVoPriVo target) {

        return dao.updateBase(source, target) > 0 ? true : false;

    }

    /**
     * update base (include value is null or "")
     */
    @Override
    public boolean updateBaseIncludeNull(TVoPriVo source, TVoPriVo target) {

        return dao.updateBaseIncludeNull(source, target) > 0 ? true : false;

    }

    /**
     * 删除base
     */
    @Override
    public boolean deleteBase(TVoPriVo vo) {

        return dao.deleteBase(vo) > 0 ? true : false;

    }


    /**
     * 根据PrimaryKey查询
     * <p>
     * dbName  库名
     */
    @Override
    public TVoPriVo queryByPrimaryKey(String dbName) {

        return dao.queryByPrimaryKey(dbName);

    }

    /**
     * 根据PrimaryKey删除
     * <p>
     * dbName : 库名
     * @param dbName
     * 
     */
    @Override
    public boolean deleteByPrimaryKey(String dbName) {

        return dao.deleteByPrimaryKey(dbName) > 0 ? true : false;

    }

    /**
     * 根据PrimaryKey更新，会根据主键去更新其他的值(空值不覆盖有值)
     * <p>
     * dbName : 库名
     * @param dbName
     * 
     */
    @Override
    public boolean updateByPrimaryKey(TVoPriNoPriVo source,TVoPriPriVo target){

        return dao.updateByPrimaryKey(source,target) > 0 ? true : false;

    }


}