package demo.spring.boot.demospringboot.framework;

public interface UserDetail {
    /**
     * Returns the password used to authenticate the user.
     *
     * @return the password
     */
    String getPassword();

    /**
     * Returns the username used to authenticate the user. Cannot return <code>null</code>
     * .
     *
     * @return the username (never <code>null</code>)
     */
    String getUsername();


    /**
     * 获取用户id
     *
     * @return the id (never <code>null</code>)
     */
    String getId();


    /**
     * 获取用户角色
     * @return
     */
    String getRole();


}
