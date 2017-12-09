package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.model.Book;
import com.spring.repository.BookRepository;
@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookRepository bookRepository;

	@Override
	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	@Override
	public Book findByID(Integer id) {
		return bookRepository.findById(id);
	}

	@Override
	public void save(Book book) {
		boolean status = bookRepository.save(book);

		if (status)
			System.out.println("-> Added Successfully  !");
		else
			System.out.println("-> Add Fail !");
	}

	@Override
	public void remove(Integer id) {
		if(bookRepository.remove(id))
			System.out.println("-> Removed Successfully !");
		else
			System.out.println("-> Remove Fiil");
	}

	@Override
	public void update(Book book) {
		if(bookRepository.update(book))
			System.out.println(" -> Updated Successfully !");
		else 
			System.out.println("-> Update Fail!");
		}
}
