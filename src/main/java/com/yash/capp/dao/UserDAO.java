package com.yash.capp.dao;

import com.yash.capp.domain.User;

import java.util.List;

/**
 *
 * @author Kushagra Mishra
 */
public interface UserDAO {

    public void save(User u);

    public void update(User u);

    public void delete(User u);

    public void delete(Integer userId);

    public User findById(Integer userId);

    public List<User> findAll();

    public List<User> findByProperty(String propName, Object propValue);
}
