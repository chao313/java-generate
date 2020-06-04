package demomaster.serviceImpl;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import demomaster.vo.TMulltiTermVoVo;
import demomaster.dao.TMulltiTermVoDAO;
import demomaster.service.TMulltiTermVoService;
import demomaster.vo.plugin.TMulltiTermVoPriVo;
import demomaster.vo.plugin.TMulltiTermVoNoPriVo;
import demomaster.vo.TMulltiTermVoMultiTermVo;



/**
 * 表名称      :t_mullti_term_vo
 * 表类型      :BASE TABLE
 * 表引擎      :InnoDB
 * 表版本      :10
 * 行格式      :Compact
 * 表创建      :2020-6-5
 * 字符集      :utf8_bin
 * 表注释      :
 */
@Service
public class TMulltiTermVoServiceImpl implements TMulltiTermVoService {

    @Autowired
    private TMulltiTermVoDAO dao;

    /**
     * insert
     */
    @Override
    public boolean insert(TMulltiTermVoVo vo) {

        return dao.insert(vo) > 0 ? true : false;

    }

    /**
     * insert vos 批量插入
     */
    @Override
    public boolean insert(List<TMulltiTermVoVo> vos) {

        return dao.inserts(vos) > 0 ? true : false;

    }

    /**
     * 查询base
     */
    @Override
    public List<TMulltiTermVoVo> queryBase(TMulltiTermVoVo query) {

        return dao.queryBase(query);

    }

    /**
     * 查询base 多维条件
     */
    @Override
    public List<TMulltiTermVoVo> queryMultiTerm(TMulltiTermVoMultiTermVo query) {

        return dao.queryMultiTerm(query);

    }

    /**
     * update base (exclude value is null or "")
     */
    @Override
    public boolean updateBase(TMulltiTermVoVo source, TMulltiTermVoVo target) {

        return dao.updateBase(source, target) > 0 ? true : false;

    }

    /**
     * update base (include value is null or "")
     */
    @Override
    public boolean updateBaseIncludeNull(TMulltiTermVoVo source, TMulltiTermVoVo target) {

        return dao.updateBaseIncludeNull(source, target) > 0 ? true : false;

    }

    /**
     * 删除base
     */
    @Override
    public boolean deleteBase(TMulltiTermVoVo vo) {

        return dao.deleteBase(vo) > 0 ? true : false;

    }


    /**
     * 根据PrimaryKey查询
     * <p>
     * dbName  库名<p>
     * dbTableName  表名
     */
    @Override
    public TMulltiTermVoVo queryByPrimaryKey(String dbName, String dbTableName) {

        return dao.queryByPrimaryKey(dbName, dbTableName);

    }

    /**
     * 根据PrimaryKey删除
     * <p>
     * dbName : 库名<p>
     * dbTableName : 表名
     * @param dbName
     * @param dbTableName
     * 
     */
    @Override
    public boolean deleteByPrimaryKey(String dbName, String dbTableName) {

        return dao.deleteByPrimaryKey(dbName, dbTableName) > 0 ? true : false;

    }

    /**
     * 根据PrimaryKey更新，会根据主键去更新其他的值(空值不覆盖有值)
     * <p>
     * dbName : 库名<p>
     * dbTableName : 表名
     * @param dbName
     * @param dbTableName
     * 
     */
    @Override
    public boolean updateByPrimaryKey(TMulltiTermVoNoPriVo source,TMulltiTermVoPriVo target){

        return dao.updateByPrimaryKey(source,target) > 0 ? true : false;

    }


}
