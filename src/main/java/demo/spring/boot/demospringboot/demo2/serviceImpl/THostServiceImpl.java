package demo.spring.boot.demospringboot.demo2.serviceImpl;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import demo.spring.boot.demospringboot.demo2.vo.THostVo;
import demo.spring.boot.demospringboot.demo2.dao.THostDAO;
import demo.spring.boot.demospringboot.demo2.service.THostService;


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
@Service
public class THostServiceImpl implements THostService {

    @Autowired
    private THostDAO dao;

    /**
     * insert
     */
    @Override
    public boolean insert(THostVo vo) {

        return dao.insert(vo) > 0 ? true : false;

    }

    /**
     * insert vos 批量插入
     */
    @Override
    public boolean insert(List<THostVo> vos) {

        return dao.inserts(vos) > 0 ? true : false;

    }

    /**
     * 查询base
     */
    @Override
    public List<THostVo> queryBase(THostVo query) {

        return dao.queryBase(query);

    }

    /**
     * update base (exclude value is null or "")
     */
    @Override
    public boolean updateBase(THostVo source, THostVo target) {

        return dao.updateBase(source, target) > 0 ? true : false;

    }

    /**
     * update base (include value is null or "")
     */
    @Override
    public boolean updateBaseIncludeNull(THostVo source, THostVo target) {

        return dao.updateBaseIncludeNull(source, target) > 0 ? true : false;

    }

    /**
     * 删除base
     */
    @Override
    public boolean deleteBase(THostVo vo) {

        return dao.deleteBase(vo) > 0 ? true : false;

    }


    /**
     * 根据PrimaryKey查询
     * <p>
     * id  id<p>
     * account  account
     */
    @Override
    public THostVo queryByPrimaryKey(Integer id, String account) {

        return dao.queryByPrimaryKey(id, account);

    }

    /**
     * 根据PrimaryKey删除
     * <p>
     * id : id<p>
     * account : account
     */
    @Override
    public boolean deleteByPrimaryKey(Integer id, String account) {

        return dao.deleteByPrimaryKey(id, account) > 0 ? true : false;

    }

}
