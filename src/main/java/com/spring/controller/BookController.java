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
import org.springframework.web.multipart.MultipartFile;

import com.spring.model.Book;
import com.spring.service.BookService;
import com.spring.service.FileUploadService;

@Controller
public class BookController {
	@Autowired
	private BookService bookService;
	@Autowired
	private FileUploadService fileUpload;

	// @RequestMapping(value="/index")
	@GetMapping(value = { "", "/index" })
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

	// Add vilidator we user @Valid and BindingResult to show resutl of error upload
	// file
	@PostMapping("/book/addbook") // @RequestMapping(value="/book/addbook",method = RequestMethod.POST)
	public String actionAddBook(@RequestParam("file") MultipartFile file, Model model, @Valid Book book,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			for (FieldError error : bindingResult.getFieldErrors()) {
				System.out.println(error.getField() + ":" + error.getField());

			}
			model.addAttribute("addStatus", true);
			model.addAttribute("book", book);
			return "/book/add";
		}
		String filePath = fileUpload.upload(file);
		book.setCoverImage(filePath);
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
	public String updateBook(@RequestParam("file") MultipartFile file, Model model, @Valid Book book,
			BindingResult result) {
		if (result.hasErrors()) {
			for (FieldError error : result.getFieldErrors()) {
				System.out.println(error.getField() + ": " + error.getDefaultMessage());
			}
			model.addAttribute("addStatus", false);
			model.addAttribute("book", book);
			System.out.println(book);
			bookService.update(book);
			return "/book/add";
		}
		if (!file.isEmpty()) {
			System.out.println("file" + file.getOriginalFilename());
			String filePath = fileUpload.upload(file);
			book.setCoverImage(filePath);
		}

		System.out.println(book);
		bookService.update(book);

		return "redirect:/index";
	}

}
