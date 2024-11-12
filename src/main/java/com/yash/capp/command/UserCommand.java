package com.yash.capp.command;

import com.yash.capp.domain.User;

/**
 *
 * @author Kushagra Mishra
 */

//command is used to convert values obtained from jsp form to domain or entity
public class UserCommand {
    User user;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
}
