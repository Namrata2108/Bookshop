package com.example.demo.controller;


import com.example.demo.dao.RoleDAO;
import com.example.demo.dao.UserDAO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.global.CurrentSession;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class ApplicationController {
    public static String directory = System.getProperty("user.dir") + "/src/main/resources/static/productImages";

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserDAO userDAO;

    @Autowired
    RoleDAO roleDAO;

    //Admin Dashboard
    @GetMapping("/admin")
    public String adminDashboard(){
        return "adminHome";
    }

    //View Categories for Admin
    @GetMapping("/admin/categories")
    public String getCategory(Model model){
        model.addAttribute("categories", categoryService.getAllCategory());
        return "categories";
    }

    //Add Categories in Admin Dashboard
    @GetMapping("/admin/categories/add")
    public String getCategoryAdd(Model model){
        model.addAttribute("category", new Category());
        return "categoriesAdd";
    }

    //Add Categories in Admin Dashboard
    @PostMapping("/admin/categories/add")
    public String postCategoryAdd(@ModelAttribute("category") Category category){
        if (category.getId()==0) categoryService.addCategory(category);
        else {
        	System.out.println(category.getId()+" "+ category.getName());
        	categoryService.updateCategory(category);
        }
        return "redirect:/admin/categories";
    }

    //Update Categories in Admin Dashboard
    @GetMapping("/admin/categories/update/{id}")
    public String updateCategory(@PathVariable int id, Model model){
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        return "categoriesAdd";
    }

    //Delete Categories in Admin Dashboard
    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCategory(@PathVariable int id){
        categoryService.removeCategoryById(id);
        return "redirect:/admin/categories";
    }

    // Product Section
    @GetMapping("/admin/products")
    public String products(Model model){
        model.addAttribute("products", productService.getAllProduct());
        //model.addAttribute("category", categoryService.getAllCategory());
        return "products";
    }

    //Add Products in Admin Dashboard
    @GetMapping("/admin/products/add")
    public String productAddGet(Model model){
        model.addAttribute("productDTO", new ProductDTO());
        model.addAttribute("categories", categoryService.getAllCategory());
        return "productsAdd";
    }

    //Add Products in Admin Dashboard
    @PostMapping("/admin/products/add")
    public String productAddPost(@ModelAttribute("productDTO")ProductDTO productDTO, @RequestParam("productImage") MultipartFile file, @RequestParam("imgName")String imgName) throws IOException {

        Product product = new Product();

        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()));
        product.setPrice(productDTO.getPrice());
        product.setAuthor(productDTO.getAuthor());
        product.setInStockNumber(productDTO.getInStockNumber());
        product.setPublisher(productDTO.getPublisher());
        product.setLanguage(productDTO.getLanguage());
        product.setiSBN(productDTO.getiSBN());
        
        product.setDescription(productDTO.getDescription());
        String imageUUID;

        if(!file.isEmpty()){
            imageUUID = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(directory, imageUUID);
            Files.write(fileNameAndPath, file.getBytes());
        } else{
            imageUUID = imgName;
        }
        product.setImageName(imageUUID);

        if(productDTO.getId()==null){
            productService.addProduct(product);
            Long id= productDTO.getId();
            System.out.println(id+"are you aded");
        }
        else {
        	Long id2= productDTO.getId();
            System.out.println(id2+"are you updated");
            productService.update(product);
        }

        
        //productService.addProduct(product);

        return "redirect:/admin/products";
    }


    //Delete Products in Admin Dashboard
    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable long id){
        productService.removeProductById(id);
        return "redirect:/admin/products";

    }

    //Update Products in Admin Dashboard
    @GetMapping("/admin/product/update/{id}")
    public String updateProductGet(@PathVariable long id, Model model){
    	System.out.println(id+"r u coming here");	
        Product newProduct = productService.getProductById(id);
        System.out.println(newProduct.getId()+" checking id here ");	
        ProductDTO dto = new ProductDTO();
        dto.setId(newProduct.getId());
        dto.setName(newProduct.getName());
        dto.setCategoryId((newProduct.getCategory().getId()));
        dto.setPrice(newProduct.getPrice());
        dto.setAuthor(newProduct.getAuthor());
        dto.setPublisher(newProduct.getPublisher());
        dto.setInStockNumber(newProduct.getInStockNumber());
        dto.setLanguage(newProduct.getLanguage());
        dto.setiSBN(newProduct.getiSBN());
        
        dto.setDescription(newProduct.getDescription());
        dto.setImageName(newProduct.getImageName());
        
        System.out.println(dto.getId()+" checking id ");	
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("productDTO", dto);
        return "productsedit";
    }
    @PostMapping("/admin/product/update/{id}")
    public String editProduct(@ModelAttribute("productDTO")ProductDTO productDTO,
			 @RequestParam("productImage") MultipartFile file, @RequestParam("imgName")String imgName) throws IOException {
    	System.out.println(productDTO.getId()+" r u coming here1");	
    	Product product = new Product();

        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()));
        product.setPrice(productDTO.getPrice());
        product.setAuthor(productDTO.getAuthor());
        product.setPublisher(productDTO.getPublisher());
        product.setLanguage(productDTO.getLanguage());
        product.setInStockNumber(productDTO.getInStockNumber());
        product.setiSBN(productDTO.getiSBN());
        product.setDescription(productDTO.getDescription());
        String imageUUID;
    	 if(!file.isEmpty()){
             imageUUID = file.getOriginalFilename();
             Path fileNameAndPath = Paths.get(directory, imageUUID);
             Files.write(fileNameAndPath, file.getBytes());
         } else{
             imageUUID = imgName;
         }
    	 product.setImageName(imageUUID);
     	Long id2= productDTO.getId();
        System.out.println(id2+"are you updated");
        productService.update(product);
    	return "redirect:/admin/products";
	}

    //Login Page
    @GetMapping("/login")
    public String signIn(){
        CurrentSession.cart.clear();
        return "login";
    }

    //Registration Page
    @GetMapping("/register")
    public String registerUserGet(){
        return "register";
    }

    //Registration Post Page
    @PostMapping("/register")
    public String registerUserPost(@ModelAttribute("user") User user, HttpServletRequest request) throws ServletException {
        String password = user.getPassword();
        user.setPassword((bCryptPasswordEncoder.encode(password)));
        List<Role> roles = new ArrayList<>();
        roles.add(roleDAO.findById(2));
        user.setRoles(roles);
        userDAO.save(user);
        request.login(user.getEmail(), password);
        return "redirect:/";
    }

    // Home Page
    @GetMapping({"/", "/home"})
    public String home(Model model){
        return "index";
    }

    // View All products section
    @GetMapping("/shop")
    public String shopAll(Model model){
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("products", productService.getAllProduct());
        return "shop";
    }

    // View specific products by id
    @GetMapping("/shop/viewproduct/{id}")
    public String viewProductbyId(Model model, @PathVariable int id){
        model.addAttribute("product", productService.getProductById(id));
        return "viewProduct";
    }

    // View products by category
    @GetMapping("/shop/category/{id}")
    public String shopCategory(Model model, @PathVariable int id){
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("products", productService.getAllProductsByCategoryId(id));
        return "shop";
    }

    // Checkout cart
    @GetMapping("/cart")
    public String cartGet(Model model){
        model.addAttribute("cartCount", CurrentSession.cart.size());
        model.addAttribute("total", CurrentSession.cart.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("cart", CurrentSession.cart);
        return "cart";
    }

    // Adding products to the cart
    @GetMapping("/addToCart/{id}")
    public String addProductToCart(@PathVariable int id){
        CurrentSession.cart.add(productService.getProductById(id));
        return "redirect:/shop";
    }

    // Removing products to the cart
    @GetMapping("cart/removeItem/{index}")
    public String cartRemoveProduct(@PathVariable int index){
        CurrentSession.cart.remove(index);
        return "redirect:/cart";
    }

    // Checkout Page
    @GetMapping("/checkout")
    public String checkoutProducts(Model model){
        model.addAttribute("total", CurrentSession.cart.stream().mapToDouble(Product::getPrice).sum());
        return "checkout";
    }

    // Order placed
    @GetMapping("/payNow")
    public String orderPlaced(Model model){
        model.addAttribute("result", "Total price is $ "+ CurrentSession.cart.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("items", CurrentSession.cart);
        model.addAttribute("number", String.valueOf(new Random().nextInt(100000,999999)));
        return "orderPlaced";
    }
}

