package drive.time.jwt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drive.time.jwt.entity.Driver;
import drive.time.jwt.entity.DriversShifts;
import drive.time.jwt.entity.Shift;
import drive.time.jwt.repository.DriverRepository;
import drive.time.jwt.repository.DriversShiftsRepository;

@Service
public class DriversShiftsServiceImpl implements DriversShiftsService {
	
	private DriversShiftsRepository driversShiftsRepository; 
	private DriverRepository driverRepository; 
	
	
	@Autowired
	public DriversShiftsServiceImpl(DriversShiftsRepository driversShiftsRepository, DriverRepository driverRepository) {
		this.driversShiftsRepository = driversShiftsRepository;
		this.driverRepository = driverRepository;
	}



	@Override
	public Optional<DriversShifts> findByID(Integer id) {

		return driversShiftsRepository.findById(id);
	}



	@Override
	public DriversShifts save(DriversShifts driversShifts) {
		
		return driversShiftsRepository.save(driversShifts);
	}



	@Override
	public List<DriversShifts> findAll() {
		
		return driversShiftsRepository.findAll();
	}



	@Override
	public Integer findLineAndDriver(Integer lineId, Integer driverId) {
		
		Integer queryInt = null;
		
		queryInt = driversShiftsRepository.findLineAndDriver(lineId, driverId);
		
		return queryInt;
	}
	

	@Override
	public List<Driver> oldDrivers(Integer lineId) {
		
		List<DriversShifts> driversShifts = driversShiftsRepository.lineListIds(lineId);
		List<Driver> drivers = new ArrayList<>();
		List<Integer> driversIds = new ArrayList<>();
		
		for(int i=0; i<driversShifts.size(); i++) {
			
			driversIds.add(driversShifts.get(i).getDriverId());
		}
		
		for(int j=0; j<driversIds.size(); j++) {
			
			Optional<Driver> driver = driverRepository.findById(driversIds.get(j));
			
			drivers.add(driver.get());
		}
		
		
		return drivers;
	}
	

	@Override
	public Integer findLineId(Integer driversShiftsId) {
		
		return driversShiftsRepository.findLineId(driversShiftsId);
	}



	@Override
	public List<DriversShifts> driversShiftsListByLine(Integer lineId) {
		
		List<DriversShifts> driversShifts = driversShiftsRepository.lineListIds(lineId);
		
		return driversShifts;
	}



	@Override
	public boolean checkShift(List<DriversShifts> driversShifts, Shift shift) {
		
		for(int i=0; i<driversShifts.size(); i++) {
			
			if(driversShifts.get(i).getShift() == shift) {
				
				return true;
			}
		}
		
		return false;
	}


	
}
