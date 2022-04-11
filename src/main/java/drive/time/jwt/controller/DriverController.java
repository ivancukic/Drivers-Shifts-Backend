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
import drive.time.jwt.entity.Driver;
import drive.time.jwt.entity.User;
import drive.time.jwt.service.CategoryService;
import drive.time.jwt.service.DriverService;
import drive.time.jwt.service.UserService;

@RestController
@RequestMapping("/api/driver")
public class DriverController {
	
	private DriverService driverService;
	private CategoryService categoryService; 
	private UserService userService; 

	
	@Autowired
	public DriverController(DriverService driverService, CategoryService categoryService, UserService userService) {
		this.driverService = driverService;
		this.categoryService = categoryService;
		this.userService = userService;
	}
	
	@GetMapping("/all")
	public @ResponseBody ResponseEntity<List<Driver>> findAll() {
		
		List<Driver> drivers = driverService.findAll();
		
		return ResponseEntity.status(HttpStatus.OK).body(drivers);
	}
	
	@GetMapping("/get/{id}")
	public @ResponseBody ResponseEntity<Driver> get(@PathVariable Integer id) {
		
		Optional<Driver> driver = driverService.findByID(id);
		
		if(driver.isPresent()) {
			
			return ResponseEntity.status(HttpStatus.OK).body(driver.get());
		}
		else {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@PutMapping(value="/update/{id}")
	public @ResponseBody ResponseEntity<Driver> update(@RequestBody Driver driver) {
		
		Optional<Driver> driverOpt = driverService.findByID(driver.getId());
		Optional<Category> categoryOpt = categoryService.findByID(driver.getCategory().getId()); 
		Optional<User> userOpt = userService.findByID(driver.getUser().getId());
		
		if(driverOpt.isPresent() && categoryOpt.isPresent()) {
			
			driver.setCategory(categoryOpt.get());
			driver.setUser(userOpt.get());
			
			return ResponseEntity.ok(driverService.save(driver));
		}
		else {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@PostMapping(value = "/save")
	public @ResponseBody ResponseEntity<Driver> save(@RequestBody Driver driver) {
		

		Optional<Category> category = categoryService.findByID(driver.getCategory().getId());
		Optional<User> user = userService.findByID(driver.getUser().getId());
		
		
		driver.setCategory(category.get());
		driver.setUser(user.get());
			
		return ResponseEntity.ok(driverService.save(driver));
	}
	
	@DeleteMapping("/delete/{id}")
	public @ResponseBody ResponseEntity<Integer> delete(@PathVariable Integer id) {
		
		driverService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
