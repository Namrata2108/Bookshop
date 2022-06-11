package com.example.demo.dao;


import com.example.demo.model.Product;
import com.example.demo.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class UserDAO extends DAO{
    // Query for Saving User
    public void save(User user) {
        try {
            begin();//begin the transaction
            getSession().save(user);//update category
            commit();//commit the transaction
        } catch (HibernateException e) {
            rollback();//rollback
        }
    }
    //finding user by the email address
    public User findUserByEmail(String email){
        Transaction transaction = getSession().beginTransaction();//begin transaction
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<User> cr = cb.createQuery(User.class);//create query
        Root<User> root = cr.from(User.class);
        cr.select(root).where(cb.equal(root.get("email"), email));
        Query<User> query = getSession().createQuery(cr);
        query.setMaxResults(1);
        List<User> result = query.getResultList();
        transaction.commit();
        return result.get(0);
    }
}

