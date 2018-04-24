/**
 * 
 */
package br.com.livrariaweb.controller;



/**
 * @author Eduardo
 *
 */
public class ActionFactory {

	@SuppressWarnings("deprecation")
	public static Action create(String action){
        Action actionObject = null;
        String nomeClasse   = "br.com.livrariaweb.action." + action + "Action";

        @SuppressWarnings("rawtypes")
		Class classe  = null;
        Object objeto = null;

        try {
            classe = Class.forName(nomeClasse);
            objeto = classe.newInstance();

        } catch(Exception e){
        	e.printStackTrace();
            return null;
        }

        if (!(objeto instanceof Action)) return null;

        actionObject = (Action) objeto;

        return actionObject;
    }
	
}
