package com.naturalgoods.backend.productType;

import com.naturalgoods.backend.auth.Language;
import com.naturalgoods.backend.dto.DropDownDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductTypeService {
    private final ProductTypeRepository productTypeRepository;

    public List<DropDownDto> productTypeList(Long productId, Language lang) {
        List<DropDownDto> result = new ArrayList<>();
        List<ProductTypeEntity> productTypeEntityList = productTypeRepository.findAllByProductId(productId);
        productTypeEntityList.stream().forEach(o -> {
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
