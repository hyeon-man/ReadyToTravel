package kr.ac.kopo.ReadyToTravel.root;

import kr.ac.kopo.ReadyToTravel.entity.PlaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<PlaceEntity, Long> {
}
