package pl.coderslab.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "products")
public class Product {
	
	@Id
	@GeneratedValue
	private Long id;
	@NotBlank(message = "Name may not be empty")
	private String name;
	@Length(max=1000, message = "Description may not be longer than 1000 characters")
	private String description;
	@Range(min = 0, max = 5, message = "Evaluation should be in a range of 0-5")
	private double evaluation;
	@Min(0)
	private double price;
	@Min(0)
	private int quantity = 0;
	@Range(min=0, max=1, message = "Discount should be in a range of 0-5")
	private double discount;
	@NotEmpty(message = "Choose both category and subcategory/subcategories")
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	private List<Subcategory> subcategories = new ArrayList<Subcategory>();
	@OneToMany(mappedBy = "product")
	private List<Comment> comments = new ArrayList<Comment>();
	public Product() {}
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(double evaluation) {
		setCommentEvaluation();
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public List<Subcategory> getSubcategories() {
		return subcategories;
	}
	public void setSubcategories(List<Subcategory> subcategories) {
		this.subcategories = subcategories;
	}
	
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public Product(String name, String description, double evaluation, double price, int quantity, double discount,
			List<Subcategory> subcategories) {
		super();
		this.name = name;
		this.description = description;
		this.evaluation = evaluation;
		this.price = price;
		this.quantity = quantity;
		this.discount = discount;
		this.subcategories = subcategories;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", evaluation=" + evaluation
				+ ", price=" + price + ", quantity=" + quantity + ", discount=" + discount + ", subcategories="
				+ subcategories + ", comments: " + comments.size() + "]";
	}
	
	public void setCommentEvaluation() {
		this.evaluation = 0.0;
		double ev = 0.0;
		if(this.comments.size()!=0) {
			int index = 0;
			for(Comment comment : this.comments) {
				ev += comment.getEvaluation();
				index++;
			}
			ev /= index;
			this.evaluation = Math.round(ev*100)/100.0d;
		}
	}
	public void addComment(Comment comment) {
		this.comments.add(comment);
	}
	public void removeComment(Comment comment) {
		this.comments.remove(comment);
	}
	
	
	

}
