package com.example.demo.service;


import com.example.demo.dao.CategoryDAO;
import com.example.demo.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {


    @Autowired
    CategoryDAO categoryDAO;

    public List<Category> getAllCategory(){
        return categoryDAO.findAll();
    }

    public void addCategory(Category category){
        categoryDAO.save(category);
    }

    public void updateCategory(Category category){
        categoryDAO.update(category);
    }

    public void removeCategoryById(int id){
        categoryDAO.deleteById(id);
    }

    public Category getCategoryById(int id){
        return categoryDAO.findById(id);
    }

}

