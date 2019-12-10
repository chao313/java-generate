package demo.spring.boot.demospringboot;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

@SpringBootApplication
@MapperScan(value = "demo.spring.boot.demospringboot.mybatis.dao")//mybatis
public class Application {

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
        //StringHttpMessageConverter converter  = new StringHttpMessageConverter(Charset.forName("ISO-8859-1"));
        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return converter;
    }

    public static void test() {
//        InetSocketTransportAddress inetSocketTransportAddress = null;
//        try {
//            inetSocketTransportAddress = new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9301);
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }
//        Settings settings = Settings.settingsBuilder().put("cluster.name","elasticsearch").build();
//        TransportClient transportClient = TransportClient.builder().settings(settings).build().addTransportAddress(inetSocketTransportAddress);
//        GetResponse getFields = transportClient.prepareGet("index", "type", "document").get();
//        logger.info("getFields", getFields);
//        transportClient.close();


    }
}
