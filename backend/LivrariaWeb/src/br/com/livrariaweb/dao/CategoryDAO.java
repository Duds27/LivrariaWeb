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

import br.com.livrariaweb.bean.Category;

/**
 * @author Eduardo
 *
 */
public class CategoryDAO<T> extends CadastroDAO<T> {

	public CategoryDAO(Class<T> typeClass) {
		super(typeClass);
	}
	
	public long GetCategoryId(String name) {

		EntityManager em = emf.createEntityManager();
		
		CriteriaBuilder builder 	   		 	 = em.getCriteriaBuilder();
		CriteriaQuery<Category> cQuery 			 = builder.createQuery(Category.class);
		Root<Category> from 		   			 = cQuery.from(Category.class);
		ParameterExpression<String> categoryName = builder.parameter(String.class);
		cQuery.select(from).where
			(builder.equal(from.<String>get("categoryName"), categoryName));
		
		TypedQuery<Category> typeQuery = em.createQuery(cQuery);
		typeQuery.setParameter(categoryName, name);
		
		Category category = typeQuery.getSingleResult();
		
		em.close();
		
		return category.getCategoryId();
	}
	
	public Category GetCategory(long id) {
		
		EntityManager em = emf.createEntityManager();
		
		CriteriaBuilder builder 	   		 = em.getCriteriaBuilder();
		CriteriaQuery<Category> cQuery 		 = builder.createQuery(Category.class);
		Root<Category> from 		   		 = cQuery.from(Category.class);
		ParameterExpression<Long> categoryId = builder.parameter(Long.class);
		cQuery.select(from).where
			(builder.equal(from.<Long>get("categoryId"), categoryId));
		
		TypedQuery<Category> typeQuery = em.createQuery(cQuery);
		typeQuery.setParameter(categoryId, id);
		
		Category category = typeQuery.getSingleResult();
		
		em.close();
		
		return category;
		
	}

}
