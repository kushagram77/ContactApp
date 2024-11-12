package com.yash.capp.test;

import com.yash.capp.config.SpringRootConfig;
import com.yash.capp.dao.UserDAO;
import com.yash.capp.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Kushagra Mishra
 */
public class TestUserDAOUpdate {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRootConfig.class);
        UserDAO userDAO=ctx.getBean(UserDAO.class); //Get Bean of DAO class
        //TODO: the user details will be taken from Update User Profile Page
        User u=new User();
        u.setUserId(2);
        u.setName("Amit Sinha");
        u.setPhone("9303580884");
        u.setEmail("amit.sinha@ezeon.net");
        u.setAddress("Mumbai, MS");        
        u.setRole(1);//Admin Role 
        u.setLoginStatus(1); //Active
        
        userDAO.update(u);
        System.out.println("--------Data Updated------");
    }    
}
