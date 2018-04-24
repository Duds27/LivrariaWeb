/**
 * 
 */
package br.com.livrariaweb.bean;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


/**
 * @author Eduardo
 *
 */
@Entity
@Table(name="author")
public class Author {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="author_id", nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long authorId;
	
	@Column(name="author_name", length=100)
	private String authorName;
	
	//, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE
	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(
				name="book_author_nn",
				joinColumns		   = { @JoinColumn(name="author_id") },
				inverseJoinColumns = { @JoinColumn(name="book_id") }
	)
	private Collection<Book> books = new ArrayList<Book>();
		
	
	public Author() {
		super();
	}
	
	
	public Author(String authorName) {
		super();
		this.authorName = authorName;
	}



	public long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(long authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public Collection<Book> getBooks() {
		return books;
	}

	public void setBooks(Collection<Book> books) {
		this.books = books;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (authorId ^ (authorId >>> 32));
		result = prime * result + ((authorName == null) ? 0 : authorName.hashCode());
		result = prime * result + ((books == null) ? 0 : books.hashCode());
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
		Author other = (Author) obj;
		if (authorId != other.authorId)
			return false;
		if (authorName == null) {
			if (other.authorName != null)
				return false;
		} else if (!authorName.equals(other.authorName))
			return false;
		if (books == null) {
			if (other.books != null)
				return false;
		} else if (!books.equals(other.books))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Author [authorId=" + authorId + ", authorName=" + authorName + ", books=" + books + "]";
	}

}
