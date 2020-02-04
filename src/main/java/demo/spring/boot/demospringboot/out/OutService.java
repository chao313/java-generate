package demo.spring.boot.demospringboot.out;

import demo.spring.boot.demospringboot.util.CommonUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class OutService implements InitializingBean {


    @PostConstruct
    private void init() {
        System.out.println("init");
    }

    @PreDestroy
    private void destroy() {
        System.out.println("destroy");
    }


    @Override
    public void afterPropertiesSet() throws Exception {


    }
}
