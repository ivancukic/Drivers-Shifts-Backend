package drive.time.jwt.service;

import java.util.List;
import java.util.Optional;

import drive.time.jwt.entity.DriversShifts;

public interface DriversShiftsService {
	
	public List<DriversShifts> findAll();
	
	Optional<DriversShifts> findByID(Integer id);
	
	public DriversShifts save(DriversShifts driversShifts);
	
	public Integer findLineAndDriver(Integer lineId, Integer driverId);
	
	public void delete(Integer id);

}
