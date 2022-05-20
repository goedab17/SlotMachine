package com.example.slotmachinestart.bl;

import com.example.slotmachinestart.pojo.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Database {
    private static Database theInstance;
    private EntityManagerFactory emf;
    private EntityManager em;
    public static Database getTheInstance(){
        if(theInstance==null){
            theInstance= new Database();
        }
        return theInstance;
    }
    private Database() {
        emf= Persistence.createEntityManagerFactory("PU_Slotmachine");
        em = emf.createEntityManager();
    }
    public User getUserByName(String name){
        TypedQuery<User> query = em.createNamedQuery("User.getbyName",User.class);
        query.setParameter("name",name);
        return query.getSingleResult();
    }
    public void createUsers(String name, String pwd){
        User user = new User(name,pwd);
        em.persist(user);
        em.getTransaction().begin();
        em.getTransaction().commit();
    }
}
