/**
 * 
 */
package br.com.livrariaweb.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Eduardo
 *
 */
public interface Action {
	
	public abstract void execute(HttpServletRequest request, HttpServletResponse response, String methodType) throws ServletException, IOException;
    public abstract void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	public abstract void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	public abstract void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	public abstract void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	public abstract void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	

}
