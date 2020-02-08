package demo.spring.boot.demospringboot.parse.mysql.parse.vo.mysql.ftl.vue;

import demo.spring.boot.demospringboot.parse.mysql.parse.vo.mysql.ftl.FtlVueInterface;
import demo.spring.boot.demospringboot.parse.mysql.parse.vo.mysql.ftl.vue.plugin.KeyToUrlVo;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Data
public class ApiJsFtl implements FtlVueInterface {




    private String dirPath;//file名称
    private String module;//file名称
    private String fileName = "api.js";//file名称
    private String freeMarkFtlPath = "api.js.ftl";//生成FreeMark的ftlPath
    private String freeMarkStr;//生成FreeMark的str
    private String freeMarkDirPathInResources = rootPath + "vue";//freemark所在文件夹的地址

    /**
     * 自定义的属性
     */
    private List<String> apis = new ArrayList<>();
    /**
     * 举例:
     * <deleteByPrimaryKey,<deleteTUserByPrimaryKey,/demomsater/TUserController/deleteByPrimaryKey>>
     */
    private Map<String, KeyToUrlVo> keyToKeyToUrls = new HashMap<>();//key到url的映射，留作使用
    private String BASE_URL = FtlVueInterface.tomcatPath;//后台项目的地址 -> 留作拼接使用
    private String baseName;

}