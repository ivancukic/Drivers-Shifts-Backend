package drive.time.jwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import drive.time.jwt.entity.DriversShifts;

@Repository
public interface DriversShiftsRepository extends JpaRepository<DriversShifts, Integer> {
	
	@Transactional
	@Query(value = "SELECT id FROM `drive-time-db`.drivers_shifts WHERE publiclines_id = ?1 AND driver_id = ?2", nativeQuery = true)
	Integer findLineAndDriver(Integer lineId, Integer driverId);
	
	@Transactional
	@Query(value = "SELECT * FROM `drive-time-db`.drivers_shifts WHERE publiclines_id = ?1", nativeQuery = true)
	List<DriversShifts> lineListIds(Integer lineId);
	
	@Transactional
	@Query(value = "SELECT publiclines_id FROM `drive-time-db`.drivers_shifts WHERE id = ?1", nativeQuery = true)
	Integer findLineId(Integer driversShiftsId);
	
	@Transactional
	@Query(value = "SELECT driver_id FROM `drive-time-db`.drivers_shifts WHERE id = ?1", nativeQuery = true)
	Integer findDriverId(Integer driversShiftsId);
	
}
