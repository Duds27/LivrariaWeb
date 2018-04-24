/**
 * 
 */
package br.com.livrariaweb.dao;

import java.util.List;

/**
 * @author Eduardo
 *
 */
public interface CadastroInterface<T> {

	public abstract void postObjectDatabase(T obj);
	public abstract List<T> getObjectDatabase();
	public abstract void deleteObjectDatabase(T obj, long id);
	public abstract void putObjectDatabase(T obj);
	
}
