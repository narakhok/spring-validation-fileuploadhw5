package com.spring.service;

import java.util.List;

import com.spring.model.Book;

public interface BookService {
	public List<Book> findAll();

	public Book findByID(Integer id);

	public void save(Book book);

	public void remove(Integer id);

	public void update(Book book);
}
