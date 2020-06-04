package demomaster.vo;

import demo.spring.boot.demospringboot.framework.MultiTerm;

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
public class TVoMultiTermVo {

    private MultiTerm<String> dbName;  // 库名
    private MultiTerm<String> dbTableName;  // 表名
    private MultiTerm<String> dbTableComment;  // 表注释
    private MultiTerm<String> voPackage;  // 包名
    private MultiTerm<String> voClassName;  // 类名
    private MultiTerm<String> voFileName;  // 文件名
    private MultiTerm<String> voFreemarkPath;  // freemark路径
    private MultiTerm<String> voImports;  // vo 需要注入的类型 ,逗号分隔


    public MultiTerm<String> getDbName() {

        return dbName;

    }

    public void setDbName(MultiTerm<String> dbName) {

        this.dbName = dbName;

    }
    public MultiTerm<String> getDbTableName() {

        return dbTableName;

    }

    public void setDbTableName(MultiTerm<String> dbTableName) {

        this.dbTableName = dbTableName;

    }
    public MultiTerm<String> getDbTableComment() {

        return dbTableComment;

    }

    public void setDbTableComment(MultiTerm<String> dbTableComment) {

        this.dbTableComment = dbTableComment;

    }
    public MultiTerm<String> getVoPackage() {

        return voPackage;

    }

    public void setVoPackage(MultiTerm<String> voPackage) {

        this.voPackage = voPackage;

    }
    public MultiTerm<String> getVoClassName() {

        return voClassName;

    }

    public void setVoClassName(MultiTerm<String> voClassName) {

        this.voClassName = voClassName;

    }
    public MultiTerm<String> getVoFileName() {

        return voFileName;

    }

    public void setVoFileName(MultiTerm<String> voFileName) {

        this.voFileName = voFileName;

    }
    public MultiTerm<String> getVoFreemarkPath() {

        return voFreemarkPath;

    }

    public void setVoFreemarkPath(MultiTerm<String> voFreemarkPath) {

        this.voFreemarkPath = voFreemarkPath;

    }
    public MultiTerm<String> getVoImports() {

        return voImports;

    }

    public void setVoImports(MultiTerm<String> voImports) {

        this.voImports = voImports;

    }

    @Override
    public String toString() {
        return "TVoMultiTermVo{" +
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
