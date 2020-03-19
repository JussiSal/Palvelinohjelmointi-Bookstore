package com.example.Bookstore.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long cateid;
	private String name;
	@JsonBackReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	private List<Book> books;
	
	public Long getId() {
		return cateid;
	}
	public void setId(Long id) {
		this.cateid = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	public Category(String name) {
		this.name = name;
	}
	public Category() {
	}
	@Override
	public String toString() {
		return "Category [id=" + cateid + ", name=" + name + ", books=" + books + "]";
	}
	
	
}
