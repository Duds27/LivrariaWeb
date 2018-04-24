/**
 * 
 */
package br.com.livrariaweb.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.livrariaweb.bean.Author;
import br.com.livrariaweb.bean.Book;
import br.com.livrariaweb.bean.Category;
import br.com.livrariaweb.bean.Library;
import br.com.livrariaweb.controller.Action;
import br.com.livrariaweb.controller.CrossDomainInterface;
import br.com.livrariaweb.controller.CrossDomainLivraria;
import br.com.livrariaweb.controller.FrontController;
import br.com.livrariaweb.dao.AuthorDAO;
import br.com.livrariaweb.dao.BookDAO;
import br.com.livrariaweb.dao.CadastroSingletonAction;
import br.com.livrariaweb.dao.CategoryDAO;
import br.com.livrariaweb.dao.LibraryDAO;

/**
 * @author ecorrea
 *
 */
public class LibraryAction implements Action {

	protected CrossDomainInterface crossDomain;
	
	public LibraryAction() {
		super();
		setCrossDomain(new CrossDomainLivraria());
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, String methodType) throws ServletException, IOException {

		JSONObject json    = new JSONObject();
		PrintWriter writer = response.getWriter();
		
		
		try {

			this.crossDomain.setCrossDomain(request, response);
			
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
						json.put("library_result", 0);
						writer.write(json.toString());
						
					}
					
					JSONObject jsonObject = null;
					try {
	
						jsonObject = new JSONObject(jb.toString());
					
					} catch (JSONException e) {
	
						e.printStackTrace();
						json.put("library_result", 0);
						writer.write(json.toString());
	
					}
					
					
					// Category
					Category category = new Category();
					category.setCategoryName(jsonObject.getString("category_name"));
					
					try {
						
						CadastroSingletonAction.getInstance().setDAOAction(new CategoryDAO<>(Category.class));
						CategoryDAO<Category> dao = new CategoryDAO<>(Category.class);
						
						dao.postObjectDatabase(category);
						
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Parâmetro inválido");
						json.put("category_result", 0);
						writer.write(json.toString());
					}
					
					// Author
					Author author = new Author();
					author.setAuthorName(jsonObject.getString("author_name"));
					
					// Book
					Book book = new Book();
					book.setBookTitle(jsonObject.getString("book_title"));
					book.setBookPublisher(jsonObject.getString("book_publisher"));
					book.setBookYear(jsonObject.getInt("book_year"));
					book.setBookPrice(jsonObject.getDouble("book_price"));
					book.setCategory(category);
					
					author.getBooks().add(book);
					book.getAuthors().add(author);
					
					try {
						
						CadastroSingletonAction.getInstance().setDAOAction(new AuthorDAO<>(Author.class));
						AuthorDAO<Author> dao = new AuthorDAO<>(Author.class);
						
						dao.postObjectDatabase(author);
						
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Parâmetro inválido");
						json.put("author_result", 0);
						writer.write(json.toString());
					}
					
					try {
						
						CadastroSingletonAction.getInstance().setDAOAction(new BookDAO<>(Book.class));
						BookDAO<Book> dao = new BookDAO<>(Book.class);
						
						dao.postObjectDatabase(book);
						
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Parâmetro inválido");
						json.put("book_result", 0);
						writer.write(json.toString());
					}
					
					
					
					// Library
					Library library = new Library();
					library.setBook(book);
					library.setLibraryBookCount(1);
					
					
					System.out.println("[OBJECT] = " + jsonObject.toString());
								
					
					try {
	
						CadastroSingletonAction.getInstance().setDAOAction(new LibraryDAO<>(Library.class));
						LibraryDAO<Library> dao = (LibraryDAO<Library>) CadastroSingletonAction.getInstance().getDAOAction();
						
						dao.postObjectDatabase(library);
						
					} catch (RuntimeException e) {
	
						e.printStackTrace();
						System.out.println(category.toString());
						json.put("library_result", 0);
						writer.write(json.toString());
						
					}
					
					System.out.println(category.toString());
					json.put("library_result", 1);
					writer.write(json.toString());
					
					
				break;
				
				
				case FrontController.GET_METHOD:
					
					try {
						
						CadastroSingletonAction.getInstance().setDAOAction(new LibraryDAO<>(Library.class));
						LibraryDAO<Library> dao = (LibraryDAO<Library>) CadastroSingletonAction.getInstance().getDAOAction();
						
						ArrayList<Library> objects = (ArrayList<Library>) dao.getObjectDatabase();
						
						JSONArray  arrayJSON = new JSONArray();
						JSONObject objJSON   = new JSONObject();
						
						for (Library lib : objects) {
							
							JSONObject jsonAux = new JSONObject();
							
							jsonAux.put("library_id", lib.getLibraryId());
							jsonAux.put("category_name", lib.getBook().getCategory().getCategoryName());
							jsonAux.put("book_title", lib.getBook().getBookTitle());
							jsonAux.put("book_publisher", lib.getBook().getBookPublisher());
							jsonAux.put("book_year", lib.getBook().getBookYear());
							
							AuthorDAO<Author> authorDAO = new AuthorDAO<>(Author.class); 
							jsonAux.put("author_name", authorDAO.GetAuthor(lib.getBook().getBookId()).getAuthorName());
							jsonAux.put("book_price", lib.getBook().getBookPrice());
									
							arrayJSON.put(jsonAux);
							
							objJSON.put("librarys", arrayJSON);
						}
						
						System.out.println(objJSON.toString());
						writer.write(objJSON.toString());
						return;
						
					} catch (Exception e) {
						e.printStackTrace();
						json.put("library_result", 0);
						writer.write(json.toString());
					}
					
					System.out.println("[Method " + FrontController.GET_METHOD  + "] Nenhuma ação foi selecionada");
					json.put("library_result", 0);
					writer.write(json.toString());	
					
					
					break;
				
				case FrontController.PUT_METHOD:
					
					try {
						
						StringBuffer sjb = new StringBuffer();
						String sLine     = null;
						
						try {
						
							BufferedReader reader = request.getReader();
							while ((sLine = reader.readLine()) != null)
								sjb.append(sLine);
							
						} catch (RuntimeException e) {							
							e.printStackTrace();
							System.out.println("Parâmetro inválido");
							json.put("library_result", 0);
							writer.write(json.toString());
							return;							
						}
						
						JSONObject jsonObj = null;
						try {
		
							jsonObj = new JSONObject(sjb.toString());
						
						} catch (JSONException e) {		
							e.printStackTrace();
							json.put("library_result", 0);
							writer.write(json.toString());
							return;		
						}
						
						String categoryName    = jsonObj.getString("category_name");
						String bookTitle 	   = jsonObj.getString("book_title");
						String bookPublisher   = jsonObj.getString("book_publisher");
						int bookYear 		   = jsonObj.getInt("book_year");
						String authorName 	   = jsonObj.getString("author_name");
						double bookPrice 	   = jsonObj.getDouble("book_price"); 
						long libraryId 		   = jsonObj.getLong("library_id");
						
						
						if (categoryName == null) {
							System.out.println("[Method " + FrontController.PUT_METHOD + "] Parâmetro [category_name] inválido");
							json.put("category_name_result", 0);
							writer.write(json.toString());
							return;
						}
						
						if (bookTitle == null) {
							System.out.println("[Method " + FrontController.PUT_METHOD + "] Parâmetro [book_title] inválido");
							json.put("book_title_result", 0);
							writer.write(json.toString());
							return;
						}
						
						if (bookPublisher == null) {
							System.out.println("[Method " + FrontController.PUT_METHOD + "] Parâmetro [book_publisher] inválido");
							json.put("book_publisher_result", 0);
							writer.write(json.toString());
							return;
						}
												
						if (authorName == null) {
							System.out.println("[Method " + FrontController.PUT_METHOD + "] Parâmetro [author_name] inválido");
							json.put("author_name_result", 0);
							writer.write(json.toString());
							return;
						}
						
						// Recuperando obj libray do bd
						Library libraryDB = null;
						try {
							
							CadastroSingletonAction.getInstance().setDAOAction(new LibraryDAO<>(Library.class));
							LibraryDAO<Library> libraryDAO = new LibraryDAO<>(Library.class);
							
							libraryDB = libraryDAO.GetLibrary(libraryId);
							
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("Parâmetro inválido");
							json.put("category_result", 0);
							writer.write(json.toString());
							return;
						}
						
						
						if (libraryDB == null) {
							System.out.println("[Method " + FrontController.PUT_METHOD + "] Parâmetro [library_id] inválido");
							json.put("library_obj_result", 0);
							writer.write(json.toString());
							return;
						}
						
						
						Category categoria;
						try {
							
							CadastroSingletonAction.getInstance().setDAOAction(new CategoryDAO<>(Category.class));
							CategoryDAO<Category> dao = new CategoryDAO<>(Category.class);
							
							// Category
							categoria = dao.GetCategory(libraryDB.getBook().getCategory().getCategoryId());
							categoria.setCategoryName(categoryName);
							
							dao.putObjectDatabase(categoria);
							
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("Parâmetro inválido");
							json.put("category_result", 0);
							writer.write(json.toString());
							return;
						}						
												
						Book livro;
						try {
							
							CadastroSingletonAction.getInstance().setDAOAction(new BookDAO<>(Book.class));
							BookDAO<Book> dao = new BookDAO<>(Book.class);
							
							// Category
							livro = dao.GetBook(libraryDB.getBook().getBookId());
							livro.setBookTitle(bookTitle);
							livro.setBookPublisher(bookPublisher);
							livro.setBookYear(bookYear);
							livro.setBookPrice(bookPrice);
							livro.setCategory(categoria);
							
							dao.putObjectDatabase(livro);
							
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("Parâmetro inválido");
							json.put("book_result", 0);
							writer.write(json.toString());
							return;
						}
						
						json.put("library_result", 1);
						writer.write(json.toString());
						
					} catch (Exception e) {
						e.printStackTrace();
						json.put("category_result", 0);
						writer.write(json.toString());
						return;
					}
					
					
					break;
					
				case FrontController.DELETE_METHOD:
					
					if (request.getParameter("library_id") == null) {
						System.out.println("[Method " + FrontController.DELETE_METHOD + "] Parâmetro [library_id] inválido");
						json.put("library_result", 0);
						writer.write(json.toString());
					}
					
					Library livraria;
					try {
						
						CadastroSingletonAction.getInstance().setDAOAction(new LibraryDAO<>(Library.class));
						LibraryDAO<Library> dao = (LibraryDAO<Library>) CadastroSingletonAction.getInstance().getDAOAction();
						
						long id = Integer.parseInt(request.getParameter("library_id"));
						
						livraria = dao.GetLibrary(id);
						
						dao.deleteObjectDatabase(livraria, id);
						
						json.put("library_result", 1);
						writer.write(json.toString());
						
					} catch (Exception e) {
						e.printStackTrace();
						json.put("library_result", 0);
						writer.write(json.toString());
					}
					
					break;
					
				default:
					break;
			}
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			json.put("library_result", 0);
			writer.write(json.toString());
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
	public void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.execute(request, response, FrontController.OPTIONS_METHOD);
	}
	
	public CrossDomainInterface getCrossDomain() {
		return crossDomain;
	}



	public void setCrossDomain(CrossDomainInterface crossDomain) {
		this.crossDomain = crossDomain;
	}

}
