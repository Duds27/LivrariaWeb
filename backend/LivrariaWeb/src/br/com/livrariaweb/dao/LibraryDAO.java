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

import br.com.livrariaweb.bean.Library;

/**
 * @author Eduardo
 *
 */
public class LibraryDAO<T> extends CadastroDAO<T> {

	public LibraryDAO(Class<T> typeClass) {
		super(typeClass);
	}
	
	public Library GetLibrary(long id) {
		
		EntityManager em = emf.createEntityManager();
		
		CriteriaBuilder builder 	   	 	= em.getCriteriaBuilder();
		CriteriaQuery<Library> cQuery    	= builder.createQuery(Library.class);
		Root<Library> from 		   		 	= cQuery.from(Library.class);
		ParameterExpression<Long> libraryId = builder.parameter(Long.class);
		cQuery.select(from).where
			(builder.equal(from.<Long>get("libraryId"), libraryId));
		
		TypedQuery<Library> typeQuery = em.createQuery(cQuery);
		typeQuery.setParameter(libraryId, id);
		
		Library library = typeQuery.getSingleResult();
		
		em.close();
		
		return library;
		
	}
	
}
