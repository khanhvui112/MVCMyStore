package com.sanvui.model.dto.resp;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

/**
 * @author: VuiSK
 * @created: 06/12/2021-9:29 PM
 * @mailto: sanvankhanh@gmail.com
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponseDto implements Serializable {
    private boolean success;
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errorCode;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    public static BaseResponseDto success(String message, Object data) {
        return BaseResponseDto.builder()
                .success(true)
                .message(message)
                .data(data)
                .build();
    }

    public static BaseResponseDto error(String message, String error) {
        return BaseResponseDto.builder()
                .success(false)
                .message(message)
                .errorCode(error)
                .build();
    }

    public static BaseResponseDto error(String message, String error, Object data) {
        return BaseResponseDto.builder()
                .success(false)
                .message(message)
                .errorCode(error)
                .data(data)
                .build();

    }
}
