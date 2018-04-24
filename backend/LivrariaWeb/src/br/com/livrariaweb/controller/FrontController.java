/**
 * 
 */
package br.com.livrariaweb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author Eduardo
 *
 */
@WebServlet("/FrontController")
public class FrontController extends HttpServlet {
	
	public final static String POST_METHOD 	  = "POST";
	public final static String GET_METHOD 	  = "GET";
	public final static String PUT_METHOD 	  = "PUT";
	public final static String DELETE_METHOD  = "DELETE";
	public final static String OPTIONS_METHOD = "OPTIONS";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String methodType) throws ServletException, IOException {
        
    	response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out 	= response.getWriter();
        String action   	= request.getParameter("action");
        Action actionObject = null;

        try {
          if(action == null || action.equals(""))
              response.sendRedirect("http://localhost/livraria_hbsis/view/notfound.html");

          actionObject = ActionFactory.create(action);

         if(actionObject != null){
              actionObject.execute(request, response, methodType);
          }
        } finally {
            out.close();
        }
    }

    
    
    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response, POST_METHOD);
    }
    
    
    
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response, GET_METHOD);
    } 

    
    
    @Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response, PUT_METHOD);
	}

    
    
    @Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	processRequest(request, response, DELETE_METHOD);
	}

    

	/** 
     * Handles the HTTP <code>OPTIONS</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	processRequest(request, response, OPTIONS_METHOD);
    }

}
