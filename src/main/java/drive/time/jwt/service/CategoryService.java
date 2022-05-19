package drive.time.jwt.service;

import java.util.List;
import java.util.Optional;

import drive.time.jwt.entity.Category;

public interface CategoryService {
	
	public Category save(Category category);

	public List<Category> findAll();

	public void delete(Integer id);

	Optional<Category> findByID(Integer id);
	
}
