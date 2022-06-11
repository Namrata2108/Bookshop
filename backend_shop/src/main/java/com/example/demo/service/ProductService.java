package com.example.demo.service;


import com.example.demo.dao.ProductDAO;
import com.example.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.transaction.Transactional;

@Service
public class  ProductService {
    @Autowired
    ProductDAO productDAO;
    public List<Product> getAllProduct(){
        return productDAO.findAll();
    }
    public Product getProductById(long id){
        return  productDAO.findById(id);
    }
    public void update(Product product){
        productDAO.update(product);
    }
    public void addProduct(Product product){
        productDAO.save(product);
    }
    public List<Product> getAllProductsByCategoryId(int id){
        return productDAO.getAllByCategoryId(id);
    }
    public void removeProductById(long id){
        productDAO.deleteById(id);
    }


}

