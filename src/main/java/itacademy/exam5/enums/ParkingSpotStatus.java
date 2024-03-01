package itacademy.exam5.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ParkingSpotStatus {
    FREE("Свободно"),
    OCCUPIED("Занято"),
    ;
    String description;
}
