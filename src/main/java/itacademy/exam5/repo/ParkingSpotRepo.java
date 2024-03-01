package itacademy.exam5.repo;

import itacademy.exam5.entity.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkingSpotRepo extends JpaRepository<ParkingSpot, Long> {
    Optional<ParkingSpot> findByUserId(Long userId);
    Optional<ParkingSpot> findBySpotNumber(String spotNumber);
}
