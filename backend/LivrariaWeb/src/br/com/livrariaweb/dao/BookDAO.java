/**
 * 
 */
package br.com.livrariaweb.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import br.com.livrariaweb.bean.Book;

/**
 * @author Eduardo
 *
 */
public class BookDAO<T> extends CadastroDAO<T> {

	public BookDAO(Class<T> typeClass) {
		super(typeClass);
	}

	public long GetBookId(String title) {

		EntityManager em = emf.createEntityManager();
		
		CriteriaBuilder builder 	   		  = em.getCriteriaBuilder();
		CriteriaQuery<Book> cQuery 			  = builder.createQuery(Book.class);
		Root<Book> from 		   			  = cQuery.from(Book.class);
		ParameterExpression<String> bookTitle = builder.parameter(String.class);
		cQuery.select(from).where
			(builder.equal(from.<String>get("bookTitle"), bookTitle));
		
		TypedQuery<Book> typeQuery = em.createQuery(cQuery);
		typeQuery.setParameter(bookTitle, title);
		
		Book book = typeQuery.getSingleResult();
		
		em.close();
		
		return book.getBookId();
	}
	
	public Book GetBook(long id) {
		
		EntityManager em = emf.createEntityManager();
		
		CriteriaBuilder builder 	   	 = em.getCriteriaBuilder();
		CriteriaQuery<Book> cQuery 		 = builder.createQuery(Book.class);
		Root<Book> from 		   		 = cQuery.from(Book.class);
		ParameterExpression<Long> bookId = builder.parameter(Long.class);
		cQuery.select(from).where
			(builder.equal(from.<Long>get("bookId"), bookId));
		
		TypedQuery<Book> typeQuery = em.createQuery(cQuery);
		typeQuery.setParameter(bookId, id);
		
		Book book = typeQuery.getSingleResult();
		
		em.close();
		
		return book;
		
	}
}
