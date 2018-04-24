/**
 * 
 */
package br.com.livrariaweb.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Eduardo
 *
 */
public class Service {

	protected static EntityManagerFactory emf;
	
	public Service() {
		emf = Persistence.createEntityManagerFactory("LivrariaWebDB");
	}
	
}
