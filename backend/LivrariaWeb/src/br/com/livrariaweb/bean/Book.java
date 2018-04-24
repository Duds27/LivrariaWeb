/**
 * 
 */
package br.com.livrariaweb.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * @author Eduardo
 *
 */
@Entity
@Table(name="book")
public class Book {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="book_id", nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long bookId;
	
	@OneToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	@Column(name="book_title", length=100, nullable=false)
	private String bookTitle;
	
	@Column(name="book_publisher", length=100, nullable=false)
	private String bookPublisher;
	
	@Column(name="book_year")
	private int bookYear;
	
	@Column(name="book_price", length=100, nullable=false)
	private double bookPrice;
	
	
	@ManyToMany(mappedBy = "books", cascade = { CascadeType.ALL })
	private List<Author> authors = new ArrayList<Author>();
	
		
	public Book() {
		super();
	}
	

	public long getBookId() {
		return bookId;
	}
	
	
	public void setBookId(long bookId) {
		this.bookId = bookId;
	}
	
	
	public Category getCategory() {
		return category;
	}
	
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	
	public String getBookTitle() {
		return bookTitle;
	}
	
	
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	
	
	public String getBookPublisher() {
		return bookPublisher;
	}
	
	
	public void setBookPublisher(String bookPublisher) {
		this.bookPublisher = bookPublisher;
	}
	
	
	public int getBookYear() {
		return bookYear;
	}
	
	
	public void setBookYear(int bookYear) {
		this.bookYear = bookYear;
	}
	
	
	public double getBookPrice() {
		return bookPrice;
	}

	
	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}


	public List<Author> getAuthors() {
		return authors;
	}


	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authors == null) ? 0 : authors.hashCode());
		result = prime * result + (int) (bookId ^ (bookId >>> 32));
		long temp;
		temp = Double.doubleToLongBits(bookPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((bookPublisher == null) ? 0 : bookPublisher.hashCode());
		result = prime * result + ((bookTitle == null) ? 0 : bookTitle.hashCode());
		result = prime * result + bookYear;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (authors == null) {
			if (other.authors != null)
				return false;
		} else if (!authors.equals(other.authors))
			return false;
		if (bookId != other.bookId)
			return false;
		if (Double.doubleToLongBits(bookPrice) != Double.doubleToLongBits(other.bookPrice))
			return false;
		if (bookPublisher == null) {
			if (other.bookPublisher != null)
				return false;
		} else if (!bookPublisher.equals(other.bookPublisher))
			return false;
		if (bookTitle == null) {
			if (other.bookTitle != null)
				return false;
		} else if (!bookTitle.equals(other.bookTitle))
			return false;
		if (bookYear != other.bookYear)
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", category=" + category + ", bookTitle=" + bookTitle + ", bookPublisher="
				+ bookPublisher + ", bookYear=" + bookYear + ", bookPrice=" + bookPrice + ", authors=" + authors + "]";
	}
	
}
