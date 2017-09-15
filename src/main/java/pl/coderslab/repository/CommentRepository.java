package pl.coderslab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
	Comment findById(Long id);
	List<Comment> findByProductId(Long id);
}
