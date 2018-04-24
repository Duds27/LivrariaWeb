package br.com.livrariaweb.teste;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import br.com.livrariaweb.bean.Author;
import br.com.livrariaweb.bean.Book;
import br.com.livrariaweb.bean.Category;
import br.com.livrariaweb.bean.Library;
import br.com.livrariaweb.dao.CadastroDAO;
import br.com.livrariaweb.dao.CategoryDAO;

public class TesteJPA {

	public static void main(String[] args) {
		
		//TesteCreateCategory();
		//TesteCreateAuthorBook();
		//TesteCreateLibrary();
		TesteListAllCategory();
		
	}
	
	@SuppressWarnings("unused")
	private static void TesteCreateCategory() {
		Category c1 = new Category();
		//Category c2 = new Category();
		Category c3 = new Category();
		Category c4 = new Category();
		
		c1.setCategoryName("Engenharia");
		//c2.setCategoryName("Engenharia");
		c3.setCategoryName("Computação");
		c4.setCategoryName("Drama");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("LivrariaWebDB");
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
			em.persist(c1);
			//em.persist(c2);
			em.persist(c3);
			em.persist(c4);
		em.getTransaction().commit();	
	    em.close();
	}
	
	@SuppressWarnings("unused")
	private static void TesteCreateAuthorBook( ) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("LivrariaWebDB");
		EntityManager em   	     = emf.createEntityManager();
		
		CriteriaBuilder builder 	   			 = em.getCriteriaBuilder();
		CriteriaQuery<Category> cQuery 			 = builder.createQuery(Category.class);
		Root<Category> from 		   			 = cQuery.from(Category.class);
		ParameterExpression<String> categoryName = builder.parameter(String.class);
		cQuery.select(from).where
			(builder.equal(from.<String>get("categoryName"), categoryName));
		
		TypedQuery<Category> typeQuery = em.createQuery(cQuery);
		typeQuery.setParameter(categoryName, "Computação");
		
		Category category = typeQuery.getSingleResult();
		
		
		Author a1 = new Author();
		Author a2 = new Author();
		
		Book b1 = new Book();
		Book b2 = new Book();
		
		a1.setAuthorName("Eric Gama");
		a2.setAuthorName("Fabio Caversan");
		
		
		b1.setBookTitle("Padrões de Projeto");
		b1.setBookPublisher("Elselvier");
		b1.setBookPrice(157.54);
		b1.setBookYear(2010);
		b1.setCategory(category);
		b1.getAuthors().add(a1);
		
		
		b2.setBookTitle("Data Structure");
		b2.setBookPublisher("Caversan");
		b2.setBookPrice(254.43);
		b2.setBookYear(2015);
		b2.setCategory(category);
		b2.getAuthors().add(a1);
		b2.getAuthors().add(a2);
		
		a1.getBooks().add(b1);
		a1.getBooks().add(b2);
		a2.getBooks().add(b2);
		
		em.getTransaction().begin();
			em.persist(b1);
			em.persist(b2);
			em.persist(a1);
			em.persist(a2);
		em.getTransaction().commit();	
	    em.close();
	    emf.close();
		
	}
	
	private static void TesteCreateLibrary() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("LivrariaWebDB");
		EntityManager em   	     = emf.createEntityManager();
		
		
		CriteriaBuilder builder 	   		 = em.getCriteriaBuilder();
		CriteriaQuery<Book> cQuery 			 = builder.createQuery(Book.class);
		Root<Book> from 		   			 = cQuery.from(Book.class);
		ParameterExpression<String> bookTitle = builder.parameter(String.class);
		cQuery.select(from).where
			(builder.equal(from.<String>get("bookTitle"), bookTitle));
		
		TypedQuery<Book> typeQuery = em.createQuery(cQuery);
		typeQuery.setParameter(bookTitle, "Padrões de Projeto");
		
		Book book = typeQuery.getSingleResult();
		
		Library lib = new Library();
		lib.setBook(book);
		lib.setLibraryBookCount(3);
		
			em.getTransaction().begin();
			em.persist(lib);
		em.getTransaction().commit();	
	    em.close();
	    emf.close();
		
	}

	private static void TesteListAllCategory() {
	
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("LivrariaWebDB");
		//EntityManager em   	     = emf.createEntityManager();
		
		CategoryDAO<Category> dao = new CategoryDAO<Category>(Category.class);
		
		ArrayList<Category> lista = (ArrayList<Category>) dao.getObjectDatabase();
		
		for (Category category : lista) {
			System.out.println(category.toString());
		}
		
	}
	
}
