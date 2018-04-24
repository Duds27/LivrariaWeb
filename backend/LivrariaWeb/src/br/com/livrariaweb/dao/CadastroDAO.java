/**
 * 
 */
package br.com.livrariaweb.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


/**
 * @author Eduardo
 *
 */
public abstract class CadastroDAO<T> extends Service implements CadastroInterface<T> {

private final Class<T> typeClass;
	
	public CadastroDAO(Class<T> typeClass) {
		this.typeClass = typeClass;
	}
	
	@Override
	public void postObjectDatabase(T obj) {
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();	
			em.persist(obj);
		em.getTransaction().commit();	
	    em.close();
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> getObjectDatabase() {
		
		List<T> objects;
		
		EntityManager em = emf.createEntityManager();
		String s = "Select f From " + typeClass.getName() + " f";
		Query q 		 = em.createQuery(s);
		objects 		 = q.getResultList();
		em.close();
		
		return objects;
		
	}
	
	@Override
	public void deleteObjectDatabase(T obj, long id) {
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();	
			obj = em.find(typeClass, id);
			em.remove(obj);
		em.getTransaction().commit();
		em.flush();
	    em.close();
		
	}
	
	@Override
	public void putObjectDatabase(T obj) {
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();	
			em.flush();
			em.merge(obj);
			em.flush();
		em.getTransaction().commit();
	    em.close();
		
	}	
	
}
