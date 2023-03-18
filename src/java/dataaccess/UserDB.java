/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import models.Role;
import models.User;

/**
 *
 * @author nuket
 */
public class UserDB {
    public User getUser(String email) throws Exception{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try{
            User user = em.find(User.class, email);
           return user;
        }finally{
           em.close();
        }
       
        
    }
    public List<User> getAll() throws Exception{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try{
            
           List<User> users = em.createNamedQuery("User.findAll", User.class).getResultList();
           return users;
           }finally{
            em.close();
        }
    }
    
    public void insert(User user) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            
            trans.begin();
            em.persist(user);
            trans.commit();
        } finally{
         em.close();
        }
    }
    
    public void update(User user) throws Exception {
        
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            
            trans.begin();
            em.merge(user);
            trans.commit();
        } finally{
         em.close();
        }
    }
    public void delete(String email) throws Exception {
       EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            
            trans.begin();
            em.remove(user);
            trans.commit();
        } finally{
         em.close();
        }
    }
}
