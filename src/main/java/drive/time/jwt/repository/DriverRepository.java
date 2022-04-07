package drive.time.jwt.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import drive.time.jwt.entity.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {

}
