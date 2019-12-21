package demo.spring.boot.demospringboot.out;

import demo.spring.boot.demospringboot.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class OutService implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        List<String> dirClean3List = CommonUtil.getListByClassPath("dirClean3");
        dirClean3List.forEach(line -> {
            //12392&id= 位置：http://down.asp300.net:8008/PHP/21/971t.rar
            String id = CommonUtil.RegexValueListFirst(line, "(.*?)&([\\s\\S]*)/([\\s\\S]*)", 1);
            String jarName = CommonUtil.RegexValueListFirst(line, "(.*?)&([\\s\\S]*)/([\\s\\S]*)", 3);
            log.info("id,jarName:{},{}", id.trim(), jarName.trim());
        });

    }
}
