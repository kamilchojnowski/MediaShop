package pl.coderslab.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "categories")
public class Category {

	@Id
	@GeneratedValue
	private Long id;
	@NotBlank(message = "Name may not be empty")
	private String name;
	@OneToMany(mappedBy = "category", cascade = CascadeType.MERGE)
	List<Subcategory> subcategories = new ArrayList<Subcategory>();
	public Category() {}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Subcategory> getSubcategories() {
		return subcategories;
	}
	public void setSubcategories(List<Subcategory> subcategories) {
		this.subcategories = subcategories;
	}
	public Category(String name) {
		super();
		this.name = name;
	}
	@Override
	public String toString() {
		return "Category [name=" + name + "]";
	}
	

	
	
	
}
