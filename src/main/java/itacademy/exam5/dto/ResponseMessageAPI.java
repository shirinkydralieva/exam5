package itacademy.exam5.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import itacademy.exam5.enums.ResultCodeAPI;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseMessageAPI<T>{
    T result;
    String resultCode;
    String details;
    String message;
    int code;

    public ResponseMessageAPI(T result, ResultCodeAPI resultCode, String details, String message, int code) {
        this.result = result;
        this.resultCode = resultCode.getDescription();
        this.details = details;
        this.message = message;
        this.code = code;
    }
}
