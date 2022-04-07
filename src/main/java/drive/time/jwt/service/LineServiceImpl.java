package drive.time.jwt.service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drive.time.jwt.entity.Line;
import drive.time.jwt.repository.LineRepository;

@Service
public class LineServiceImpl implements LineService {

	private LineRepository lineRepository;

	@Autowired
	public LineServiceImpl(LineRepository lineRepository) {
		this.lineRepository = lineRepository;
	}

	@Override
	public Line save(Line line) {
		
		return lineRepository.save(line);
	}
	

	@Override
	public List<Line> findAll() {
		
		return lineRepository.findAll();
	}

	@Override
	public void delete(Integer id) {

		lineRepository.deleteById(id);
	}

	@Override
	public Optional<Line> findByID(Integer id) {
		
		return lineRepository.findById(id);
	}

	@Override
	public void calcTotalTime(LocalTime end_time, LocalTime start_time) {
		
		lineRepository.calcTotalTime(end_time, start_time);
	}

	@Override
	public void negativeTime() {
		
		lineRepository.negativeTime();
	}

	@Override
	public void numberOfDrivers() {

		lineRepository.numberOfDrivers();
	}

	
	
}
