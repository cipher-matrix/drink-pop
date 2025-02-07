package com.birimarung.dto;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class RestResponse<T> {
    private String message;
    private Integer statusCode;
    private T data;
}
