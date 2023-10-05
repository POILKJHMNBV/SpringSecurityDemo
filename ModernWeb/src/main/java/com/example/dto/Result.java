package com.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhenwu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result <T> implements Serializable {

    private static final long serialVersionUID = -4542160094446174851L;

    private Integer code;
    private T data;
    private String errorMsg;

    public static <T> Result<T> ok() {
        return new Result<T>(200, null, null);
    }

    public static <T> Result<T> ok(T data) {
        return new Result<T>(200, data, null);
    }

    public static <T> Result<T> fail(String errorMsg) {
        return new Result<T>(500, null, errorMsg);
    }

    public static <T> Result<T> fail(Integer code, String errorMsg) {
        return new Result<T>(code, null, errorMsg);
    }

    public static <T> Result<T> forbidden(String errorMsg) {
        return new Result<T>(403, null, errorMsg);
    }

    public static <T> Result<T> unauthorized(String errorMsg) {
        return new Result<T>(401, null, errorMsg);
    }
}