package drive.time.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import drive.time.jwt.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
