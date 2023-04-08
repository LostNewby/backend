package com.naturalgoods.backend.api;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ApiListResponse<T> extends ApiResponse {
    private List<T> list;
    private Long totalCount;

    public ApiListResponse(List<T> list) {
        this.list = list;
        setSuccess(true);
    }

    public ApiListResponse(List<T> list, Long totalCount) {
        this.list = list;
        this.totalCount = totalCount;
        setSuccess(true);
    }
}
