package com.example.togather.exception.errorcode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    //400
    NOT_BLANK("4000", "필수값이 공백입니다."),
    PATTERN("4001","형식에 맞지 않습니다."),
    LENGTH("4002", "길이가 유효하지 않습니다."),
    NOT_NULL("4003", "필수값이 공백입니다.")
    ;

    private final String code;
    private final String message;

    public static ErrorCode resolveValidationErrorCode(String code){
        return switch (code){
            case "Pattern" -> PATTERN;
            case "NotBlank" -> NOT_BLANK;
            case "Length" -> LENGTH;
            case "NotNull" -> NOT_NULL;
            default -> throw new IllegalArgumentException("Unexpected value: "+ code);
        };
    }
}