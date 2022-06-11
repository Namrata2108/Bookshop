package com.example.demo.dao;


import com.example.demo.model.Category;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryDAO extends DAO {
    // Query for Saving Category
    public void save(Category category) {
        try {
            begin();//begin the transaction
            getSession().save(category);//update category
            commit();//commit the transaction
        } catch (HibernateException e) {
            rollback();//rollback
        }
    }

    // Query to find category id
    public Category findById(int id) {
        return getSession().get(Category.class, id);
    }

    // Query to findAll category id
    public List<Category> findAll(){
        Query<Category> query = getSession().createQuery("FROM Category");// create query string
        List<Category> list = query.list(); // add categery rows to lsit
        return list;
    }

    // Query to delete category by id
    public void deleteById(int id){
        Transaction txn = getSession().beginTransaction();//begin the transaction
        Query query = getSession().createQuery("delete Category where id = :ID");//Query String
        Query query1 = getSession().createQuery("delete Product where category_id = :ID");//Query String
        query.setParameter("ID", id);//setting the id
        query1.setParameter("ID", id);

        int result = query.executeUpdate();//getting result query
        if (result > 0) {// if result set has more than 1 row
            System.out.println(id + " category was removed");
        }
        //txn.commit();//commiting the transaction
//        Query query = getSession().createQuery("delete Product where id = :ID");//Query String
//        query.setParameter("ID", id);//setting the id

        int result1 = query1.executeUpdate();//getting result query
        if (result1 > 0) {// if result set has more than 1 row
            System.out.println(id + " products were removed");
        }
        txn.commit();//commit transaction
    }

    // Query to update category
    public void update(Category category) {
        try {
            begin();//begin the transaction
            getSession().update(category);//update category
            commit();//commit the transaction
        } catch (HibernateException e) {
            rollback();//rollback
        }
    }
}
