/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



/**
 *
 * @author nuket
 */
public class DBUtil {
     private static final EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("UserPU");

    public static EntityManagerFactory getEmFactory() {
        return emf;
    }
}
