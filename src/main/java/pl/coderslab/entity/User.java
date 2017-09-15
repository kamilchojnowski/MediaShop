package pl.coderslab.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.mindrot.jbcrypt.BCrypt;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue
	private Long id;
	@NotBlank(message = "Username may not be empty")
	private String username;
	@NotBlank(message = "Password may not be empty")
	@Length(max=2000, message = "Password may not be longer than 2000 characters")
	private String password;
	@NotBlank(message = "Email may not be empty")
	@Column(unique=true)
	@Email
	private String email;
	private boolean enabled;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Comment> comments = new ArrayList<Comment>();
	public User() {}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
		this.password = BCrypt.hashpw(password, BCrypt.gensalt());
	}
	public boolean isPasswordCorrect(String password) {
		return BCrypt.checkpw(password, this.password);
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public void enable() {
		this.enabled = true;
	}
	public void disable() {
		this.enabled = false;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public void addComment(Comment comment) {
		this.comments.add(comment);
	}
	public void removeComment(Comment comment) {
		this.comments.remove(comment);
	}
	
	

}
