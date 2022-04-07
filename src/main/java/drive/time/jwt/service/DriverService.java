package drive.time.jwt.service;

import java.util.List;
import java.util.Optional;

import drive.time.jwt.entity.Driver;

public interface DriverService {
	
	public Driver save(Driver driver);

	public List<Driver> findAll();

	public void delete(Integer id);

	Optional<Driver> findByID(Integer id);
	

}
