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
import drive.time.jwt.entity.Line;
import drive.time.jwt.entity.User;
import drive.time.jwt.service.CategoryService;
import drive.time.jwt.service.DriverService;
import drive.time.jwt.service.LineService;
import drive.time.jwt.service.UserService;
import drive.time.jwt.service.DriversShiftsService;

@RestController
@RequestMapping("/api/driver")
public class DriverController {
	
	private DriverService driverService;
	private CategoryService categoryService; 
	private LineService lineService; 
	private UserService userService; 

//	@Autowired
//	public DriverController(DriverService driverService) {
//		this.driverService = driverService;
//	}
	
	@Autowired
	public DriverController(DriverService driverService, CategoryService categoryService, LineService lineService, UserService userService) {
		this.driverService = driverService;
		this.categoryService = categoryService;
		this.lineService = lineService;
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
		
		//List<Category> categoryList = categoryService.findAll();
		//driver.setCategory(categoryList.get(0));
		//Integer categoryId = Integer.parseInt(driver.getCategory().getName());
		
		Optional<Driver> driverOpt = driverService.findByID(driver.getId());
		Optional<Category> categoryOpt = categoryService.findByID(driver.getCategory().getId()); // MORA GET NAME
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
		
		System.out.println("Usao u save");
		System.out.println("Driver id " + driver.getId());
		System.out.println("Driver name " + driver.getName());
		System.out.println("Driver dob " + driver.getDob());
		System.out.println("Driver Category NAME " + driver.getCategory().getName());
		System.out.println("Driver Category tip je " + driver.getCategory().getName().getClass());
		System.out.println("Driver Categori ID je " + driver.getCategory().getId());
		System.out.println("Admin Id je " + driver.getUser().getId());
		System.out.println("Admin Name je " + driver.getUser().getName());
		System.out.println("Admin Username je " + driver.getUser().getUsername());
	
		
		Integer categoryId = Integer.parseInt(driver.getCategory().getName());
		
		
		Optional<Category> category = categoryService.findByID(categoryId);
		//Optional<Category> category = categoryService.findByID(driver.getCategory().getId());
		Optional<User> user = userService.findByID(driver.getUser().getId());
		//Optional<Line> line = lineService.findByID(driver.getLines().get(0));
		
		driver.setCategory(category.get());
		driver.setUser(user.get());
		//driver.setLines(line.get());
			
		return ResponseEntity.ok(driverService.save(driver));
	}
	
//	@PostMapping(value = "/save")
//	public @ResponseBody ResponseEntity<Driver> save(@PathVariable String categoryId, @RequestBody Driver driver) {
//		
//		System.out.println("Usao u save");
//		System.out.println("Driver id " + driver.getId());
//		System.out.println("Driver name " + driver.getName());
//		System.out.println("Driver dob " + driver.getDob());
//		System.out.println("Driver Category NAME " + driver.getCategory().getName());
//		System.out.println("Driver Category tip je " + driver.getCategory().getName().getClass());
//		System.out.println("Driver Category ID je " + driver.getCategory().getId());
//		System.out.println("Driver Category ID STRING je " + categoryId);
//		
//		Integer id = Integer.parseInt(categoryId);
//		
//		Optional<Category> category = categoryService.findByID(id);
//		
//		driver.setCategory(category.get());
//			
//		return ResponseEntity.ok(driverService.save(driver));
//	}
	
	@DeleteMapping("/delete/{id}")
	public @ResponseBody ResponseEntity<String> delete(@PathVariable Integer id) {
		
		driverService.delete(id);
		
		return ResponseEntity.status(HttpStatus.OK).body("Driver deleted!");
	}

}
