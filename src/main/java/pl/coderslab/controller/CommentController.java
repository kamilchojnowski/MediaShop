package pl.coderslab.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.coderslab.entity.Comment;
import pl.coderslab.entity.Product;
import pl.coderslab.entity.User;
import pl.coderslab.repository.CommentRepository;
import pl.coderslab.repository.ProductRepository;
import pl.coderslab.repository.UserRepository;

@Controller
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("")
	public String comment() {
		return "commentHome";
	}
	
	@GetMapping("/all")
	public String all(Model model) {
		List<Comment> list = commentRepository.findAll();
		model.addAttribute("comments", list);
		return "commentAll";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("comment", new Comment());
		return "commentAdd";
	}
	
	@PostMapping({"/add", "/edit"})
	public String save(@Valid Comment comment, BindingResult result) {
		if(result.hasErrors()){
			return "commentAdd";
		}
		commentRepository.save(comment);
		Product product = productRepository.findById(comment.getProduct().getId());
		product.setCommentEvaluation();
		productRepository.save(product);
		return "commentHome";
	}
	
	@GetMapping("edit")
	public String edit(@RequestParam Long id, Model model) {
		Comment comment = commentRepository.findById(id);
		model.addAttribute("comment", comment);
		return "commentEdit";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam Long id) {
		Comment comment = commentRepository.findById(id);
		User user = comment.getUser();
		user.removeComment(comment);
		Product product = comment.getProduct();
		product.removeComment(comment);
		commentRepository.delete(comment);
		product.setCommentEvaluation();
		productRepository.save(product);
		return "commentHome";
	}
	@ModelAttribute("availableProducts")
	public List<Product> availableProducts(){
		return productRepository.findAll();
	}
	
	@ModelAttribute("availableUsers")
	public List<User> availableUsers(){
		return userRepository.findAll();
	}

}
