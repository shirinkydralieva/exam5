package itacademy.exam5.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ResultCodeAPI {
    SUCCESS("SUCCESS"),
    FAIL("FAIL"),
    EXCEPTION("EXCEPTION"),
    ;

    String description;
}
