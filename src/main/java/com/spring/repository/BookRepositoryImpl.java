package com.spring.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.javafaker.Faker;
import com.spring.model.Book;
@Repository
public class BookRepositoryImpl implements BookRepository {

	private List<Book> books = new ArrayList<>();

	public BookRepositoryImpl() {
		// Call faker to use Dummy Data

		Faker faker = new Faker();
		for (int i = 0; i < 10; i++) {
			String title = faker.book().title();
			String author = faker.book().author();

			String image = faker.internet().image(100, 100, false, null);

			Book book = new Book(i + 1, title, 2005 + i, author, i + 600, image);
			books.add(book);

		}
	}

	@Override
	public List<Book> findAll() {
		return books;
	}

	@Override
	public Book findById(Integer id) {

		for (Book book : books) {
			if (book.getId() == id)
				return book;
		}
		return null;
	}

	@Override
	public boolean save(Book book) {
		return books.add(book);
	}

	@Override
	public boolean remove(Integer id) {
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getId() == id) {
				books.remove(i);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean update(Book book) {
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getId() == book.getId()) {
				books.set(i, book);
				return true;
			}
		}
		return false;
	}
}
