package demo.spring.boot.demospringboot.controller;

import demo.spring.boot.demospringboot.config.Constant;
import demo.spring.boot.demospringboot.framework.Code;
import demo.spring.boot.demospringboot.framework.Response;
import demo.spring.boot.demospringboot.framework.UserDetail;
import demo.spring.boot.demospringboot.service.SessionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


@Slf4j
@RestController
@RequestMapping("/SessionController")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private HttpSession session;

    /**
     * 登陆
     *
     * @param userName
     * @param password
     * @return
     */
    @GetMapping("/login")
    public Response<Boolean> login(@RequestParam(value = "userName") String userName,
                                   @RequestParam(value = "password") String password) {
        Response<Boolean> response = new Response();
        try {
            Response<UserDetail> userDetailResponse = sessionService.login(userName, password);
            if (userDetailResponse.getContent() != null) {
                session.setAttribute(Constant.SESSION_USER, userDetailResponse.getContent());
                session.setAttribute(Constant.SESSION_USER_ID, userDetailResponse.getContent().getId());
                session.setAttribute(Constant.SESSION_USER_ROLE, userDetailResponse.getContent().getRole());
                session.setAttribute(Constant.SESSION_USER_NAME, userDetailResponse.getContent().getUsername());
                response.setCode(Code.System.OK);
                response.setContent(true);
            } else {
                response.setCode(Code.System.FAIL);
                response.setContent(false);
                response.setMsg(userDetailResponse.getMsg());
            }
            log.info("success result -> {} ", response);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(e.getMessage());
            response.addException(e);
            log.error("异常 -> {} ", e.getMessage(), e);
        }
        return response;
    }

    /**
     * 登出
     *
     * @return
     */
    @GetMapping("/loginOut")
    public Response<Boolean> loginOut() {
        Response<Boolean> response = new Response();
        try {
            session.removeAttribute(Constant.SESSION_USER);
            session.removeAttribute(Constant.SESSION_USER_ID);
            session.removeAttribute(Constant.SESSION_USER_ROLE);
            response.setCode(Code.System.OK);
            response.setContent(true);
            log.info("success result -> {} ", response);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(e.getMessage());
            response.addException(e);
            log.error("异常 -> {} ", e.getMessage(), e);
        }
        return response;
    }

    /**
     * 获取用户的id
     *
     * @return
     */
    @GetMapping("/getUserId")
    public Response<String> getUserId() {
        Response<String> response = new Response();
        try {
            String userId = sessionService.getUserId();
            if (StringUtils.isNotBlank(userId)) {
                response.setCode(Code.System.OK);
                response.setContent(userId);
            } else {
                response.setCode(Code.System.FAIL);
                response.setMsg("当前用户未登陆");
            }
            log.info("success result -> {} ", response);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(e.getMessage());
            response.addException(e);
            log.error("异常 -> {} ", e.getMessage(), e);
        }
        return response;
    }

    /**
     * 获取用户的UserName
     *
     * @return
     */
    @GetMapping("/getUserName")
    public Response<String> getUserName() {
        Response<String> response = new Response();
        try {
            String userName = sessionService.getUserName();
            if (StringUtils.isNotBlank(userName)) {
                response.setCode(Code.System.OK);
                response.setContent(userName);
            } else {
                response.setCode(Code.System.FAIL);
                response.setMsg("当前用户未登陆");
            }
            log.info("success result -> {} ", response);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(e.getMessage());
            response.addException(e);
            log.error("异常 -> {} ", e.getMessage(), e);
        }
        return response;
    }


    /**
     * 登陆(管理员登陆)
     *
     * @param userName
     * @param password
     * @return
     */
    @GetMapping("/admin/login")
    public Response<Boolean> adminLogin(@RequestParam(value = "userName") String userName,
                                        @RequestParam(value = "password") String password) {
        Response<Boolean> response = new Response();
        try {
            Response<UserDetail> userDetailResponse = sessionService.login(userName, password);
            if (userDetailResponse.getContent() != null) {
                session.setAttribute(Constant.SESSION_USER, userDetailResponse.getContent());
                session.setAttribute(Constant.SESSION_USER_ID, userDetailResponse.getContent().getId());
                session.setAttribute(Constant.SESSION_USER_ROLE, userDetailResponse.getContent().getRole());
                session.setAttribute(Constant.SESSION_USER_NAME, userDetailResponse.getContent().getUsername());
                response.setCode(Code.System.OK);
                response.setContent(true);
            } else {
                response.setCode(Code.System.FAIL);
                response.setContent(false);
                response.setMsg(userDetailResponse.getMsg());
            }
            log.info("success result -> {} ", response);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(e.getMessage());
            response.addException(e);
            log.error("异常 -> {} ", e.getMessage(), e);
        }
        return response;
    }

}
