/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Role;
import models.User;

/**
 *
 * @author nuket
 */
public class UserDB {
    
    public List<User> getAll() throws Exception{
        List<User> users = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
         String statement = "SELECT * FROM user";
        
        try{
           ps = con.prepareStatement(statement);
           rs = ps.executeQuery();
           while(rs.next()){
               String email = rs.getString(1);
               String firstName = rs.getString(2);
               String lastName = rs.getString(3);
               String password = rs.getString(4);
               int role = rs.getInt(5);
               
               if(role==1){
                   Role temp = new Role(1,"System Admin");
                   
                   User user = new User(email,firstName, lastName, password, temp);
                   users.add(user);
               }
               if(role==2){
                   Role temp = new Role(2,"General User");
                   
                   User user = new User(email,firstName, lastName, password, temp);
                   users.add(user);
               }
               
           }
        }finally{
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(con);
        }
        return users;
    }
    
    public void insert(User user) throws Exception {
         ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String statement = "INSERT INTO user (email, first_name, last_name, password, role values(?,?,?,?)";
        
        try {
            ps = con.prepareStatement(statement);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getPassword());
            if(user.getRole().getID()==1){
                ps.setInt(5,1);
            }
             if(user.getRole().getID()==2){
                ps.setInt(5,2);
            }
            ps.executeUpdate();
        } finally{
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(con);
        }
    }
    
    public void update(User user) throws Exception {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String statement = "UPDATE user SET first_name=?. last_name=?, password=?, role=? WHERE email=?";
        
        try {
            ps = con.prepareStatement(statement);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getPassword());
            if(user.getRole().getID()==1){
                ps.setInt(5,1);
            }
             if(user.getRole().getID()==2){
                ps.setInt(5,2);
            }
            ps.executeUpdate();
        } finally{
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(con);
        }
    }
    public void delete(User user) throws Exception {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String statement = "DELETE FROM user WHERE email=?";
        
        try{
            ps = con.prepareStatement(statement);
            ps.setString(1, user.getEmail());
            ps.executeUpdate();
        }finally{
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(con);
        }
    }
}
