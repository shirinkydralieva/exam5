package itacademy.exam5.dto;

import itacademy.exam5.enums.ParkingSpotStatus;
import itacademy.exam5.enums.ParkingSpotType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParkingSpotDto {
    private Long id;
    private String spotNumber;
    private ParkingSpotStatus status;
    private ParkingSpotType type;
    private Long userId;
}
