package drive.time.jwt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drive.time.jwt.entity.DriversShifts;
import drive.time.jwt.repository.DriversShiftsRepository;

@Service
public class DriversShiftsServiceImpl implements DriversShiftsService {
	
	private DriversShiftsRepository driversShiftsRepository; 
	
	
	@Autowired
	public DriversShiftsServiceImpl(DriversShiftsRepository driversShiftsRepository) {
		this.driversShiftsRepository = driversShiftsRepository;
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
	public void delete(Integer id) {
		
		driversShiftsRepository.deleteById(id);
	}

}
