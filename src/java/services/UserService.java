/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.RoleDB;
import dataaccess.UserDB;
import java.util.List;
import models.Role;
import models.User;

/**
 *
 * @author nuket
 */
public class UserService {
    public User getUser(String email) throws Exception{
        UserDB ub = new UserDB();
        User user = ub.getUser(email);
        return user;
    }
    public List<User> getAll() throws Exception{
        UserDB  ub = new UserDB();
       List<User> users = ub.getAll();
       return users;
    }
    
    public void insert(String email, String firstName, String lastName, String password, int role) throws Exception{
       RoleDB rb = new RoleDB();
       Role temp = rb.get(role);
        User user = new User(email, firstName, lastName, password);
        user.setRole(temp);
        UserDB ub = new UserDB();
        ub.insert(user);
    }
    
    public void update(String email, String firstName, String lastName, String password, Role role) throws Exception{
        User user = new User(email, firstName, lastName, password);
        user.setRole(role);
        UserDB ub = new UserDB();
        ub.update(user);
    }
    
    public void delete(String email) throws Exception{
        
        UserDB ub = new UserDB();
        ub.delete(email);
    }
    
}
