package itacademy.exam5.entity;

import itacademy.exam5.enums.ParkingSpotStatus;
import itacademy.exam5.enums.ParkingSpotType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParkingSpot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String spotNumber;
    @Enumerated(value = EnumType.STRING)
    private ParkingSpotStatus status;
    @Enumerated(value = EnumType.STRING)
    private ParkingSpotType type;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
