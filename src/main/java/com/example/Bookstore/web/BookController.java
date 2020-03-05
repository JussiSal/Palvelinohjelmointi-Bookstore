package com.example.Bookstore.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.CategoryRepository;

@Controller
public class BookController {
	
	//Injektoidaan itsetehty repository, jotta sitä ja sen tietoja voidaan käyttää. Esim. lähetetään kaikki relaation tiedot sivulle näytetäväksi
	@Autowired
	private BookRepository repo;
	@Autowired
	private CategoryRepository caterepo;
	
	@GetMapping("/index")
	public String indexGet() {
		return "index";
	}
	
	//Lähetetään modelilla kaikki repon tietorivit html:lle CrudRepositoryn findall() metodilla (peritty omalle repolle).
	@GetMapping("/booklist")
	public String booklistGet(Model model) {
		model.addAttribute("books", repo.findAll());
		return "booklist";
	}
	
	//id tieto saadaan urlisata /delete/X ja @PathVAriable saa sen käyttöön
	@GetMapping("/delete/{id}")
	public String bookDelete(@PathVariable("id") Long id, Model model) {
		repo.deleteById(id);
		return "redirect:../booklist";
	}
	
	@GetMapping("/addbook")
	public String addbookGet(Model model) {
		model.addAttribute("categorys", caterepo.findAll());
		model.addAttribute("book", new Book());
		return "addbook";
	}
	
	@PostMapping("/savebook")
	public String savebookPost(Book book) {
		repo.save(book);
		return "redirect:booklist";
	}
	
	@GetMapping("/editbook/{id}")
	public String editbookGet(@PathVariable("id") Long id, Model model) {
		Book book = repo.findById(id).get();
		model.addAttribute("book", book);
		model.addAttribute("categorys", caterepo.findAll());
		return "editbook";
	}
	
}
