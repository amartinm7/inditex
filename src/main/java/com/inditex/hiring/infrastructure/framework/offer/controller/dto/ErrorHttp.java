package com.inditex.hiring.infrastructure.framework.offer.controller.dto;

public record ErrorHttp(String msg) {
    public static ErrorHttp of(String msg) {
        return new ErrorHttp(msg);
    }
}
