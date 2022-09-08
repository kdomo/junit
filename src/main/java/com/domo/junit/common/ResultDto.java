package com.domo.junit.common;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResultDto<T> {
    private Integer code;
    private String msg;
    private T body;

    @Builder
    public ResultDto(Integer code, String msg, T body) {
        this.code = code;
        this.msg = msg;
        this.body = body;
    }
}
