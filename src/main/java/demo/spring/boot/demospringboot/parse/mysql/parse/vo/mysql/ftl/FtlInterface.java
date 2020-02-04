package demo.spring.boot.demospringboot.parse.mysql.parse.vo.mysql.ftl;

import demo.spring.boot.demospringboot.parse.mysql.parse.vo.MysqlField;
import demo.spring.boot.demospringboot.parse.mysql.parse.vo.mysql.Table;

import java.util.List;

/**
 * 接口,由Ftl继承
 */
public interface FtlInterface {


    String getPackageName();

    String getDir();


    String getClassName();


    String getFileName();


    String getFreeMarkFtlPath();


    String getFreeMarkStr();

    void setFreeMarkStr(String freeMarkStr);


    List<MysqlField> getPrimaryKeyFields();


    List<MysqlField> getFields();

    Table getTable();

}
