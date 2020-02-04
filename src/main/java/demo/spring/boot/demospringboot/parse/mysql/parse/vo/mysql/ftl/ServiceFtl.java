package demo.spring.boot.demospringboot.parse.mysql.parse.vo.mysql.ftl;

import demo.spring.boot.demospringboot.parse.mysql.parse.vo.MysqlField;
import demo.spring.boot.demospringboot.parse.mysql.parse.vo.mysql.Table;
import lombok.Data;

import java.util.List;

/**
 * 准备和FTL绑定的
 */
@Data
public class ServiceFtl implements FtlInterface {
    private String packageName;//包名
    private String dir = "service";//文件夹名称
    private String className;//class名称
    private String fileName;//file名称
    private String freeMarkFtlPath = "Service.ftl";//生成FreeMark的ftlPath
    private String freeMarkStr;//生成FreeMark的str
    private List<MysqlField> primaryKeyFields;//主键字段
    private List<MysqlField> fields;//全部主键字段
    /**
     * 包含:表名称,表类型,引擎,表的字符集,表的注释
     */
    private Table table;//包含表
}
