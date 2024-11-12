package com.yash.capp.command;

/**
 *
 * @author Kushagra Mishra
 */

//command is used to convert values obtained from jsp form to domain or entity
public class LoginCommand {
    private String loginName;
    private String password;    

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
