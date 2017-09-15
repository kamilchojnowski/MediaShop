package pl.coderslab.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.coderslab.entity.Comment;
import pl.coderslab.entity.Order;
import pl.coderslab.entity.OrderedItem;
import pl.coderslab.entity.Product;
import pl.coderslab.entity.User;
import pl.coderslab.model.Cart;
import pl.coderslab.model.ProductModel;
import pl.coderslab.model.UserModel;
import pl.coderslab.repository.CommentRepository;
import pl.coderslab.repository.OrderRepository;
import pl.coderslab.repository.OrderedItemRepository;
import pl.coderslab.repository.ProductRepository;
import pl.coderslab.repository.UserRepository;

@Controller
@RequestMapping("/shopuser")
public class ShopUserController extends SessionedController {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderedItemRepository orderedItemRepository;

	@GetMapping("/all")
	public String all(Model model) {
		List<Comment> comments = commentRepository.findAll();
		model.addAttribute("comments", comments);
		return "shop/allComments";
	}

	@GetMapping({ "/home", "" })
	public String home(Model model) {
		UserModel userModel = new UserModel();
		if (session().getAttribute("userModel") != null) {
			userModel = (UserModel) session().getAttribute("userModel");
		} else {
			userModel.setCart(new Cart());
		}
		model.addAttribute("cart", "Cart: " + userModel.getCart().getProducts().size() + " items");
		model.addAttribute("productModel", new ProductModel());
		List<Product> products = productRepository.findAll();
		List<ProductModel> list = new ArrayList<ProductModel>();
		for (Product product : products) {
			ProductModel elem = new ProductModel();
			elem.setId(product.getId());
			elem.setName(product.getName());
			elem.setDescription(product.getDescription().split(";"));
			product.setCommentEvaluation();
			elem.setEvaluation(product.getEvaluation());
			elem.setPrice(product.getPrice() * product.getDiscount());
			elem.setAmount(product.getQuantity());
			elem.setQuantity(0);
			list.add(elem);
		}
		model.addAttribute("products", list);
		return "shop/home";
	}

	@GetMapping("/addcomment")
	public String addComment(Model model, @RequestParam(name = "productId") Long id) {
		if (session().getAttribute("user") == null) {
			model.addAttribute("msg", "You must be logged in to comment");
			return "home";
		}
		model.addAttribute("comment", new Comment());
		User user = (User) session().getAttribute("user");
		model.addAttribute("productId", id);
		model.addAttribute("userId", user.getId());
		return "shop/addComment";
	}

	@PostMapping("/addcomment")
	public String addComment(@ModelAttribute Comment comment) {
		commentRepository.save(comment);
		return "redirect:/shopuser/home";
	}

	@GetMapping("/find")
	@Transactional
	public String find(@RequestParam Long id, Model model) {
		Product product = productRepository.findById(id);
		String[] description = product.getDescription().split(";");
		Hibernate.initialize(product.getComments());
		List<Comment> comments = product.getComments();
		model.addAttribute("productId", id);
		model.addAttribute("comments", comments);
		model.addAttribute("description", description);
		model.addAttribute("product", product);
		model.addAttribute("productModel", new ProductModel());
		return "productFind";
	}

	@PostMapping("/addtocart")
	public String addToCart(@ModelAttribute ProductModel productModel, Model model) {
		UserModel userModel = new UserModel();
		if (session().getAttribute("userModel") != null) {
			userModel = (UserModel) session().getAttribute("userModel");
		} else {
			userModel.setCart(new Cart());
		}
		List<ProductModel> list = userModel.getCart().getProducts();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId().equals(productModel.getId())) {
				list.get(i).increase(productModel.getQuantity());
				session().setAttribute("userModel", userModel);
				return "redirect:/shopuser/home";
			}
		}
		userModel.addToCart(productModel);
		session().setAttribute("userModel", userModel);
		return "redirect:/shopuser/home";
	}

	@GetMapping("/cart")
	public String cart(Model model) {
		UserModel userModel = new UserModel();
		if (session().getAttribute("userModel") != null) {
			userModel = (UserModel) session().getAttribute("userModel");
		} else {
			userModel.setCart(new Cart());
		}
		model.addAttribute("products", userModel.getCart().getProducts());
		return "shop/cart";
	}

	@GetMapping("/buy")
	public String buy(@RequestParam Long[] id, @RequestParam int[] quantity, Model model) {
		if (session().getAttribute("user") != null) {
			Order order = new Order();
			order.setAmount(0);
			for (int i = 0; i < id.length; i++) {
				Product product = productRepository.findById(id[i]);
				product.setQuantity(product.getQuantity() - quantity[i]);
				productRepository.save(product);
				OrderedItem orderedItem = new OrderedItem();
				orderedItem.setName(product.getName());
				orderedItem.setPrice(product.getPrice());
				orderedItem.setDiscount(product.getDiscount());
				orderedItem.setQuantity(quantity[i]);
				orderedItemRepository.save(orderedItem);
				order.addToOrder(orderedItem);
				order.setUser((User) session().getAttribute("user"));
				order.increaseAmount(product.getPrice() * quantity[i] * product.getDiscount());
				orderRepository.save(order);
			}
			UserModel userModel = (UserModel) session().getAttribute("userModel");
			userModel.setCart(new Cart());
			session().setAttribute("userModel", userModel);
			return "redirect:/shopuser/home";
		} else {
			UserModel userModel = (UserModel) session().getAttribute("userModel");
			session().setAttribute("userModelToRegister", userModel);
			model.addAttribute("msg", "You must be logged in to buy");
			return "home";
		}
	}

	@GetMapping("/remove")
	public String remove(@RequestParam Long id) {
		UserModel userModel = new UserModel();
		if (session().getAttribute("userModel") != null) {
			userModel = (UserModel) session().getAttribute("userModel");
		} else {
			userModel.setCart(new Cart());
		}
		List<ProductModel> list = userModel.getCart().getProducts();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId().equals(id)) {
				list.remove(list.get(i));
			}
		}
		return "redirect:/shopuser/cart";
	}

	@GetMapping("/comment")
	@Transactional
	public String comment(@RequestParam Long id, Model model) {
		Product product = productRepository.findById(id);
		Hibernate.initialize(product.getComments());
		model.addAttribute("comments", product.getComments());
		model.addAttribute("productId", id);
		return "shop/commentShow";
	}

	@ModelAttribute("availableProducts")
	public List<Product> availableProducts() {
		return productRepository.findAll();
	}

	@ModelAttribute("availableUsers")
	public List<User> availableUsers() {
		return userRepository.findAll();
	}

	@GetMapping("login")
	@ResponseBody
	public String login() {
		String str = "<span><a href=\"/login\">Log in</a></span><span><a href=\"/register\">Register</a></span>";
		if (session().getAttribute("user") != null) {
			User user = (User) session().getAttribute("user");
			str = "<span>Logged in as " + user.getUsername() + "</span><span><a href=\"/logout\">Log out</a></span>";
		}
		return str;
	}

}
