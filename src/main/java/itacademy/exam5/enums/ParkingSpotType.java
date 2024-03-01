package itacademy.exam5.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ParkingSpotType {
    STANDARD("Стандартное место"),
    DISABLED("Для инвалидов"),
    FAMILY("Для сепмей с детьми"),
    ELECTRIC_VEHICLES("Для электромобилей"),
    ;
    String description;
}
