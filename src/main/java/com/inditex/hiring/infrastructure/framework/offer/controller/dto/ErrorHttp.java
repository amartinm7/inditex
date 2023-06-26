package com.inditex.hiring.infrastructure.framework.offer.controller.dto;

public record ErrorHttp (Exception exception) {
    public static ErrorHttp of(Exception exception) {
        return new ErrorHttp(exception);
    }
}
