package demo.spring.boot.demospringboot;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.StringHttpMessageConverter;

import java.nio.charset.Charset;

@SpringBootApplication
@ComponentScan(value = {"demo.spring.boot", "demo2"})
@MapperScan(value = {"demo.spring.boot.demospringboot.mybatis.dao", "demo2.dao"})//mybatis
public class Application implements ApplicationRunner {

    private static Logger logger = LoggerFactory.getLogger(Application.class);


    public static void main(String[] args) {
        Application.test();
        SpringApplication.run(Application.class, args);
    }


    /**
     * springboot 默认的就是 utf-8
     * 修改为iso-8859-1就会出现乱码
     *
     * @return
     */
    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter() {
        //StringHttpMessageConverter converter  = mysql StringHttpMessageConverter(Charset.forName("ISO-8859-1"));
        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return converter;
    }

    public static void test() {
//        InetSocketTransportAddress inetSocketTransportAddress = null;
//        try {
//            inetSocketTransportAddress = mysql InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9301);
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }
//        Settings settings = Settings.settingsBuilder().put("cluster.name","elasticsearch").build();
//        TransportClient transportClient = TransportClient.builder().settings(settings).build().addTransportAddress(inetSocketTransportAddress);
//        GetResponse getFields = transportClient.prepareGet("index", "type", "document").get();
//        logger.info("getFields", getFields);
//        transportClient.close();

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        UnzipService.generateUnzipBash("idMapName");
    }

//    //    @Bean
//    public EmbeddedServletContainerFactory servletContainer() {
//        TomcatEmbeddedServletContainerFactory tomcat = mysql TomcatEmbeddedServletContainerFactory() {
//            @Override
//            protected void postProcessContext(Context context) {
//                SecurityConstraint securityConstraint = mysql SecurityConstraint();
//                securityConstraint.setUserConstraint("CONFIDENTIAL");
//                SecurityCollection collection = mysql SecurityCollection();
//                collection.addPattern("/*");
//                securityConstraint.addCollection(collection);
//                context.addConstraint(securityConstraint);
//            }
//        };
//
//        tomcat.addAdditionalTomcatConnectors(initiateHttpConnector());
//        return tomcat;
//    }
//
//    private Connector initiateHttpConnector() {
//        Connector connector = mysql Connector("org.apache.coyote.http11.Http11NioProtocol");
//        connector.setScheme("http");
//        //需要重定向的http端口 -> 自定义端口号,这个会占用一个端口
//        connector.setPort(80);
//        connector.setSecure(false);
//        //设置重定向到https端口 -> 做个就是yml配置的端口号
//        connector.setRedirectPort(443);
//        return connector;
//    }
}
