package demo.spring.boot.demospringboot.controller;

import demo.spring.boot.demospringboot.framework.Response;
import demo.spring.boot.demospringboot.service.MailService;
import demo.spring.boot.demospringboot.service.SessionService;
import demo.spring.boot.demospringboot.util.EncryptUtil;
import demomaster.service.TUserService;
import demomaster.vo.TUserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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

    @Autowired
    private SessionService sessionService;

    @Autowired
    private TUserService tUserService;

    /**
     * 发送验证码
     *
     * @param email
     * @param prefix
     * @return
     */
    @RequestMapping("getCheckCode")
    @ResponseBody
    public Response getCheckCode(String email, String prefix) {
        Response response = new Response();
        try {
            String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
            String message = "您的注册验证码为：" + checkCode;
            mailService.sendSimpleMail(prefix, email, "注册验证码", message);
            sessionService.putCheckCode(checkCode);
            response = Response.OK("验证码发送成功");
        } catch (Exception e) {
            log.info("验证码发送异常:{}", e.toString(), e);
            response = Response.Fail(e.getMessage());
        }
        return response;
    }

    /**
     * 验证验证码是否正确
     *
     * @param checkCode
     * @return
     */
    @RequestMapping("validCheckCode")
    @ResponseBody
    public Response validCheckCode(String checkCode) {
        Response response = new Response();
        try {
            String checkCodeInServer = sessionService.getCheckCode();
            if (checkCodeInServer.equalsIgnoreCase(checkCode)) {
                response = Response.OK(true);
            } else {
                response = Response.OK(false);
            }
        } catch (Exception e) {
            log.info("验证码验证异常:{}", e.toString(), e);
            response = Response.Fail(e.getMessage());
        }
        return response;
    }

    /**
     * 验证找回密码
     *
     * @return
     */
    @RequestMapping("forgetPassword")
    @ResponseBody
    public Response forgetPassword(String email, String prefix) {
        Response response = new Response();
        try {
            TUserVo query = new TUserVo();
            query.setEmail(email);
            List<TUserVo> tUserVos = tUserService.queryBase(query);
            if (tUserVos.size() == 0) {
                response = Response.Fail("此邮箱没有用户绑定!");
            } else {
                tUserVos.forEach(tUserVo -> {
                    String message = "您的账号为:" + tUserVo.getName() + ",您的密码为:" + EncryptUtil.getInstance().Base64Decode(tUserVo.getPassword());//取出加密的数据;
                    mailService.sendSimpleMail(prefix, email, "密码找回", message);
                });
                response = Response.OK(true);
            }
        } catch (Exception e) {
            log.info("验证码验证异常:{}", e.toString(), e);
            response = Response.Fail(e.getMessage());
        }
        return response;
    }


}
