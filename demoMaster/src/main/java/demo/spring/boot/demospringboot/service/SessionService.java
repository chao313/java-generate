package demo.spring.boot.demospringboot.service;

import demo.spring.boot.demospringboot.config.Constant;
import demo.spring.boot.demospringboot.enums.Roles;
import demo.spring.boot.demospringboot.framework.Response;
import demo.spring.boot.demospringboot.framework.UserDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * 辅助登陆的接口
 * content == true 才是登陆成功
 * 否则，提示记录在msg中
 */
@Slf4j
@Service
public class SessionService {

    @Autowired
    private HttpSession session;

    /**
     * Session登陆
     *
     * @param userName
     * @param password
     * @return
     */
    public Response<UserDetail> login(String userName, String password) {
        return Response.OK(new UserDetail() {
            @Override
            public String getPassword() {
                return userName;
            }

            @Override
            public String getUsername() {
                return password;
            }

            @Override
            public String getId() {
                return "1";
            }

            @Override
            public String getRole() {
                return Roles.USER.getValue();
            }
        });
    }

    /**
     * Session登陆
     *
     * @param userName
     * @param password
     * @return
     */
    public Response<UserDetail> adminLogin(String userName, String password) {
        return Response.OK(new UserDetail() {
            @Override
            public String getPassword() {
                return userName;
            }

            @Override
            public String getUsername() {
                return password;
            }

            @Override
            public String getId() {
                return "1";
            }

            @Override
            public String getRole() {
                return Roles.ADMIN.getValue();
            }
        });
    }


    /**
     * 获取当前的UserId
     *
     * @return
     */
    public String getUserId() {
        Object sessionUserId = session.getAttribute(Constant.SESSION_USER_ID);
        if (null == sessionUserId) {
            return null;

        } else {
            return sessionUserId.toString();
        }
    }

    /**
     * 获取当前的UserName
     *
     * @return
     */
    public String getUserName() {
        Object sessionUserName = session.getAttribute(Constant.SESSION_USER_NAME);
        if (null == sessionUserName) {
            return null;

        } else {
            return sessionUserName.toString();
        }
    }
}
