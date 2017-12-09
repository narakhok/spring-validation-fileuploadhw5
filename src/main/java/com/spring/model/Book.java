package com.spring.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;



public class Book {
	private int id;
	@NotEmpty(message = "Book tile cannot be empty!")
	//@Size(min = 10, max = 30, message = "Book title must be between {2} and {1} characters!")
	private String title;
	private int publishDate;
	private String author;
	private int page;
	private String coverImage;

	public Book() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(int publishDate) {
		this.publishDate = publishDate;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}

	public Book(int id, String title, int publishDate, String author, int page, String coverImage) {
		super();
		this.id = id;
		this.title = title;
		this.publishDate = publishDate;
		this.author = author;
		this.page = page;
		this.coverImage = coverImage;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", publishDate=" + publishDate + ", author=" + author + ", page="
				+ page + ", coverImage=" + coverImage + "]";
	}

}
