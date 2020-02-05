package demo.spring.boot.demospringboot.main;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 拦截启动参数... 做一些操作
 */
@Component
public class SpringBootService implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {


    }
}
