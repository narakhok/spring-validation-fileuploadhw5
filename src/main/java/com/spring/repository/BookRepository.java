package com.spring.repository;

import java.util.List;

import com.spring.model.Book;

public interface BookRepository {
	public List<Book> findAll();

	public Book findById(Integer id);

	public boolean save(Book book);

	public boolean remove(Integer id);

	public boolean update(Book book);
}
