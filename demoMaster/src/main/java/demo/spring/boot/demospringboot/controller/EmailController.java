package demo.spring.boot.demospringboot.controller;

import demo.spring.boot.demospringboot.framework.Response;
import demo.spring.boot.demospringboot.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * 专门处理邮箱的接口
 */
@Slf4j
@RestController
@RequestMapping("/EmailController")
public class EmailController {

    @Autowired
    private MailService mailService;

    @RequestMapping("getCheckCode")
    @ResponseBody
    public Response getCheckCode(String email, String prefix) {
        Response response = new Response();
        try {
            String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
            String message = "您的注册验证码为：" + checkCode;
            mailService.sendSimpleMail(prefix, email, "注册验证码", message);
            response = Response.OK("验证码发送成功");
        } catch (Exception e) {
            log.info("验证码发送异常:{}", e.toString(), e);
            response = Response.Fail(e.getMessage());
        }
        return response;
    }


}
