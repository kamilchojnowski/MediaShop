package pl.coderslab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.entity.Subcategory;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Long>{
	List<Subcategory> findByCategoryId(Long id);
	Subcategory findById(Long id);
}
