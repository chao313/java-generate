package demo.spring.boot.demospringboot.parse.mysql.parse.db;

import demo.spring.boot.demospringboot.parse.mysql.parse.db.util.ChangeType;
import demo.spring.boot.demospringboot.parse.mysql.parse.util.CamelUtils;
import demo.spring.boot.demospringboot.parse.mysql.parse.util.FreemarkUtil;
import demo.spring.boot.demospringboot.parse.mysql.parse.vo.*;
import demo.spring.boot.demospringboot.parse.mysql.parse.vo.mysql.GenerateUtil;
import demo.spring.boot.demospringboot.parse.mysql.parse.vo.mysql.Table;
import demo.spring.boot.demospringboot.parse.mysql.parse.vo.mysql.ftl.DAOFtl;
import demo.spring.boot.demospringboot.parse.mysql.parse.vo.mysql.ftl.VoFtl;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j(topic = "生成文件")
public class GenerateFile {


    /**
     * 根据数据库名称+表名称+包名称 -> 生成code
     * -> 这里是静态方法，目前的链接是硬编码，需要升级
     * 1.获取指定表的所有信息
     * 2.获取指定表的所有字段信息
     * 3.提取出主键字段
     * 4.补全vo类包名,类名，文件名
     * 5.补全dao类包名,类名，文件名
     * 6.补全service类包名,类名，文件名
     * 7.补全serviceImpl类包名,类名，文件名
     * 8.补全Controller类包名,类名，文件名
     * 9.补全Mapper类包名,类名，文件名
     *
     * @param dataBase
     * @param ptableName
     * @param basePackage
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    public static JavaTable GenerateFileV2(String dataBase, String ptableName, String basePackage) throws SQLException, ClassNotFoundException, IOException, TemplateException {
        DBInfo dbInfo = new DBInfo();
        /**
         * 返回这个表的所有相关信息:表名称,表类型,引擎,表的字符集,表的注释<br>
         */
        MysqlTable mysqlTable = dbInfo.obtainTableInfo(dataBase, ptableName);
        /**
         * 获取字段的信息：字段名称,字段类型,字段注释,字段是否允许为null,字段是否为主键
         */
        List<MysqlField> fields = dbInfo.obtainFieldsInfo(dataBase, ptableName);
        mysqlTable.setMysqlFields(fields);

        JavaTable javaTable = GenerateFile.transByMysqlTable(mysqlTable, basePackage);

        /**
         * 把主键字段中从全部字段中筛选出来
         */
        List<MysqlField> priFromFields = MysqlTable.getPRIFromFields(mysqlTable.getMysqlFields());
        mysqlTable.setPrimaryKeys(priFromFields);
        log.info("[mysql解析]获取主键:{}", javaTable);


        Table table = new Table();
        BeanUtils.copyProperties(mysqlTable, table);

        /**
         * className的前缀(驼峰命名)
         */
        String classNamePrefix = CamelUtils.toUpperCaseFirstOne(CamelUtils.underline2Camel(javaTable.getTableName()));

        VoFtl voFtl = new GenerateUtil<VoFtl>() {
            @Override
            public VoFtl getFtlVo() {
                VoFtl vo = new VoFtl();
                vo.setTable(table);
                vo.setPackageName(basePackage.toLowerCase() + "." + vo.getDir());
                vo.setClassName(classNamePrefix + "Vo");
                vo.setFields(fields);
                vo.setPrimaryKeyFields(priFromFields);
                //使用之前提取的className直接加上.java
                vo.setFileName(vo.getClassName() + ".java");
                return vo;
            }
        }.grenerate();

