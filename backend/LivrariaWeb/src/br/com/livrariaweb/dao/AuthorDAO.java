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

import br.com.livrariaweb.bean.Author;
import br.com.livrariaweb.bean.Book;

/**
 * @author Eduardo
 *
 */
public class AuthorDAO<T> extends CadastroDAO<T> {

	public AuthorDAO(Class<T> typeClass) {
		super(typeClass);
	}
	
	public long GetAuthorId(String name) {

		EntityManager em = emf.createEntityManager();
		
		CriteriaBuilder builder 	   		 	 = em.getCriteriaBuilder();
		CriteriaQuery<Author> cQuery 			 = builder.createQuery(Author.class);
		Root<Author> from 		   			 = cQuery.from(Author.class);
		ParameterExpression<String> authorName = builder.parameter(String.class);
		cQuery.select(from).where
			(builder.equal(from.<String>get("authorName"), authorName));
		
		TypedQuery<Author> typeQuery = em.createQuery(cQuery);
		typeQuery.setParameter(authorName, name);
		
		Author author = typeQuery.getSingleResult();
		
		em.close();
		
		return author.getAuthorId();
	}
	
	public Author GetAuthor(long bookId) {
		EntityManager em = emf.createEntityManager();
		Book book     = em.find(Book.class, bookId);
		Author author = (Author) book.getAuthors().toArray()[0];
		em.close();
		return author;
	}

}
