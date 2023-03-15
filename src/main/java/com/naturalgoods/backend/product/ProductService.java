package com.naturalgoods.backend.product;

import com.naturalgoods.backend.auth.Language;
import com.naturalgoods.backend.dto.DropDownDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<DropDownDto> productList(Long categoryId, Language lang) {
        List<DropDownDto> result = new ArrayList<>();
        List<ProductEntity> productEntitiesList = productRepository.findAllByCategoryId(categoryId);
        productEntitiesList.stream().forEach(o -> {
            DropDownDto dropDownDto = new DropDownDto();
            dropDownDto.setValue(o.getId());
            if (lang.equals(Language.RU)) {
                dropDownDto.setName(o.getNameRu());
            } else if (lang.equals(Language.EN)) {
                dropDownDto.setName(o.getNameEn());
            } else if (lang.equals(Language.KK)) {
                dropDownDto.setName(o.getNameKz());
            }
            result.add(dropDownDto);
        });
        return result;
    }
}
