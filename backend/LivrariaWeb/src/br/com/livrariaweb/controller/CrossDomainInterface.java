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
public interface CrossDomainInterface {
	public abstract boolean setCrossDomain (HttpServletRequest request, HttpServletResponse response);
}
