/**
 * 
 */
package br.com.livrariaweb.dao;


/**
 * @author Eduardo
 *
 */
public class CadastroSingletonAction<T> {

	@SuppressWarnings("rawtypes")
	private static CadastroSingletonAction instance = new CadastroSingletonAction();
	private CadastroInterface<T> cadastro;

	private CadastroSingletonAction() {	}
	
    @SuppressWarnings("rawtypes")
	public static CadastroSingletonAction getInstance() {
        return instance;
    }
    
    public void setDAOAction (CadastroInterface<T> cadastro) {
    	if (cadastro != null)
    		this.cadastro = cadastro;
    	else
    		throw new RuntimeException("CadastroSingletonAction: setCadastro não pode ser nulo.");
    }
    
    public CadastroInterface<T> getDAOAction () {
    	return this.cadastro;
    }

	
}
