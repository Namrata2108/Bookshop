package com.example.demo.dao;



import com.example.demo.model.Category;
import com.example.demo.model.Product;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDAO extends DAO{

    // Query for Saving Category
    public void save(Product product) {
        try {
            begin();//begin the transaction
            getSession().save(product);//update category
            commit();//commit the transaction
        } catch (HibernateException e) {
            rollback();//rollback
        }
    }

    // Query to find product id
    public Product findById(long id) {
        return getSession().get(Product.class, id);
    }

    // Query to find list of product by id
    public List<Product> findAll(){
        Query<Product> query = getSession().createQuery("FROM Product");// query string
        List<Product> list = query.list();// list of products
        return list;
    }

    // delete product by id
    public void deleteById(long id){
        Transaction txn = getSession().beginTransaction();//begin transaction
        Query query = getSession().createQuery("delete Product where id = :ID");//Query String
        query.setParameter("ID", id);//setting the id

        int result = query.executeUpdate();//getting result query
        if (result > 0) {// if result set has more than 1 row
            System.out.println(id + " products was removed");
        }
        txn.commit();//commit transaction
       
   
    }

    // get all products by category
    public List<Product> getAllByCategoryId(int categoryId){
        Query<Product> query = getSession().createQuery("FROM Product where category_id = :ID");// query for selecting products by all products
        query.setParameter("ID", categoryId);//setting the category id
        return query.list();//executing the query
    }

    // Query to update category
    public void update(Product product) {
        try {
            begin();//begin the transaction
            getSession().update(product);//update category
            commit();//commit the transaction
        } catch (HibernateException e) {
            rollback();//rollback
        }
    }
}

