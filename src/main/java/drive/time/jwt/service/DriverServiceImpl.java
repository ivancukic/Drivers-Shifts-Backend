package drive.time.jwt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drive.time.jwt.entity.Driver;
import drive.time.jwt.repository.DriverRepository;

@Service
public class DriverServiceImpl implements DriverService  {
	
	private DriverRepository driverRepository;
	

	@Autowired
	public DriverServiceImpl(DriverRepository driverRepository) {
		this.driverRepository = driverRepository;
	}
	

	@Override
	public Driver save(Driver driver) {
		
		return driverRepository.save(driver);
	}

	@Override
	public List<Driver> findAll() {
		
		return driverRepository.findAll();
	}

	@Override
	public void delete(Integer id) {

		driverRepository.deleteById(id);
	}

	@Override
	public Optional<Driver> findByID(Integer id) {
		
		return driverRepository.findById(id);
	}

	

}
