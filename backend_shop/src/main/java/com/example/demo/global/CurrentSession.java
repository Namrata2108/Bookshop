package com.example.demo.global;


import com.example.demo.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CurrentSession {
    public static List<Product> cart;

    static {
        cart= new ArrayList<Product>();
    }
}

