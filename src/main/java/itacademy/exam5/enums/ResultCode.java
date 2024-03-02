package itacademy.exam5.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ResultCode {
    OK(200),
    FAIL(400),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500),
    ;

    int httpCode;
}
