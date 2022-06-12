# Bookshop
E-Commerce Project using Spring boot, Hibernate, Thymeleaf &amp; MySQL 

### TECHNOLOGIES:
* Java
* Spring framework (Sping boot, Spring MVC, Hibernate, Spring Security)
* MySQL
* Thymeleaf
* Bootstrap
* jQuery

### FUNCTIONALITY/FEATURES:
* User register/login with role based authorization.
* Admin portal for CRUD operations on products (only accessible by admin user).
* Admin portal for CRUD operation on categories and products.
* Store: display all products, filter products by category, order results by different criteria or filter by search input.
* Purchasing: view details of product, add to shopping cart, checkout.
* Responsive design.

### STEPS TO COMPILE
* Run your project
* Run following querties in your database:
  * INSERT INTO `bookshopdb2`.`roles` (`id`, `name`) VALUES ('1', 'ROLE_ADMIN');
  * INSERT INTO `bookshopdb2`.`roles` (`id`, `name`) VALUES ('2', 'ROLE_USER');
* For admin role, register as a normal user, note the user id from the users table and run the following query with the id:
  * INSERT INTO user_role (USER_ID,ROLE_ID) values
(user_id_number, 1);

### TO-DO
* Mail security
* Edit user profile
* View order history
* Place order with shipping address and payment info

### SCREENSHOTS
* Admin-portal

![Admin home](https://github.com/Namrata2108/Bookshop/blob/fcea404e97d428abd8e5293974eb39c3079a39a6/images/admin_home.png)

![Manage Category](https://github.com/Namrata2108/Bookshop/blob/fcea404e97d428abd8e5293974eb39c3079a39a6/images/manage_category.png)

![Manage Product](https://github.com/Namrata2108/Bookshop/blob/9ced30822fb5740f8b8ad082f628c84a5c1dbabe/images/manage_product.png)

* User-portal
    
![Login page](https://github.com/Namrata2108/Bookshop/blob/fcea404e97d428abd8e5293974eb39c3079a39a6/images/loginpage.png)

![Signup page](https://github.com/Namrata2108/Bookshop/blob/fcea404e97d428abd8e5293974eb39c3079a39a6/images/signup_page.png)

![Product page](https://github.com/Namrata2108/Bookshop/blob/fcea404e97d428abd8e5293974eb39c3079a39a6/images/user_productspage.png)

![View product](https://github.com/Namrata2108/Bookshop/blob/fcea404e97d428abd8e5293974eb39c3079a39a6/images/viewproduct.png)

![Product with category](https://github.com/Namrata2108/Bookshop/blob/fcea404e97d428abd8e5293974eb39c3079a39a6/images/product_with_category.png)

![Cart](https://github.com/Namrata2108/Bookshop/blob/fcea404e97d428abd8e5293974eb39c3079a39a6/images/cart.png)

![Receipt](https://github.com/Namrata2108/Bookshop/blob/fcea404e97d428abd8e5293974eb39c3079a39a6/images/receipt.png)
