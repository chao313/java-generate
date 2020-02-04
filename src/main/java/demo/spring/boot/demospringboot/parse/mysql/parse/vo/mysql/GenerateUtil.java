package demo.spring.boot.demospringboot.parse.mysql.parse.vo.mysql;

import demo.spring.boot.demospringboot.parse.mysql.parse.util.FreemarkUtil;
import demo.spring.boot.demospringboot.parse.mysql.parse.vo.mysql.ftl.FtlInterface;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


interface CallBack<T> { //用于回调的接口
    T deal(InputStream in) throws IOException;
}


/**
 * 模板方法 -> 定义流程，继承者只需要实现接口
 * 限定了接口 FtlInterface
 */
@Slf4j
public abstract class GenerateUtil<FtlVo extends FtlInterface> {


    /**
     * 实现着需要从这个接口去获取 FtlVo
     *
     * @return
     */
    public abstract FtlVo getFtlVo();

    /**
     * 实现着需要从这个接口去获取 FtlVo
     *
     * @return
     */
//    abstract FtlVo getCommon();

    /**
     * 1.注入需要的Vo
     * 2.根据freeMark生成ftl
     * 3.返回的就生成好的freeMark的ftl
     *
     * @return
     */
    public FtlVo grenerate() throws IOException, TemplateException {
        FtlVo ftlVo = this.getFtlVo();//获取数据vo
        Map<String, Object> map = new HashMap<>();
        map.put("ftlVo", ftlVo);
        File templateDirFile = ResourceUtils.getFile("classpath:freemark");
        StringBuffer freeMarkStr = FreemarkUtil.generateXmlByTemplate(map, templateDirFile, ftlVo.getFreeMarkFtlPath());
        ftlVo.setFreeMarkStr(freeMarkStr.toString());
        return ftlVo;
    }

}









