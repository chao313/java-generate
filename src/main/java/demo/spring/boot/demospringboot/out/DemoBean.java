package demo.spring.boot.demospringboot.out;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
public class DemoBean {

    @Data
    public class BeanVo {
        private String profile;

        public BeanVo(String profile) {
            this.profile = profile;
        }
    }

    @Bean
    @Profile("dev")
    public BeanVo devProfileBean() {
        return new BeanVo("dev");
    }

    @Bean
    @Profile("prod")
    public BeanVo prodProfileBean() {
        return new BeanVo("prod");
    }


}
