package com.spring.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.spring.model.Book;
import com.spring.service.BookService;

@Controller
public class BookController {
	@Autowired
	private BookService bookService;

	// @RequestMapping(value="/index")
	@GetMapping("/index")
	public String index(Model model) {

		List<Book> books = bookService.findAll();
		model.addAttribute("books", books);
		return "book/index";
	}

	@GetMapping("/book/{id}") // @RequestMapping(value="/book/{id}")
	public String bookdetail(@PathVariable("id") Integer id, Model model) {
		Book book = bookService.findByID(id);
		model.addAttribute("bookdetail", book);
		return "book/bookdetail";
	}

	@GetMapping("/book/addbook") // @RequestMapping(value="/book/addbook")
	public String addBook(Model model) {

		model.addAttribute("addStatus", true);
		model.addAttribute("book", new Book());
		return "book/add";
	}

	@PostMapping("/book/addbook") // @RequestMapping(value="/book/addbook",method = RequestMethod.POST)
	public String actionAddBook(@Valid Book book, BindingResult result) {

		if (result.hasErrors()) {
			for (FieldError error : result.getFieldErrors()) {
				System.out.println(error.getField() + ":" + error.getDefaultMessage());

			}
		}

		System.out.println(book);
		bookService.save(book);
		return "redirect:/index";
	}

	@PostMapping("/book/remove") // @RequaestMapping(value ="/book/remove",method = RequestMethod.POST)
	public String removeBook(@RequestParam("id") Integer id) {
		System.out.println("Id : " + id);
		bookService.remove(id);
		return "redirect:/index";
	}

	@GetMapping("/book/edit") // @RequestMapping(value ="/book/edit")
	public String editBook(@RequestParam("id") Integer id, Model model) {
		System.out.println("Id:" + id);

		Book book = bookService.findByID(id);
		model.addAttribute("book", book);
		model.addAttribute("addStatus", false);
		return "book/add";

	}

	@PostMapping("/book/update") // @RequestMapping(value ="/book/update",method = RequestMethod.POST)
	public String updateBook(Book book) {
		System.out.println(book);
		bookService.update(book);
		return "redirect:/index";
	}

}
