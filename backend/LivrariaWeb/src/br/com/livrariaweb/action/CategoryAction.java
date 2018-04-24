/**
 * 
 */
package br.com.livrariaweb.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.livrariaweb.bean.Category;
import br.com.livrariaweb.controller.Action;
import br.com.livrariaweb.controller.FrontController;
import br.com.livrariaweb.dao.CadastroSingletonAction;
import br.com.livrariaweb.dao.CategoryDAO;

/**
 * @author Eduardo
 *
 */
public class CategoryAction implements Action {

	@SuppressWarnings("unchecked")
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, String methodType) throws ServletException, IOException {
		
		JSONObject json    = new JSONObject();
		PrintWriter writer = response.getWriter();
		
		String categoryName = null;
		Category category   = null;
		
		long category_id = -1L;
		
		switch (methodType) {
			
			case FrontController.POST_METHOD:
				
				StringBuffer jb = new StringBuffer();
				String line     = null;
				
				try {
				
					BufferedReader reader = request.getReader();
					while ((line = reader.readLine()) != null)
						jb.append(line);
					
				} catch (RuntimeException e) {
					
					e.printStackTrace();
					System.out.println("Parâmetro inválido");
					json.put("category_result", 0);
					writer.write(json.toString());
					
				}
				
				JSONObject jsonObject = null;
				try {

					jsonObject = new JSONObject(jb.toString());
				
				} catch (JSONException e) {

					e.printStackTrace();
					json.put("category_result", 0);
					writer.write(json.toString());

				}
				
				System.out.println("[OBJECT] = " + jsonObject.toString());
				categoryName = jsonObject.getString("category_name"); 
							
				if (categoryName == null) {
					System.out.println("[Method " + FrontController.POST_METHOD + "] Parâmetro [category_name] inválido");
					json.put("category_result", 0);
					writer.write(json.toString());				
				}
				
				category = new Category();
				category.setCategoryName(categoryName);
				
				try {

					CadastroSingletonAction.getInstance().setDAOAction(new CategoryDAO<>(Category.class));
					CategoryDAO<Category> dao = (CategoryDAO<Category>) CadastroSingletonAction.getInstance().getDAOAction();
					
					dao.postObjectDatabase(category);
					
				} catch (RuntimeException e) {

					e.printStackTrace();
					System.out.println(category.toString());
					json.put("category_result", 0);
					writer.write(json.toString());
					
				}
				
				System.out.println(category.toString());
				json.put("category_result", 1);
				writer.write(json.toString());
				
				break;
			
			case FrontController.GET_METHOD:
				
				/* TODO:
				 * 		Pode recuperar todas as categorias ou
				 * 		Pode recuperar apenas o ID de uma categoria baseada no seu nome
				 *  	
				 *  */
				
				if (request.getParameter("all_info") == null) {
					System.out.println("[Method " + FrontController.GET_METHOD + "] Parâmetro [all_info] inválido");
					json.put("category_result", 0);
					writer.write(json.toString());
				}
				
				
				// Lista apenas 1 categoria em específico				
				if (Integer.parseInt(request.getParameter("all_info")) == 0) {

					if (request.getParameter("category_name") == null) {
						System.out.println("[Method " + FrontController.GET_METHOD + "] Parâmetro [category_name] inválido");
						json.put("category_result", 0);
						writer.write(json.toString());
					}
					
					categoryName = request.getParameter("category_name");
					
					category = new Category();
					category.setCategoryName(categoryName);
					
					try {
						
						CadastroSingletonAction.getInstance().setDAOAction(new CategoryDAO<>(Category.class));
						CategoryDAO<Category> dao = (CategoryDAO<Category>) CadastroSingletonAction.getInstance().getDAOAction();
						
						category_id = dao.GetCategoryId(categoryName);
						
						json.put("category_id", category_id);
						writer.write(json.toString());
						
					} catch (Exception e) {
						e.printStackTrace();
						json.put("category_result", 0);
						writer.write(json.toString());
					}
					
				}
				
				// Lista todos os usuários
				if (Integer.parseInt(request.getParameter("all_info")) == 1) {
				
					try {
						
						CadastroSingletonAction.getInstance().setDAOAction(new CategoryDAO<>(Category.class));
						CategoryDAO<Category> dao = (CategoryDAO<Category>) CadastroSingletonAction.getInstance().getDAOAction();
						
						ArrayList<Category> objects = (ArrayList<Category>) dao.getObjectDatabase();
						
						JSONArray  arrayJSON = new JSONArray();
						JSONObject objJSON   = new JSONObject();
						
						for (Category category2 : objects) {
							
							JSONObject jsonAux = new JSONObject();
							
							jsonAux.put("category_id", category2.getCategoryId());
							jsonAux.put("category_name", category2.getCategoryName());
									
							arrayJSON.put(jsonAux);
							
							objJSON.put("categorys", arrayJSON);
						}
						
						System.out.println(objJSON.toString());
						writer.write(objJSON.toString());
						
					} catch (Exception e) {
						e.printStackTrace();
						json.put("category_result", 0);
						writer.write(json.toString());
					}
					
				}
				
				
				System.out.println("[Method " + FrontController.GET_METHOD  + "] Nenhuma ação foi selecionada");
				json.put("category_result", 0);
				writer.write(json.toString());			
				
				break;
				
			case FrontController.DELETE_METHOD:
				
				if (request.getParameter("category_id") == null) {
					System.out.println("[Method " + FrontController.DELETE_METHOD + "] Parâmetro [category_id] inválido");
					json.put("category_result", 0);
					writer.write(json.toString());
				}
				
				try {
					
					CadastroSingletonAction.getInstance().setDAOAction(new CategoryDAO<>(Category.class));
					CategoryDAO<Category> dao = (CategoryDAO<Category>) CadastroSingletonAction.getInstance().getDAOAction();
					
					long id = Integer.parseInt(request.getParameter("category_id"));
					
					category = dao.GetCategory(id);
					
					dao.deleteObjectDatabase(category, id);
					
					json.put("category_result", 1);
					writer.write(json.toString());
					
				} catch (Exception e) {
					e.printStackTrace();
					json.put("category_result", 0);
					writer.write(json.toString());
				}
				
				break;
	
				
			case FrontController.PUT_METHOD:
				
				if (request.getParameter("category_id") == null) {
					System.out.println("[Method " + FrontController.PUT_METHOD + "] Parâmetro [category_id] inválido");
					json.put("category_result", 0);
					writer.write(json.toString());
				}
				
				
				if (request.getParameter("category_name") == null) {
					System.out.println("[Method " + FrontController.PUT_METHOD + "] Parâmetro [category_name] inválido");
					json.put("category_result", 0);
					writer.write(json.toString());
				}
				
				try {
					
					CadastroSingletonAction.getInstance().setDAOAction(new CategoryDAO<>(Category.class));
					CategoryDAO<Category> dao = (CategoryDAO<Category>) CadastroSingletonAction.getInstance().getDAOAction();
					
					long id = Integer.parseInt(request.getParameter("category_id"));
					
					category = dao.GetCategory(id);
					category.setCategoryName(request.getParameter("category_name"));
					
					
					dao.putObjectDatabase(category);
					
					/*CadastroSingletonAction.getInstance().setDAOAction(new BookDAO<>(Book.class));
					BookDAO<Book> bdao = (BookDAO<Book>) CadastroSingletonAction.getInstance().getDAOAction();
					
					
					long bi = bdao.GetBookId("Paisagens do Brasillll");
					Book b = bdao.GetBook(bi);
					
					bdao.putObjectDatabase(b);*/
					
					json.put("category_result", 1);
					writer.write(json.toString());
					return;
					
				} catch (Exception e) {
					e.printStackTrace();
					json.put("category_result", 0);
					writer.write(json.toString());
				}
				
				break;
				
			default:
				break;
		}
		
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.execute(request, response, FrontController.POST_METHOD);
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.execute(request, response, FrontController.GET_METHOD);
	}

	@Override
	public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.execute(request, response, FrontController.PUT_METHOD);
	}

	@Override
	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.execute(request, response, FrontController.DELETE_METHOD);
	}

	@Override
	public void doOptions(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		this.execute(request, response, FrontController.OPTIONS_METHOD);
	}

}
