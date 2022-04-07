package drive.time.jwt.front;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import drive.time.jwt.entity.Category;
import drive.time.jwt.service.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryThymeleafController {
	
	private CategoryService categoryService;

	@Autowired
	public CategoryThymeleafController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		
		List<Category> categoryList = categoryService.findAll();
		
		model.addAttribute("categoryList", categoryList);
		
		return "category/category-list";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		
		Category category = new Category();
		
		model.addAttribute("category", category);
		
		return "category/category-form";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("categoryId") Integer theId, Model model) {
		
		Optional<Category> category = categoryService.findByID(theId);
		
		model.addAttribute("category", category);
		
		return "category/category-form";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("category") Category category) {
		
		categoryService.save(category);
		
		return "redirect:/category/list";
	}
	
	@PostMapping("/delete")
	public String delete(@RequestParam("categoryId") Integer theId) {
		
		categoryService.delete(theId);
		
		return "redirect:/category/list";
	}
	
	

}
