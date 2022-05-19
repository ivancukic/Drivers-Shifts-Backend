package drive.time.jwt.repository;

import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import drive.time.jwt.entity.Line;

@Repository
public interface LineRepository extends JpaRepository<Line, Integer> {
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE `drive-time-db`.publiclines SET `total_time` = SUBTIME(end_time, start_time)", nativeQuery = true)
	void calcTotalTime(LocalTime end_time, LocalTime start_time);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE `drive-time-db`.publiclines SET total_time = IF (total_time < '00:00:00', ADDTIME(total_time, '24:00:00'), total_time)", nativeQuery = true)
	void negativeTime();
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE `drive-time-db`.publiclines SET `num_drivers` = IF (`total_time` < '08:01:00', 1, IF (`total_time` < '16:01:00', 2, 3))", nativeQuery = true)
	void numberOfDrivers();

}
