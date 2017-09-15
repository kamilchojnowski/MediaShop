package pl.coderslab.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.coderslab.entity.Category;
import pl.coderslab.entity.Subcategory;
import pl.coderslab.repository.CategoryRepository;
import pl.coderslab.repository.SubcategoryRepository;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private SubcategoryRepository subcategoryRepository;
	
	@GetMapping("")
	public String category() {
		return "categoryHome";
	}
	
	@GetMapping("/all")
	public String all(Model model) {
		List<Category> list = categoryRepository.findAll();
		model.addAttribute("categories", list);
		return "categoryAll";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("category", new Category());
		return "categoryAdd";
	}
	
	@PostMapping({"/add", "/edit"})
	public String save(@Valid Category category, BindingResult result) {
		if(result.hasErrors()) {
			return "categoryAdd";
		}
		categoryRepository.save(category);
		return "categoryHome";
	}
	
	@GetMapping("edit")
	public String edit(@RequestParam Long id, Model model) {
		Category category = categoryRepository.findById(id);
		model.addAttribute("category", category);
		return "categoryEdit";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam Long id) {
		List<Subcategory> subcategories = subcategoryRepository.findByCategoryId(id);
		for(Subcategory subcategory : subcategories) {
			subcategory.setCategory(null);
		}
		categoryRepository.delete(categoryRepository.findById(id));
		return "categoryHome";
	}
	
	@GetMapping("/subcategories")
	@Transactional
	public String subcategories(@RequestParam Long id, Model model) {
		Category category = categoryRepository.findById(id);
		Hibernate.initialize(category.getSubcategories());
		List<Subcategory> subcategories = category.getSubcategories();
		model.addAttribute("subcategories", subcategories);
		return "subcategoryAll";
	}
	
	
	

	
}
