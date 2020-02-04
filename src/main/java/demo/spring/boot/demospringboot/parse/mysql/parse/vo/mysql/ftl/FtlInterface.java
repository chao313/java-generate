package demo.spring.boot.demospringboot.parse.mysql.parse.vo.mysql.ftl;

import demo.spring.boot.demospringboot.parse.mysql.parse.vo.mysql.JavaField;
import demo.spring.boot.demospringboot.parse.mysql.parse.vo.mysql.MysqlField;
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


    List<MysqlField> getPrimaryKeyMysqlFields();


    List<MysqlField> getMysqlFields();

    List<JavaField> getPrimaryKeyJavaFields();


    List<JavaField> getJavaFields();

    Table getTable();

}
