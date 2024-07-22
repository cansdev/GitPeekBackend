//ResponseDto.java:
package com.gitpeek.gitpeek_backend.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto {
    private String message;
    private int statusCode;
}
