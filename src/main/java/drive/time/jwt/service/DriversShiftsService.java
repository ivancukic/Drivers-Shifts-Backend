package drive.time.jwt.service;

import java.util.List;
import java.util.Optional;

import drive.time.jwt.entity.Driver;
import drive.time.jwt.entity.DriversShifts;
import drive.time.jwt.entity.Shift;

public interface DriversShiftsService {
	
	public List<DriversShifts> findAll();
	
	Optional<DriversShifts> findByID(Integer id);
	
	public DriversShifts save(DriversShifts driversShifts);
	
	public Integer findLineAndDriver(Integer lineId, Integer driverId);
	
	public List<Driver> oldDrivers(Integer lineId);
	
	public Integer findLineId(Integer driversShiftsId);
	
	public List<DriversShifts> driversShiftsListByLine(Integer lineId);
	
	public boolean checkShift(List<DriversShifts> driversShifts, Shift shift);
	

}
