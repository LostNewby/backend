package com.naturalgoods.backend.api;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ApiErrorResponse extends ApiResponse {
    private String msg;
    private List<String> errors;

    public ApiErrorResponse(String msg) {
        this.msg = msg;
        setSuccess(false);
    }

    public ApiErrorResponse(String msg, List<String> errors) {
        this.msg = msg;
        this.errors = errors;
        setSuccess(false);
    }

    public static ApiErrorResponse create(String msg) {
        return new ApiErrorResponse(msg);
    }

    public static ApiErrorResponse create(String msg, List<String> errors) {
        return new ApiErrorResponse(msg, errors);
    }

    public static ApiErrorResponse validationError(List<String> errors) {
        return new ApiErrorResponse("Validation error", errors);
    }
}
