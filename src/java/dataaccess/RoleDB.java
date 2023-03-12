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


/**
 *
 * @author nuket
 */
public class RoleDB {
    public List<Role> getAll() throws Exception{
        List<Role> roles = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String statement = "SELECT * FROM role";
        
        try{
            ps = con.prepareStatement(statement);
            rs = ps.executeQuery();
            while(rs.next()){
                int rowNumber = rs.getInt(1);
                int roleID = rs.getInt(2);
                String roleDesc = rs.getString(3);
                Role role = new Role(roleID, roleDesc);
                roles.add(role);
            }
        }finally{
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(con);
        }
        return roles;
    }
    
    public void insert(Role role) throws Exception {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String statement= "INSERT INTO role (role_id, role_name) values (?,?,)";
        
        try{
            ps = con.prepareStatement(statement);

        }
    
    
}
