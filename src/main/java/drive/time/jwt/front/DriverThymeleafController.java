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
import drive.time.jwt.entity.Driver;
import drive.time.jwt.service.CategoryService;
import drive.time.jwt.service.DriverService;

@Controller
@RequestMapping("/driver")
public class DriverThymeleafController {

	private DriverService driverService;
	private CategoryService categoryService;

	
	@Autowired
	public DriverThymeleafController(DriverService driverService, CategoryService categoryService) {
		this.driverService = driverService;
		this.categoryService = categoryService;
	}

	@GetMapping("/list")
	public String list(Model model) {
		
		List<Driver> driverList = driverService.findAll();
		
		model.addAttribute("driverList", driverList);
		
		return "driver/driver-list";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		
		Driver driver = new Driver();
		
		List<Category> categoryList = categoryService.findAll();
		
		model.addAttribute("driver", driver);
		model.addAttribute("categoryList", categoryList);
		
		return "driver/driver-form";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("driverId") Integer theId, Model model) {
		
		Optional<Driver> driver = driverService.findByID(theId);
		
		List<Category> categoryList = categoryService.findAll();
		
		model.addAttribute("driver", driver);
		model.addAttribute("categoryList", categoryList);
		
		return "driver/driver-form";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("driver") Driver driver) {
		
		driverService.save(driver);
		
		return "redirect:/driver/list";
	}
	
	@PostMapping("/delete")
	public String delete(@RequestParam("driverId") Integer theId) {
		
		driverService.delete(theId);
		
		return "redirect:/driver/list";
	}
	
	
}
