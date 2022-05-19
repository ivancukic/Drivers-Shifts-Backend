package drive.time.jwt.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import drive.time.jwt.entity.Driver;
import drive.time.jwt.entity.DriversShifts;
import drive.time.jwt.exception.ShiftException;
import drive.time.jwt.service.DriverService;
import drive.time.jwt.service.DriversShiftsService;

@RestController
@RequestMapping("/api/schedule")
public class DriversShiftsController {
	
	private DriversShiftsService driversShiftsService;
	private DriverService driverService;

	@Autowired
	public DriversShiftsController(DriversShiftsService driversShiftsService, DriverService driverService) {
		this.driversShiftsService = driversShiftsService;
		this.driverService = driverService;
	}
	
	@GetMapping("/all")
	public @ResponseBody ResponseEntity<List<DriversShifts>> findAll() {
		
		List<DriversShifts> driverShifts = driversShiftsService.findAll();
		
		return ResponseEntity.status(HttpStatus.OK).body(driverShifts);
	}
	
	
	@GetMapping("/get/{id}")
	public @ResponseBody ResponseEntity<DriversShifts> get(@PathVariable Integer id) {
		
		Optional<DriversShifts> driverShifts = driversShiftsService.findByID(id);
		
		if(driverShifts.isPresent()) {
			
			return ResponseEntity.status(HttpStatus.OK).body(driverShifts.get());
		}
		else {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@PutMapping(value = "/changeShift/{id}")
	public @ResponseBody ResponseEntity<DriversShifts> changeShift(@PathVariable Integer id, @RequestBody DriversShifts driversShifts) throws ShiftException {
		
		
		Optional<DriversShifts> driverShiftsOpt = driversShiftsService.findByID(id);
		Integer lineId = driversShiftsService.findLineId(id);
		List<DriversShifts> driversShiftsList = driversShiftsService.driversShiftsListByLine(lineId);
		
		
		if(driverShiftsOpt != null) {
			
			if(driverShiftsOpt.get().getShift() == driversShifts.getShift()) {
				
				driverShiftsOpt.get().setDriverId(driversShifts.getDriverId());
				driverShiftsOpt.get().setShift(driversShifts.getShift());
				driverShiftsOpt.get().setUser(driversShifts.getUser());
				Optional<Driver> oldDriver = driverService.findByID(driverShiftsOpt.get().getDriverId());
				oldDriver.get().setActive(true);
				
			}
			else if(driversShiftsService.checkShift(driversShiftsList, driversShifts.getShift()) == true) {
				
				throw new ShiftException("You can't have two Drivers on same shift! Please use option CHANGE_SHIFT!");
			}
			
			return ResponseEntity.ok(driversShiftsService.save(driversShifts));
		}
		else {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
		}
	}
	

}
