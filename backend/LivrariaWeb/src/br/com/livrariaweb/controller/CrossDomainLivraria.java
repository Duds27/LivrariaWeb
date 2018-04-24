/**
 * 
 */
package br.com.livrariaweb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ecorrea
 *
 */
public class CrossDomainLivraria implements CrossDomainInterface {

	@Override
	public boolean setCrossDomain(HttpServletRequest request, HttpServletResponse response) {
		if (request.getMethod().equals("OPTIONS") || request.getMethod().equals("GET") || request.getMethod().equals("POST") || request.getMethod().equals("PUT") || request.getMethod().equals("DELETE")) {
    		response.addHeader("Access-Control-Allow-Origin", "http://localhost:8070");
    		response.addHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
    	    response.addHeader("Access-Control-Allow-Headers", "Content-Type");
    	    return true;
		}
		else
			return false;
	}

}
