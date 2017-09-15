package pl.coderslab.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "comments")
public class Comment {
	
	@Id
	@GeneratedValue
	private Long id;
	@NotBlank(message = "Comment may not be empty")
	private String content;
	@NotNull(message = "Choose product")
	@ManyToOne(cascade = CascadeType.MERGE)
	private Product product;
	@NotNull(message = "Choose user")
	@ManyToOne(cascade = CascadeType.MERGE)
	private User user;
	@Range(min=0, max=5, message = "Evaluacion should be in a range of 0-5")
	private double evaluation = 0;
	public Comment() {}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public double getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(double evaluation) {
		this.evaluation = evaluation;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", content=" + content + ", product=" + product + ", user=" + user
				+ ", evaluation=" + evaluation + "]";
	}
	
	
	
}
