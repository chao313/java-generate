package demomaster.vo.plugin;


/**
 * 这里属性是主键字段
 *
 * 表名称      :t_vo_pri
 * 表类型      :BASE TABLE
 * 表引擎      :InnoDB
 * 表版本      :10
 * 行格式      :Compact
 * 表创建      :2020-6-5
 * 字符集      :utf8_bin
 * 表注释      :
 */
public class TVoPriPriVo {

    private String dbName;  // 库名 


    public String getDbName() {

        return dbName;

    }

    public void setDbName(String dbName) {

        this.dbName = dbName;

    }

    @Override
    public String toString() {
        return "TVoPriPriVo{" +
                ", dbName '" + dbName + '\'' +
                '}';
    }

}
