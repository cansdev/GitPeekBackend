package com.gitpeek.gitpeek_backend.payload;

import lombok.Data;

@Data
public class ResponseDto {

    private String message;
    private int status;
    private Object data;

    public ResponseDto(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public ResponseDto(String message, int status, Object data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public ResponseDto(String message) {
        this.message = message;
    }
}
