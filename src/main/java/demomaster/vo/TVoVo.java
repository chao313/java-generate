package demomaster.vo;




/**
 * 表名称      :t_vo
 * 表类型      :BASE TABLE
 * 表引擎      :InnoDB
 * 表版本      :10
 * 行格式      :Compact
 * 表创建      :2020-6-5
 * 字符集      :utf8_bin
 * 表注释      :
 */
public class TVoVo {

    private String dbName;  // 库名 
    private String dbTableName;  // 表名 
    private String dbTableComment;  // 表注释 
    private String voPackage;  // 包名 
    private String voClassName;  // 类名 
    private String voFileName;  // 文件名 
    private String voFreemarkPath;  // freemark路径 
    private String voImports;  // vo 需要注入的类型 ,逗号分隔 


    public String getDbName() {

        return dbName;

    }

    public void setDbName(String dbName) {

        this.dbName = dbName;

    }

    public String getDbTableName() {

        return dbTableName;

    }

    public void setDbTableName(String dbTableName) {

        this.dbTableName = dbTableName;

    }

    public String getDbTableComment() {

        return dbTableComment;

    }

    public void setDbTableComment(String dbTableComment) {

        this.dbTableComment = dbTableComment;

    }

    public String getVoPackage() {

        return voPackage;

    }

    public void setVoPackage(String voPackage) {

        this.voPackage = voPackage;

    }

    public String getVoClassName() {

        return voClassName;

    }

    public void setVoClassName(String voClassName) {

        this.voClassName = voClassName;

    }

    public String getVoFileName() {

        return voFileName;

    }

    public void setVoFileName(String voFileName) {

        this.voFileName = voFileName;

    }

    public String getVoFreemarkPath() {

        return voFreemarkPath;

    }

    public void setVoFreemarkPath(String voFreemarkPath) {

        this.voFreemarkPath = voFreemarkPath;

    }

    public String getVoImports() {

        return voImports;

    }

    public void setVoImports(String voImports) {

        this.voImports = voImports;

    }


    @Override
    public String toString() {
        return "TVoVo{" +
                ", dbName '" + dbName + '\'' +
                ", dbTableName '" + dbTableName + '\'' +
                ", dbTableComment '" + dbTableComment + '\'' +
                ", voPackage '" + voPackage + '\'' +
                ", voClassName '" + voClassName + '\'' +
                ", voFileName '" + voFileName + '\'' +
                ", voFreemarkPath '" + voFreemarkPath + '\'' +
                ", voImports '" + voImports + '\'' +
                '}';
    }

}
