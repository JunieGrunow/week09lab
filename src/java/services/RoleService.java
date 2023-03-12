/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.RoleDB;
import java.util.List;
import models.Role;

/**
 *
 * @author nuket
 */
public class RoleService {
    public List<Role> getAll() throws Exception{
        RoleDB rb = new RoleDB();
        
        List<Role> roles = rb.getAll();
        return roles;
    }
}
