package pl.coderslab.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "subcategories")
public class Subcategory {
	
	@Id
	@GeneratedValue
	private Long id;
	@NotBlank(message = "Name may not be empty")
	private String name;
	@NotNull(message = "Choose category")
	@ManyToOne(cascade = CascadeType.MERGE)
	private Category category;
	@ManyToMany(mappedBy = "subcategories", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	private List<Product> products = new ArrayList<Product>();
	public Subcategory() {}
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
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public Subcategory(String name, Category category) {
		super();
		this.name = name;
		this.category = category;
	}
	@Override
	public String toString() {
		return "Subcategory [id=" + id + ", name=" + name + ", category=" + category + "]";
	}
	
	
	
	

}
