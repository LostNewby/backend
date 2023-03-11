package com.naturalgoods.backend.dto;

import com.naturalgoods.backend.record.enums.SortingType;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;
@Data
public class FilterDto {
    String name;
    List<Long> categoryId;
    List<Long> productId;
    List<Long> productTypeId;
    SortingType sortingType;
    Long minPrice;
    Long maxPrice;
    @NotNull
    String region;
}
