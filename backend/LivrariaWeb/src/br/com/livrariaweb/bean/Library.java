/**
 * 
 */
package br.com.livrariaweb.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * @author Eduardo
 *
 */
@Entity
@Table(name="library")
public class Library {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="library_id", nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long libraryId;
	
	@OneToOne
	@JoinColumn(name="book_id", updatable=true)
	private Book book;
	
	@Column(name="library_book_count", nullable=true)
	private int libraryBookCount;
	
	
	public Library() {
		super();
	}
	
	public long getLibraryId() {
		return libraryId;
	}
	
	public void setLibraryId(long libraryId) {
		this.libraryId = libraryId;
	}
	
	public Book getBook() {
		return book;
	}
	
	public void setBook(Book book) {
		this.book = book;
	}
	
	public int getLibraryBookCount() {
		return libraryBookCount;
	}

	public void setLibraryBookCount(int libraryBookCount) {
		this.libraryBookCount = libraryBookCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		result = prime * result + libraryBookCount;
		result = prime * result + (int) (libraryId ^ (libraryId >>> 32));
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
		Library other = (Library) obj;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		if (libraryBookCount != other.libraryBookCount)
			return false;
		if (libraryId != other.libraryId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Library [libraryId=" + libraryId + ", book=" + book + ", libraryBookCount=" + libraryBookCount + "]";
	}
	
}
