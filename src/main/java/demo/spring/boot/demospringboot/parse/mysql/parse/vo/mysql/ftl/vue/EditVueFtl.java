package demo.spring.boot.demospringboot.parse.mysql.parse.vo.mysql.ftl.vue;

import demo.spring.boot.demospringboot.parse.mysql.parse.vo.mysql.JavaField;
import demo.spring.boot.demospringboot.parse.mysql.parse.vo.mysql.ftl.FtlVueInterface;
import lombok.Data;

import java.util.List;

@Data
public class EditVueFtl implements FtlVueInterface {
    private String dirPath;//file名称
    private String module;//file名称
    private String fileName;//file名称
    private String freeMarkFtlPath = "View.vue.ftl";//生成FreeMark的ftlPath
    private String freeMarkStr;//生成FreeMark的str
    private String freeMarkDirPathInResources = rootPath + "vue";//freemark所在文件夹的地址

    /**
     * 自定义的属性
     * View Vue
     * 1.需要的是queryById的接口
     * 2.需要的voFtl的所有的java字段
     * -> 这些都由allVueFtl提供
     * -> 经过考虑，还是决定放在这里
     */
    private String queryByPrimaryKeyUrl;//根据主键查询的接口
    private String updateBaseUrl;//多条件更新的接口

    private List<JavaField> javaFields;//所有的字段
    private List<JavaField> primaryKeyJavaFields;//所有主键的字段


}