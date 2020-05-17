package demo.spring.boot.demospringboot.service;

import demo.spring.boot.demospringboot.config.Constant;
import demo.spring.boot.demospringboot.enums.ApproveStatus;
import demo.spring.boot.demospringboot.enums.BlackStatus;
import demo.spring.boot.demospringboot.enums.Roles;
import demo.spring.boot.demospringboot.framework.Response;
import demo.spring.boot.demospringboot.framework.UserDetail;
import demo.spring.boot.demospringboot.util.EncryptUtil;
import demomaster.service.TUserService;
import demomaster.vo.TUserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.provider.MD5;

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

    @Autowired
    private TUserService tUserService;

    /**
     * Session登陆
     *
     * @param userName
     * @param password
     * @return
     */
    public Response<UserDetail> login(String userName, String password) {
        TUserVo tUserVo = tUserService.queryByPrimaryKey(userName);
        if (BlackStatus.IN_BLACK.getValue() == tUserVo.getBlackListUser()) {
            return Response.Fail("账号已经被拉入黑名单,请联系管理员!!!");
        }
        if (ApproveStatus.WATTING.getValue() == tUserVo.getApproveStatus()) {
            return Response.Fail("账号在审核中,请联系管理员!!!");
        }
        if (ApproveStatus.NOTPASSED.getValue() == tUserVo.getApproveStatus()) {
            return Response.Fail("账号审核不通过,请联系管理员!!!");
        }
        String passwd = EncryptUtil.getInstance().Base64Encode(password);//取出加密的数据
        if (null != tUserVo && tUserVo.getPassword().equalsIgnoreCase(passwd)) {
            /**代表登录成功*/
            return Response.OK(new UserDetail() {
                @Override
                public String getPassword() {
                    return tUserVo.getPassword();
                }

                @Override
                public String getUsername() {
                    return tUserVo.getName();
                }

                @Override
                public String getId() {
                    return tUserVo.getId();
                }

                @Override
                public String getRole() {
                    return Roles.USER.getValue();
                }
            });
        } else {
            return Response.Fail("账号或者密码错误");
        }

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

    /**
     * 获取存放当前的注册验证码
     *
     * @return
     */
    public void putCheckCode(String checkCode) {
        session.setAttribute(Constant.EMAIL_REGISTER_CODE, checkCode);
    }

    /**
     * 获取当前的验证码
     *
     * @return
     */
    public String getCheckCode() {
        Object sessionCheckCode = session.getAttribute(Constant.EMAIL_REGISTER_CODE);
        if (null == sessionCheckCode) {
            return null;

        } else {
            return sessionCheckCode.toString();
        }
    }
}
