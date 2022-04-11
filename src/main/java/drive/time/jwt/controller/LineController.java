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

import drive.time.jwt.entity.Line;
import drive.time.jwt.entity.User;
import drive.time.jwt.service.LineService;
import drive.time.jwt.service.UserService;

@RestController
@RequestMapping("/api/line")
public class LineController {
	
	private LineService lineService;
	private UserService userService; 

	@Autowired
	public LineController(LineService lineService, UserService userService) {
		this.lineService = lineService;
		this.userService = userService;
	}
	
	@GetMapping("/all")
	public @ResponseBody ResponseEntity<List<Line>> findAll() {
		
		List<Line> lines = lineService.findAll();
		
		return ResponseEntity.status(HttpStatus.OK).body(lines);
	}
	
	@GetMapping("/get/{id}")
	public @ResponseBody ResponseEntity<Line> get(@PathVariable Integer id) {
		
		Optional<Line> line = lineService.findByID(id);
		
		if(line.isPresent()) {
			
			return ResponseEntity.status(HttpStatus.OK).body(line.get());
		}
		else {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@PutMapping(value="/update/{id}")
	public @ResponseBody ResponseEntity<Line> update(@PathVariable Integer id,@RequestBody Line line) {
		
		Optional<Line> lineOpt = lineService.findByID(id);
		
		if(lineOpt.isPresent()) {
			
			Optional<User> user = userService.findByID(line.getUser().getId());
			
			lineService.save(line);
			lineService.calcTotalTime(line.getEnd_time(), line.getStart_time());
			lineService.negativeTime();
			lineService.numberOfDrivers();

			line.setUser(user.get());
			
			return ResponseEntity.ok(lineService.save(line));
		}
		else {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}
	
	@PostMapping(value = "/save")
	public @ResponseBody ResponseEntity<Line> save(@RequestBody Line line) {
		
		lineService.save(line);
		lineService.calcTotalTime(line.getEnd_time(), line.getStart_time());
		lineService.negativeTime();
		lineService.numberOfDrivers();
		
		Optional<User> user = userService.findByID(line.getUser().getId());
		
		line.setUser(user.get());
		
		return ResponseEntity.ok(lineService.save(line));
	}
	
	@DeleteMapping("/delete/{id}")
	public @ResponseBody ResponseEntity<Integer> delete(@PathVariable Integer id) {
		
		lineService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