        return javaTable;

    }


    /**
     * 根据数据库名称+表名称+包名称 -> 生成code
     * -> 这里是静态方法，目前的链接是硬编码，需要升级
     * 1.获取指定表的所有信息
     * 2.获取指定表的所有字段信息
     * 3.提取出主键字段
     * 4.补全vo类包名,类名，文件名
     * 5.补全dao类包名,类名，文件名
     * 6.补全service类包名,类名，文件名
     * 7.补全serviceImpl类包名,类名，文件名
     * 8.补全Controller类包名,类名，文件名
     * 9.补全Mapper类包名,类名，文件名
     *
     * @param dataBase
     * @param ptableName
     * @param basePackage
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IOException
     * @throws TemplateException
     */
    public static JavaTable GenerateFile(String dataBase, String ptableName, String basePackage)
            throws SQLException, ClassNotFoundException, IOException, TemplateException {
        DBInfo dbInfo = new DBInfo();
        /**
         * 返回这个表的所有相关信息:表名称,表类型,引擎,表的字符集,表的注释<br>
         */
        MysqlTable mysqlTable = dbInfo.obtainTableInfo(dataBase, ptableName);
        /**
         * 获取字段的信息：字段名称,字段类型,字段注释,字段是否允许为null,字段是否为主键
         */
        List<MysqlField> fields = dbInfo.obtainFieldsInfo(dataBase, ptableName);
        mysqlTable.setMysqlFields(fields);

        JavaTable javaTable = GenerateFile.transByMysqlTable(mysqlTable, basePackage);

        /**
         * 把主键字段中从全部字段中筛选出来
         */
        List<MysqlField> priFromFields = MysqlTable.getPRIFromFields(mysqlTable.getMysqlFields());
        mysqlTable.setPrimaryKeys(priFromFields);
        log.info("[mysql解析]获取主键:{}", javaTable);

        //转入freemark
        Map<String, Object> map = new HashMap<>();
        map.put("mysqlTable", mysqlTable);
        map.put("javaTable", javaTable);
        File templateDirFile = ResourceUtils.getFile("classpath:freemark");
        StringBuffer voStr = FreemarkUtil.generateXmlByTemplate(map, templateDirFile, "Vo.ftl");
        javaTable.setClassVoStr(voStr.toString());
        log.info("[mysql解析]Vo成功:{}", voStr.toString());

        StringBuffer daoStr = FreemarkUtil.generateXmlByTemplate(map, templateDirFile, "DAO.ftl");
        javaTable.setClassDaoStr(daoStr.toString());
        log.info("[mysql解析]DAO成功{}", daoStr.toString());

        StringBuffer serviceStr = FreemarkUtil.generateXmlByTemplate(map, templateDirFile, "Service.ftl");
        javaTable.setClassServiceStr(serviceStr.toString());
        log.info("[mysql解析]Service成功{}", serviceStr.toString());

        StringBuffer serviceImplStr = FreemarkUtil.generateXmlByTemplate(map, templateDirFile, "ServiceImpl.ftl");
        javaTable.setClassServiceImplStr(serviceImplStr.toString());
        log.info("[mysql解析]ServiceImpl成功{}}", serviceImplStr.toString());

        List<MysqlAndJavaField> mysqlAndJavaFields = new ArrayList<>();
        for (int i = 0; i < mysqlTable.getMysqlFields().size(); i++) {
            MysqlAndJavaField mysqlAndJavaField = new MysqlAndJavaField();
            mysqlAndJavaField.setJavaField(javaTable.getJavaFields().get(i));
            mysqlAndJavaField.setMysqlField(mysqlTable.getMysqlFields().get(i));
            mysqlAndJavaFields.add(mysqlAndJavaField);
        }
        //
        List<MysqlAndJavaField> mysqlAndJavaKeys = new ArrayList<>();
        for (int i = 0; i < mysqlTable.getPrimaryKeys().size(); i++) {
            MysqlAndJavaField mysqlAndJavaField = new MysqlAndJavaField();
            mysqlAndJavaField.setJavaField(javaTable.getPrimaryKeys().get(i));
            mysqlAndJavaField.setMysqlField(mysqlTable.getPrimaryKeys().get(i));
            mysqlAndJavaKeys.add(mysqlAndJavaField);
        }
        map.put("mysqlAndJavaFields", mysqlAndJavaFields);
        map.put("mysqlAndJavaKeys", mysqlAndJavaKeys);
        StringBuffer mapperStr = FreemarkUtil.generateXmlByTemplate(map, templateDirFile, "Mapper.ftl");
        javaTable.setMapperStr(mapperStr.toString());
        log.info("[mysql解析]Mapper成功{}", mapperStr.toString());
        return javaTable;

    }


    /**
     * @param mysqlTable
     * @param basePackage com.demo
     * @return
     */
    public static JavaTable transByMysqlTable(MysqlTable mysqlTable, String basePackage) {
        assert (null != mysqlTable);
        assert (null != mysqlTable.getMysqlFields());
        JavaTable javaTable = new JavaTable();
        javaTable.setBasePackage(basePackage);
        javaTable.setMysqlTable(mysqlTable);
        BeanUtils.copyProperties(mysqlTable, javaTable);//复制到javatable里面
        javaTable.setTableName(CamelUtils.toUpperCaseFirstOne(CamelUtils.underline2Camel(javaTable.getTableName())));
        List<JavaField> javaFields = new ArrayList<>();
        mysqlTable.getMysqlFields().forEach(mysqlField -> {
            JavaField javaField = new JavaField();
            javaField.setComment(mysqlField.getComment());
            javaField.setType(ChangeType.getTypeMap().get(mysqlField.getType()).getSimpleName());
            javaField.setIsNotNull(mysqlField.getIsNotNull());
            javaField.setIsPRI(mysqlField.getIsPRI());
            javaField.setName(CamelUtils.underline2Camel(mysqlField.getName()));
            javaFields.add(javaField);
        });
        List<JavaField> primaryKeys = new ArrayList<>();
        javaFields.forEach(javaField -> {
            if (javaField.getIsPRI()) {
                //如果是主键 -> 添加
                primaryKeys.add(javaField);
            }
        });
        javaTable.setJavaFields(javaFields);
        javaTable.setPrimaryKeys(primaryKeys);
        //处理类的名字
        String baseName = javaTable.getTableName();
        javaTable.setClassDAOName(baseName + "Dao");
        javaTable.setClassVoName(baseName + "Vo");
        javaTable.setClassServiceName(baseName + "Service");
        javaTable.setClassServiceImplName(baseName + "ServiceImpl");
        javaTable.setMapperName(baseName + "Mapper");

        //处理类的package路径
        javaTable.setClassDAOPackage(javaTable.getBasePackage() + "." + javaTable.getClassDAOName());
        javaTable.setClassVoPackage(javaTable.getBasePackage() + "." + javaTable.getClassVoName());
        javaTable.setClassServicePackage(javaTable.getBasePackage() + "." + javaTable.getClassServiceName());
        javaTable.setClassServiceImplPackage(javaTable.getBasePackage() + "." + javaTable.getClassServiceImplName());

        //处理文件的path
        javaTable.setClassDaoPath(javaTable.getClassDAOPackage().replace(".", "/") + ".java");
        javaTable.setClassVoPath(javaTable.getClassVoPackage().replace(".", "/") + ".java");
        javaTable.setClassServicePath(javaTable.getClassServicePackage().replace(".", "/") + ".java");
        javaTable.setClassServiceImplPath(javaTable.getClassServiceImplPackage().replace(".", "/") + ".java");
        javaTable.setMapperPath(javaTable.getBasePackage().replace(".", "/") + "/" + javaTable.getMapperName() + ".xml");
        javaTable.setBasePackagePath(javaTable.getBasePackage().replace(".", "/"));

        //处理 unique
        List<String> javaFieldTypeNameUnique = new ArrayList<>();
        javaTable.getJavaFields().forEach(javaField -> {
            if (!javaFieldTypeNameUnique.contains(javaField.getType())) {
                javaFieldTypeNameUnique.add(javaField.getType());
            }
        });
        javaTable.setJavaFieldTypeNameUnique(javaFieldTypeNameUnique);

        List<String> primaryKeyTypeNameUnique = new ArrayList<>();
        javaTable.getPrimaryKeys().forEach(javaField -> {
            if (!primaryKeyTypeNameUnique.contains(javaField.getType())) {
                primaryKeyTypeNameUnique.add(javaField.getType());
            }
        });
        javaTable.setPrimaryKeyTypeNameUnique(primaryKeyTypeNameUnique);

//        //处理mysqlAndJavaFields和primaryKeys
//        List<MysqlAndJavaField> mysqlAndJavaFields = mysql ArrayList<>();
//        for (int i = 0; i < mysqlTable.getMysqlFields().size(); i++) {
//            MysqlAndJavaField mysqlAndJavaField = mysql MysqlAndJavaField();
//            mysqlAndJavaField.setJavaField(javaTable.getJavaFields().get(i));
//            mysqlAndJavaField.setMysqlField(mysqlTable.getMysqlFields().get(i));
//            mysqlAndJavaFields.add(mysqlAndJavaField);
//        }
//        List<MysqlAndJavaField> mysqlAndJavaKeys = mysql ArrayList<>();
//        for (int i = 0; i < mysqlTable.getPrimaryKeys().size(); i++) {
//            MysqlAndJavaField mysqlAndJavaField = mysql MysqlAndJavaField();
//            mysqlAndJavaField.setJavaField(javaTable.getPrimaryKeys().get(i));
//            mysqlAndJavaField.setMysqlField(mysqlTable.getPrimaryKeys().get(i));
//            mysqlAndJavaKeys.add(mysqlAndJavaField);
//        }
//        javaTable.setMysqlAndJavaFields(mysqlAndJavaFields);
//        javaTable.setMysqlAndJavaKeys(mysqlAndJavaKeys);

        return javaTable;
    }

    /**
     * 生成具有关系(这里的暂时不做，因为用起来费劲)
     *
     * @param dataBase
     * @param tableBase
     * @param basePackage
     * @param associations
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IOException
     * @throws TemplateException
     */
    @Deprecated
    public static List<AssociationJavaTable> GenerateFileAssociation(String dataBase,
                                                                     String tableBase,
                                                                     String basePackage,
                                                                     List<AssociationTable> associations)
            throws SQLException, ClassNotFoundException, IOException, TemplateException {

        //第一步 生成所有关联的table的vo对象 - 过滤出所有的表
        List<String> tables = new ArrayList<>();
        associations.forEach(associationRequest -> {
            if (!tables.contains(associationRequest.getLeftTable())) {
                tables.add(associationRequest.getLeftTable());
            }
            if (!tables.contains(associationRequest.getRightTable())) {
                tables.add(associationRequest.getRightTable());
            }
        });

        List<String> leftTables = new ArrayList<>();
        associations.forEach(associationRequest -> {
            if (!leftTables.contains(associationRequest.getLeftTable())) {
                leftTables.add(associationRequest.getLeftTable());
            }
        });


        DBInfo dbInfo = new DBInfo();
        List<AssociationJavaTable> associationJavaTables = new ArrayList<>();
        for (String table : tables) {
            //处理所有关联的数据表 -> 生成AssociationJavaTable
            MysqlTable tableBaseMysql = dbInfo.obtainTableInfo(dataBase, table);//获取表的信息
            List<MysqlField> fields = dbInfo.obtainFieldsInfo(dataBase, table);//获取字段信息
            tableBaseMysql.setMysqlFields(fields);//把字段注入表中
            tableBaseMysql = MysqlTable.transByMysqlTable(tableBaseMysql);//自己转换自己，处理一些数据
            JavaTable javaTable =
                    AssociationJavaTable.transByMysqlTable(tableBaseMysql, basePackage);//把表信息(包括字段转换为java类型)
            AssociationJavaTable associationJavaTable = new AssociationJavaTable();
            BeanUtils.copyProperties(javaTable, associationJavaTable);
            associationJavaTable = AssociationJavaTable.parse(associationJavaTable, leftTables);//数据转换
            log.info("[mysql解析]解析的表信息(包括字段):tableBaseJava:{}", associationJavaTable);
            associationJavaTables.add(associationJavaTable);
            associationJavaTable.setAssociations(associations);
        }

        //把关联关系写入表中
        for (AssociationJavaTable associationJavaTable : associationJavaTables) {
            String tableName = associationJavaTable.getMysqlTable().getTableName();
            for (AssociationTable association : associations) {
                if (association.getLeftTable().equals(tableName)) {
                    //表明这个表中有关联字段
                    for (AssociationJavaTable javaTable : associationJavaTables) {
                        if (javaTable.getMysqlTable().getTableName().equals(association.getRightTable())) {
                            //匹配到了对应的table
                            AssociationJavaTable javaTableTmp = new AssociationJavaTable();
                            BeanUtils.copyProperties(javaTable, javaTableTmp);
                            javaTableTmp.setAssociationHashMap(null);
                            associationJavaTable.getAssociationHashMap().put(association.getRightField(), javaTableTmp);
                        }
                    }
                }
            }
        }
        File templateDirFile = ResourceUtils.getFile("classpath:freemarkAssociation");
        //处理Vo VoAssociation
        for (AssociationJavaTable associationJavaTable : associationJavaTables) {
            Map<String, Object> map = new HashMap<>();
            map.put("javaTable", associationJavaTable);
            map.put("associationJavaTables", associationJavaTables);//用于处理所有的table
            StringBuffer voStr = FreemarkUtil.generateXmlByTemplate(map, templateDirFile, "Vo.ftl");
            associationJavaTable.setClassVoStr(voStr.toString());
            log.info("[mysql解析]Vo成功:{}", voStr.toString());
            if (associationJavaTable.getAssociationHashMap().size() > 0) {
                StringBuffer associationVoStr = FreemarkUtil.generateXmlByTemplate(map, templateDirFile, "VoAssociation.ftl");
                associationJavaTable.setClassAssociationVoStr(associationVoStr.toString());
                log.info("[mysql解析]associationVoStr成功:{}", associationVoStr.toString());

            }
        }

        Map<String, Object> map = new HashMap<>();
        map.put("javaTables", associationJavaTables);
        map.put("javaTable", associationJavaTables.get(0));
        map.put("associationJavaTables", associationJavaTables);//用于处理所有的table

        StringBuffer associationDAOStr = FreemarkUtil.generateXmlByTemplate(map, templateDirFile, "DAOAssociation.ftl");
        associationJavaTables.get(0).setClassAssociationDAOStr(associationDAOStr.toString());
        log.info("[mysql解析]associationDAOStr成功:{}", associationDAOStr.toString());

        StringBuffer associationServiceStr = FreemarkUtil.generateXmlByTemplate(map, templateDirFile, "ServiceAssociation.ftl");
        associationJavaTables.get(0).setClassAssociationServiceStr(associationServiceStr.toString());
        log.info("[mysql解析]associationServiceStr成功:{}", associationServiceStr.toString());

        StringBuffer associationServiceImplStr = FreemarkUtil.generateXmlByTemplate(map, templateDirFile, "ServiceImplAssociation.ftl");
        associationJavaTables.get(0).setClassAssociationServiceImplStr(associationServiceImplStr.toString());
        log.info("[mysql解析]associationServiceImplStr成功:{}", associationServiceImplStr.toString());


        StringBuffer associationMapperStr = FreemarkUtil.generateXmlByTemplate(map, templateDirFile, "MapperAssociation.ftl");
        associationJavaTables.get(0).setMapperStr(associationMapperStr.toString());
        log.info("[mysql解析]associationServiceImplStr成功:{}", associationMapperStr.toString());

        return associationJavaTables;

    }

}
