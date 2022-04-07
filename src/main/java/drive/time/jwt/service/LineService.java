package drive.time.jwt.service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import drive.time.jwt.entity.Line;

public interface LineService {
	
	public Line save(Line line);
	public List<Line> findAll();

	public void delete(Integer id);

	Optional<Line> findByID(Integer id);
	
	void calcTotalTime (LocalTime end_time, LocalTime start_time);
	
	void negativeTime();
	
	void numberOfDrivers();

}
