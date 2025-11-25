package com.arun.Products.advice;

import java.time.LocalDateTime;

class ErrorResponse {

    private LocalDateTime timestamp;
    private Integer status;
    private String message;

    public ErrorResponse(LocalDateTime timestamp, Integer status, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}