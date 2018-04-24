/**
 * 
 */
package br.com.livrariaweb.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Eduardo
 *
 */
@Entity
@Table(name="category")
public class Category {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="category_id", nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long categoryId;
	
	@Column(name="category_name", unique=true, length=100, nullable=false)
	private String categoryName;
	
	public Category() {
		super();
	}

	public long getCategoryId() {
		return categoryId;
	}
	
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (categoryId ^ (categoryId >>> 32));
		result = prime * result + ((categoryName == null) ? 0 : categoryName.hashCode());
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
		Category other = (Category) obj;
		if (categoryId != other.categoryId)
			return false;
		if (categoryName == null) {
			if (other.categoryName != null)
				return false;
		} else if (!categoryName.equals(other.categoryName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + "]";
	}

}
