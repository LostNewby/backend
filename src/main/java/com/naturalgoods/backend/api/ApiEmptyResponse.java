package com.naturalgoods.backend.api;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ApiEmptyResponse extends ApiResponse {
    public ApiEmptyResponse() {
        setSuccess(true);
    }

    public static ApiEmptyResponse create() {
        return new ApiEmptyResponse();
    }
}
