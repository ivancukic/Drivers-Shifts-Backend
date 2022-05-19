package drive.time.jwt.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import drive.time.jwt.entity.Driver;
import drive.time.jwt.entity.Line;
import drive.time.jwt.entity.Shift;
import drive.time.jwt.exception.DriverExceptions;
import drive.time.jwt.entity.DriversShifts;
import drive.time.jwt.service.DriverService;
import drive.time.jwt.service.LineService;
import drive.time.jwt.service.DriversShiftsService;

@RestController
@RequestMapping("/api/shift")
public class ShiftController {
	
	private LineService lineService;
	private DriverService driverService;
	private DriversShiftsService driversShiftsService; 
	
	@Autowired
	public ShiftController(LineService lineService, DriverService driverService, DriversShiftsService driversShiftsService) {
		this.lineService = lineService;
		this.driverService = driverService;
		this.driversShiftsService = driversShiftsService;
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
	
	@PutMapping(value="/updateOne/{id}")
	public @ResponseBody ResponseEntity<DriversShifts> updateOne(@PathVariable Integer id, 
																   	  		   Integer driverOneId, @RequestBody Line line) throws DriverExceptions {
		
		if(driverOneId == null) {
			
			throw new DriverExceptions("Driver can't be blank!");
		}
		
		Optional<Line> lineOpt = lineService.findByID(id);
		// PREVIOUS DRIVERS
		List<Driver> oldDrivers = driversShiftsService.oldDrivers(id);
		 
		 if(lineOpt.isPresent()) {
		
			Optional<Driver> driverOne = driverService.findByID(driverOneId);
			List<Driver> driverList = new ArrayList<>();
			driverList.add(driverOne.get());
			
			line.setDrivers(driverList);
	
			lineService.save(line);
			
			Integer driversShiftsId = driversShiftsService.findLineAndDriver(id, driverOneId);
			Optional<DriversShifts> driversShiftsOne = driversShiftsService.findByID(driversShiftsId);
			
			driversShiftsOne.get().setShift(Shift.FIRST_SHIFT);
			driversShiftsOne.get().setUser(line.getUser());
			driverOne.get().setActive(false);
			
			// OLD DRIVERS ON ACTIVE
			for(int i=0; i<oldDrivers.size(); i++) {
									
				oldDrivers.get(i).setActive(true);
			}

			return ResponseEntity.ok(driversShiftsService.save(driversShiftsOne.get()));
		} 
		 else {
			 
		 return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
		}
	}
	
	@PutMapping(value="/updateTwo/{id}")
	public @ResponseBody ResponseEntity<DriversShifts> updateTwo(@PathVariable Integer id, 
																   	  		   Integer driverOneId, 
																   	  		   Integer driverTwoId, @RequestBody Line line) throws DriverExceptions {
		
		if(driverOneId == driverTwoId) {
			
			throw new DriverExceptions("You have two same drivers!");
		}
		
		if(driverOneId == null || driverTwoId == null) {
			
			throw new DriverExceptions("Driver can't be blank!");
		}
		  
		  
		 Optional<Line> lineOpt = lineService.findByID(id);
		 // PREVIOUS DRIVERS 
		 List<Driver> oldDrivers = driversShiftsService.oldDrivers(id);
		 
		  if(lineOpt.isPresent()) {
		  
			  Optional<Driver> driverOneOpt = driverService.findByID(driverOneId);
			  Optional<Driver> driverTwoOpt = driverService.findByID(driverTwoId);
			  
			  List<Driver> driverList = new ArrayList<>();
			  
			  driverList.add(driverOneOpt.get());
			  driverList.add(driverTwoOpt.get());
				
			  line.setDrivers(driverList);
			  
			  lineService.save(line);
			  
			  // DRIVER SHIFTS
			  Integer driversShiftsFirstId = driversShiftsService.findLineAndDriver(id, driverOneId);
			  Integer driversShiftsSecondId = driversShiftsService.findLineAndDriver(id, driverTwoId);
			  
			  Optional<DriversShifts> driversShiftsOne = driversShiftsService.findByID(driversShiftsFirstId);
			  Optional<DriversShifts> driversShiftsTwo = driversShiftsService.findByID(driversShiftsSecondId);
			  
				  
			  driversShiftsOne.get().setShift(Shift.FIRST_SHIFT);
			  driversShiftsOne.get().setUser(line.getUser());
			  driverOneOpt.get().setActive(false);
				  
			  driversShiftsService.save(driversShiftsOne.get());
			  
				  
			  driversShiftsTwo.get().setShift(Shift.SECOND_SHIFT);
			  driversShiftsTwo.get().setUser(line.getUser());
			  driverTwoOpt.get().setActive(false);
			  
			  // OLD DRIVERS ON ACTIVE
			  for(int i=0; i<oldDrivers.size(); i++) {
					
				oldDrivers.get(i).setActive(true);
			  }
				  
		  
		  return ResponseEntity.ok(driversShiftsService.save(driversShiftsTwo.get()));
		} 
		 else {
			 
		 return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
		}
	}
	
	@PutMapping(value="/updateThree/{id}")
	public @ResponseBody ResponseEntity<DriversShifts> update(@PathVariable Integer id, 
																   Integer driverOneId, 
																   Integer driverTwoId, 
																   Integer driverThreeId, @RequestBody Line line) throws DriverExceptions {
		
		
		if(driverOneId == driverTwoId || driverOneId == driverThreeId || driverTwoId == driverThreeId) {
			
			throw new DriverExceptions("You have same driver in your shifts, check your shifts!");
		}
		
		if(driverOneId == null || driverTwoId == null || driverThreeId == null) {
			
			throw new DriverExceptions("Driver can't be blank!");
		}
		
		 Optional<Line> lineOpt = lineService.findByID(id);
		 // PREVIOUS DRIVERS 
		 List<Driver> oldDrivers = driversShiftsService.oldDrivers(id);
		 
		  if(lineOpt.isPresent()) {
		  
			  Optional<Driver> driverOneOpt = driverService.findByID(driverOneId);
			  Optional<Driver> driverTwoOpt = driverService.findByID(driverTwoId);
			  Optional<Driver> driverThreeOpt = driverService.findByID(driverThreeId);
			  
			  List<Driver> driverList = new ArrayList<>();
			  
			  driverList.add(driverOneOpt.get());
			  driverList.add(driverTwoOpt.get());
			  driverList.add(driverThreeOpt.get());
			
			  line.setDrivers(driverList);
			 
			  lineService.save(line);
			  
			  // DRIVER SHIFTS
			  Integer driversShiftsFirstId = driversShiftsService.findLineAndDriver(id, driverOneId);
			  Integer driversShiftsSecondId = driversShiftsService.findLineAndDriver(id, driverTwoId);
			  Integer driversShiftsThirdId = driversShiftsService.findLineAndDriver(id, driverThreeId);
			  
			  Optional<DriversShifts> driversShiftsOne = driversShiftsService.findByID(driversShiftsFirstId);
			  Optional<DriversShifts> driversShiftsTwo = driversShiftsService.findByID(driversShiftsSecondId);
			  Optional<DriversShifts> driversShiftsThree = driversShiftsService.findByID(driversShiftsThirdId);
			  
				  
			  driversShiftsOne.get().setShift(Shift.FIRST_SHIFT);
			  driversShiftsOne.get().setUser(line.getUser());
			  driverOneOpt.get().setActive(false);
			  driversShiftsService.save(driversShiftsOne.get());
				
			  driversShiftsTwo.get().setShift(Shift.SECOND_SHIFT);
			  driversShiftsTwo.get().setUser(line.getUser());
			  driverTwoOpt.get().setActive(false);
			  driversShiftsService.save(driversShiftsTwo.get());
				  
			  driversShiftsThree.get().setShift(Shift.THIRD_SHIFT);
			  driversShiftsThree.get().setUser(line.getUser());
			  driverThreeOpt.get().setActive(false);
			  
			  // OLD DRIVERS ON ACTIVE
			  for(int i=0; i<oldDrivers.size(); i++) {
					
				oldDrivers.get(i).setActive(true);
			  }
			  
		  
		  return ResponseEntity.ok(driversShiftsService.save(driversShiftsThree.get()));
		} 
		 else {
			 
		 return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public @ResponseBody ResponseEntity<String> delete(@PathVariable Integer id) {
		
		lineService.delete(id);
		
		return ResponseEntity.status(HttpStatus.OK).body("Line deleted!");
	}

}
