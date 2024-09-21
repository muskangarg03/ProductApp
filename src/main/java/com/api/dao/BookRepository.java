package com.api.dao;

import org.springframework.data.repository.CrudRepository;

import com.api.entities.Book;

public interface BookRepository extends CrudRepository<Book, Integer>{

	public Book findById(int Id);
	
}
