package drive.time.jwt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drive.time.jwt.entity.Category;
import drive.time.jwt.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	private CategoryRepository categoryRepository;

	@Autowired
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public Category save(Category category) {

		return categoryRepository.save(category);
	}

	@Override
	public List<Category> findAll() {

		return categoryRepository.findAll();
	}

	@Override
	public void delete(Integer id) {

		categoryRepository.deleteById(id);
	}

	@Override
	public Optional<Category> findByID(Integer id) {

		return categoryRepository.findById(id);
	}

	@Override
	public Category findByIDCategory(Integer id) {
		
		Optional<Category> result =categoryRepository.findById(id);
		
		Category category = null;
		
		if(result.isPresent()) {
			
			category = result.get();
		}
		else {
			
			throw new RuntimeException("Did not find!");
		}

		return category;
	}
	
	

}
