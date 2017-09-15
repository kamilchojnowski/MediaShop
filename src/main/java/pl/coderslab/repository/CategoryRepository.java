package pl.coderslab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.entity.Category;
import pl.coderslab.entity.Subcategory;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	Category findById(Long id);
	List<Subcategory> findSubcategoriesById(Long id);
}
