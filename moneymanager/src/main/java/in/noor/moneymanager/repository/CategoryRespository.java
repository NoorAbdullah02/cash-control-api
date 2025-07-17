package in.noor.moneymanager.repository;

import in.noor.moneymanager.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRespository extends JpaRepository<CategoryEntity, Long> {

 // Select * from tbl_category
  List<CategoryEntity> findByProfileId(Long profileId);

 Optional<CategoryEntity> findByIdAndProfileId(Long id, Long profileId);

 List<CategoryEntity> findByTypeAndProfileId(String type, Long profileId);

 Boolean existsByNameAndProfileId(String name, Long profileId);
}
