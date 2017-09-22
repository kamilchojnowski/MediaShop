package pl.coderslab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	Product findById(Long id);
	List<Product> findBySubcategoriesId(Long id);
	List<Product> findBySubcategoriesCategoryId(Long id);
}
