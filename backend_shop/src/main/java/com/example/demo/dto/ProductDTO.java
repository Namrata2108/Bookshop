package com.example.demo.dto;


public class ProductDTO {

    private Long id;
    private String name;

    private int categoryId;
    private double price;
    private long iSBN;
    private int inStockNumber;
    private String language;
    private String publisher;
    private String author;
    public long getiSBN() {
		return iSBN;
	}
	public void setiSBN(long ISBN) {
		this.iSBN = ISBN;
	}
	public int getInStockNumber() {
		return inStockNumber;
	}

	public void setInStockNumber(int inStockNumber) {
		this.inStockNumber = inStockNumber;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	private String description;
    private String imageName;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}

}

