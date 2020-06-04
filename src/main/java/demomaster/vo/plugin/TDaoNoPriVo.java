package demomaster.vo.plugin;


/**
 * 这里属性是非主键字段
 *
 * 表名称      :t_dao
 * 表类型      :BASE TABLE
 * 表引擎      :InnoDB
 * 表版本      :10
 * 行格式      :Compact
 * 表创建      :2020-6-5
 * 字符集      :utf8_bin
 * 表注释      :
 */
public class TDaoNoPriVo {

    private String dbTableComment;  // 表注释 
    private String packageName;  // 包名 
    private String className;  // 类名 
    private String fileName;  // 文件名 
    private String freemarkPath;  // freemark路径 
    private String freemarkContent;  // freemark内容 
    private String imports;  // vo 需要注入的类型 ,逗号分隔 


    public String getDbTableComment() {

        return dbTableComment;

    }

    public void setDbTableComment(String dbTableComment) {

        this.dbTableComment = dbTableComment;

    }
    public String getPackageName() {

        return packageName;

    }

    public void setPackageName(String packageName) {

        this.packageName = packageName;

    }
    public String getClassName() {

        return className;

    }

    public void setClassName(String className) {

        this.className = className;

    }
    public String getFileName() {

        return fileName;

    }

    public void setFileName(String fileName) {

        this.fileName = fileName;

    }
    public String getFreemarkPath() {

        return freemarkPath;

    }

    public void setFreemarkPath(String freemarkPath) {

        this.freemarkPath = freemarkPath;

    }
    public String getFreemarkContent() {

        return freemarkContent;

    }

    public void setFreemarkContent(String freemarkContent) {

        this.freemarkContent = freemarkContent;

    }
    public String getImports() {

        return imports;

    }

    public void setImports(String imports) {

        this.imports = imports;

    }

    @Override
    public String toString() {
        return "TDaoNoPriVo{" +
                ", dbTableComment '" + dbTableComment + '\'' +
                ", packageName '" + packageName + '\'' +
                ", className '" + className + '\'' +
                ", fileName '" + fileName + '\'' +
                ", freemarkPath '" + freemarkPath + '\'' +
                ", freemarkContent '" + freemarkContent + '\'' +
                ", imports '" + imports + '\'' +
                '}';
    }

}