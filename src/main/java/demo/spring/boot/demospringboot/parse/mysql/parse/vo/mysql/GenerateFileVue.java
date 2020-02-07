package demo.spring.boot.demospringboot.parse.mysql.parse.vo.mysql;

import demo.spring.boot.demospringboot.parse.mysql.parse.vo.mysql.ftl.FtlJavaInterface;
import demo.spring.boot.demospringboot.parse.mysql.parse.vo.mysql.ftl.FtlVueInterface;
import demo.spring.boot.demospringboot.parse.mysql.parse.vo.mysql.ftl.java.*;
import demo.spring.boot.demospringboot.parse.mysql.parse.vo.mysql.ftl.vue.*;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j(topic = "生成vue文件")
public class GenerateFileVue {


    /**
     * 根据数据库名称+表名称+包名称 -> 生成vue -> 这里可能会借用Java生成的代码
     *
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    public static AllVueFtl GenerateFile(AllJavaFtl allJavaFtl, List<AllJavaFtl> allJavaFtls) throws SQLException, ClassNotFoundException, IOException, TemplateException {


        AllVueFtl allVueFtl = new AllVueFtl();
        allVueFtl.setAllJavaFtl(allJavaFtl);//设置javaFtl
        allVueFtl.setAllJavaFtls(allJavaFtls);//存放所有的javaFtls

        ApiJsFtl apiJsFtl = GenerateFileVue.getApiJsFtl(allVueFtl);//API的参数
        allVueFtl.setApiJsFtl(apiJsFtl);

        ListVueFtl listVueFtl = GenerateFileVue.getListVueFtl(allVueFtl);//API的参数
        allVueFtl.setListVueFtl(listVueFtl);

        ViewVueFtl viewVueFtl = GenerateFileVue.getViewVueFtl(allVueFtl);//API的参数
        allVueFtl.setViewVueFtl(viewVueFtl);

        return allVueFtl;

    }


    /**
     * 获取ApiJs的操作
     */
    private static ApiJsFtl getApiJsFtl(AllVueFtl allVueFtl) throws IOException, TemplateException {

        ApiJsFtl apiJsFtl = new GenerateVueUtil<ApiJsFtl>() {
            @Override
            public ApiJsFtl getFtlVo() {
                ApiJsFtl vo = new ApiJsFtl();
                List<String> apis = Arrays.asList("insert", "inserts", "queryBase", "updateBase", "updateBaseIncludeNull",
                        "deleteBase", "queryByPrimaryKey", "deleteByPrimaryKey");
                vo.setApis(apis);
                Map<String, String> keyToUrls = new HashMap<>();
                keyToUrls.put("deleteByPrimaryKey", allVueFtl.getAllJavaFtl().getControllerFtl().getClassName() + "/" + FtlVueInterface.deleteByPrimaryKey);
                keyToUrls.put("queryByPrimaryKey", allVueFtl.getAllJavaFtl().getControllerFtl().getClassName() + "/" + FtlVueInterface.queryByPrimaryKey);
                keyToUrls.put("updateBase", allVueFtl.getAllJavaFtl().getControllerFtl().getClassName() + "/" + FtlVueInterface.updateBase);
                keyToUrls.put("queryBase", allVueFtl.getAllJavaFtl().getControllerFtl().getClassName() + "/" + FtlVueInterface.queryBase);
                keyToUrls.put("inserts", allVueFtl.getAllJavaFtl().getControllerFtl().getClassName() + "/" + FtlVueInterface.inserts);
                keyToUrls.put("insert", allVueFtl.getAllJavaFtl().getControllerFtl().getClassName() + "/" + FtlVueInterface.insert);
                vo.setKeyToUrls(keyToUrls);
                return vo;
            }

            @Override
            public AllVueFtl getAllVueFtl() {
                return allVueFtl;
            }
        }.grenerate();

        return apiJsFtl;
    }

    /**
     * 获取ApiJs的操作
     */
    private static ListVueFtl getListVueFtl(AllVueFtl allVueFtl) throws IOException, TemplateException {

        ListVueFtl ftl = new GenerateVueUtil<ListVueFtl>() {
            @Override
            public ListVueFtl getFtlVo() {
                ListVueFtl vo = new ListVueFtl();

                return vo;
            }

            @Override
            public AllVueFtl getAllVueFtl() {
                return allVueFtl;
            }
        }.grenerate();

        return ftl;
    }

    /**
     * 获取ftl的操作
     */
    private static ViewVueFtl getViewVueFtl(AllVueFtl allVueFtl) throws IOException, TemplateException {

        ViewVueFtl ftl = new GenerateVueUtil<ViewVueFtl>() {
            @Override
            public ViewVueFtl getFtlVo() {
                ViewVueFtl vo = new ViewVueFtl();
                String Module = allVueFtl.getAllJavaFtl().getVoFtl().getClassName();
                vo.setDirPath("demoWeb/src/views");
                vo.setFileName("View.Vue");
                vo.setFreeMarkFtlPath("View.vue.ftl");
                vo.setModule(Module);
                vo.setJavaFields(allVueFtl.getAllJavaFtl().getVoFtl().getJavaFields());
                vo.setPrimaryKeyJavaFields(allVueFtl.getAllJavaFtl().getVoFtl().getPrimaryKeyJavaFields());
                vo.setQueryByPrimaryKeyUrl(allVueFtl.getApiJsFtl().getKeyToUrls().get(ViewVueFtl.queryByPrimaryKey));//指定查询的url
                return vo;
            }

            @Override
            public AllVueFtl getAllVueFtl() {
                return allVueFtl;
            }
        }.grenerate();

        return ftl;
    }

    /**
     * 获取ftl的操作
     */
    private static EditVueFtl getEditVueFtl(AllVueFtl allVueFtl) throws IOException, TemplateException {

        EditVueFtl ftl = new GenerateVueUtil<EditVueFtl>() {
            @Override
            public EditVueFtl getFtlVo() {
                EditVueFtl vo = new EditVueFtl();
                String Module = allVueFtl.getAllJavaFtl().getVoFtl().getClassName();
                vo.setDirPath("demoWeb/src/views");
                vo.setFileName("View.Vue");
                vo.setFreeMarkFtlPath("View.vue.ftl");
                vo.setModule(Module);
                vo.setJavaFields(allVueFtl.getAllJavaFtl().getVoFtl().getJavaFields());
                vo.setPrimaryKeyJavaFields(allVueFtl.getAllJavaFtl().getVoFtl().getPrimaryKeyJavaFields());
                vo.setQueryByPrimaryKeyUrl(allVueFtl.getApiJsFtl().getKeyToUrls().get(ViewVueFtl.queryByPrimaryKey));//指定查询的url
                vo.setUpdateBaseUrl(allVueFtl.getApiJsFtl().getKeyToUrls().get(ViewVueFtl.updateBase));//指定查询的url
                return vo;
            }

            @Override
            public AllVueFtl getAllVueFtl() {
                return allVueFtl;
            }
        }.grenerate();

        return ftl;
    }


}
