package demo.spring.boot.demospringboot.demo2.vo;

import java.sql.Timestamp;

/**
 * 表名称      :t_host
 * 表类型      :BASE TABLE
 * 表引擎      :InnoDB
 * 表版本      :10
 * 行格式      :Dynamic
 * 表创建      :2020-2-4
 * 字符集      :utf8_croatian_ci
 * 表注释      :
 */
public class THostVo {

    private Integer id;  // id 
    private String account;  // account 
    private Timestamp createTime; 
    private String hostIp; 
    private String password; 
    private Timestamp updateTime; 
    private String userId; 
    private Integer port; 


    public Integer getId() {

        return id;

    }

    public void setId(Integer id) {

        this.id = id;

    }

    public String getAccount() {

        return account;

    }

    public void setAccount(String account) {

        this.account = account;

    }

    public Timestamp getCreateTime() {

        return createTime;

    }

    public void setCreateTime(Timestamp createTime) {

        this.createTime = createTime;

    }

    public String getHostIp() {

        return hostIp;

    }

    public void setHostIp(String hostIp) {

        this.hostIp = hostIp;

    }

    public String getPassword() {

        return password;

    }

    public void setPassword(String password) {

        this.password = password;

    }

    public Timestamp getUpdateTime() {

        return updateTime;

    }

    public void setUpdateTime(Timestamp updateTime) {

        this.updateTime = updateTime;

    }

    public String getUserId() {

        return userId;

    }

    public void setUserId(String userId) {

        this.userId = userId;

    }

    public Integer getPort() {

        return port;

    }

    public void setPort(Integer port) {

        this.port = port;

    }


    @Override
    public String toString() {
        return "THostVo{" +
                ", id '" + id +
                ", account '" + account + '\'' +
                ", createTime '" + createTime +
                ", hostIp '" + hostIp + '\'' +
                ", password '" + password + '\'' +
                ", updateTime '" + updateTime +
                ", userId '" + userId + '\'' +
                ", port '" + port +
                '}';
    }

}
