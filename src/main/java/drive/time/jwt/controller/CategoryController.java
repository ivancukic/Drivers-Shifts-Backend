package drive.time.jwt.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import drive.time.jwt.entity.Category;
import drive.time.jwt.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	private CategoryService categoryService;

	@Autowired
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@GetMapping("/all")
	public @ResponseBody ResponseEntity<List<Category>> findAll() {
		
		List<Category> category = categoryService.findAll();
		
		return ResponseEntity.status(HttpStatus.OK).body(category);
	}
	
	@GetMapping("/get/{id}")
	public @ResponseBody ResponseEntity<Category> get(@PathVariable Integer id) {
		
		Optional<Category> category = categoryService.findByID(id);
		
		if(category.isPresent()) {
			
			return ResponseEntity.status(HttpStatus.OK).body(category.get());
		}
		else {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@PutMapping(value="/update/{id}")
	public @ResponseBody ResponseEntity<Category> update(@PathVariable Integer id, @RequestBody Category category) {
		
		Optional<Category> categoryOpt = categoryService.findByID(id);
		
		if(categoryOpt.isPresent()) {
			
			return ResponseEntity.ok(categoryService.save(category));
		}
		else {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@PostMapping(value = "/save")
	public @ResponseBody ResponseEntity<Category> save(@RequestBody Category category) {
		
		return ResponseEntity.ok(categoryService.save(category));
	}
	
	@DeleteMapping("/delete/{id}")
	public @ResponseBody ResponseEntity<Integer> delete(@PathVariable Integer id) {
		
		categoryService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
