package com.naturalgoods.backend.api;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ApiDataResponse<T> extends ApiResponse {
    private T data;

    public ApiDataResponse(T data) {
        this.data = data;
        setSuccess(true);
    }
}
