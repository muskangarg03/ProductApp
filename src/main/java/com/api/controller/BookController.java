package com.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.api.entities.Book;
import com.api.services.BookService;

/*
@Controller
public class BookController {
	
	@RequestMapping(value="/books", method = RequestMethod.GET)
	@ResponseBody
	
	public String getBooks() {
		return "this is testing book first";
	}
}
*/

/*
@RestController     //It can be use in case for REST APIs
public class BookController {
	
	//@RequestMapping(value="/books", method = RequestMethod.GET)
	//No need for ResponseBody in case of RestCOntroller
	
	@GetMapping("/books")        //Method use is GET
	public String getBooks() {
		return "this is testing book first";
	}
}
*/


/*
@RestController
public class BookController{
	
	@GetMapping("/books")
	public Book getBooks() {
		
		Book book = new Book();
		book.setId(1234);
		book.setTitle("Java Complete Reference");
		book.setAuthor("XYZ");
		return book;
	}
}
*/



@RestController
public class BookController{
	
	@Autowired
	private BookService bookService;
	
	
	//Get all books handler
//	@GetMapping("/books")
//	public List<Book> getBooks()
//	{
//		return this.bookService.getAllBooks();
//	}
	
	
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getBooks(){		//ResponseEntity is a class that represents an HTTP response
		List<Book> list = bookService.getAllBooks();
		if(list.size()<=0)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		//return ResponseEntity.of(Optional.of(list));      //Optional is a class that may or may not contain non-null value. It prevent null-pointer exception.
		return ResponseEntity.status(HttpStatus.CREATED).body(list);
	}
	
	
	
	
	// Get Single Book Handler
//	@GetMapping("/books/{id}")
//	public Book getBook(@PathVariable("id") int id)
//	{
//		return bookService.getBookById(id);
//	}
	
	
	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBook(@PathVariable("id") int id)
	{
		Book book = bookService.getBookById(id);
		if(book == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(book));
	}

	
	
	
	// New Book Handler
//	@PostMapping("/books")
//	public Book addBook(@RequestBody Book book)
//	{
//		Book b= this.bookService.addBook(book);
//		System.out.println(book);
//		return b;
//	}
	
	
	@PostMapping("/books")
		public ResponseEntity<Book> addBook(@RequestBody Book book){
			Book b =null;
			try {
				b = this.bookService.addBook(book);
				System.out.println(book);
				ResponseEntity.status(HttpStatus.CREATED).build();
				return ResponseEntity.of(Optional.of(b));
			}catch(Exception e)
			{
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	
	
	 //Delete Book Handler
//	@DeleteMapping("/books/{bookId}")
//	public void deleteBook(@PathVariable("bookId") int bookId)
//	{
//		this.bookService.deleteBook(bookId);
//	}
	
	
	@DeleteMapping("/books/{bookId}")
	public ResponseEntity<Void> deleteBook(@PathVariable("bookId") int bookId)
	{
		try {
			this.bookService.deleteBook(bookId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	//Update Book Handler
//	@PutMapping("/books/{bookId}")
//	public Book updateBook(@RequestBody Book book, 
//			@PathVariable("bookId") int bookId)
//	{
//		this.bookService.updateBook(book, bookId);
//		return book;
//	}
	
	
	@PutMapping("/books/{bookId}")
	public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable("bookId") int bookId){
		try {
			this.bookService.updateBook(book, bookId);
			return ResponseEntity.ok().body(book);
		}catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}

