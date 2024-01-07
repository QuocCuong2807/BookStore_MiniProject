package model;

import java.sql.Date;

public class Book {
	
	private String bookId;
	private String bookName;
	private String images;
	private Date publishDate;
	private int bookPrice;
	private int discount;
	private String bookDescription;
	private String publishCompany;
	private Author author;
	private Category category;
	private String languages;
	
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(String bookId) {
		super();
		this.bookId = bookId;
	}

	public Book(String bookId, String bookName, String images, Date publishDate, int bookPrice, int discount,
			String bookDescription, String publishCompany, Author author, Category category, String languages) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.images = images;
		this.publishDate = publishDate;
		this.bookPrice = bookPrice;
		this.discount = discount;
		this.bookDescription = bookDescription;
		this.publishCompany = publishCompany;
		this.author = author;
		this.category = category;
		this.languages = languages;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public int getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(int bookPrice) {
		this.bookPrice = bookPrice;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public String getBookDescription() {
		return bookDescription;
	}

	public void setBookDescription(String bookDescription) {
		this.bookDescription = bookDescription;
	}

	public String getPublishCompany() {
		return publishCompany;
	}

	public void setPublishCompany(String publishCompany) {
		this.publishCompany = publishCompany;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getLanguages() {
		return languages;
	}

	public void setLanguages(String languages) {
		this.languages = languages;
	}
	
	
	
	
}
