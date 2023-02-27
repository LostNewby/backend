package com.naturalgoods.backend.record;

import com.naturalgoods.backend.dto.ProductCardsDto;
import com.naturalgoods.backend.record.enums.SortingType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RecordService {
    private final EntityManager entityManager;
    //TODO p->pt
    public List<ProductCardsDto> filter(String name, List<Long> categoryId, List<Long> productId, List<Long> productTypeId, SortingType sortingType, Long minPrice, Long maxPrice, String region){
        StringBuilder builder = new StringBuilder();
        builder.append("select r.id, pt.name_ru, r.description, r.rating, r.price from records r left join product_type pt on pt.id = r.product_type_id left join product p on p.id = pt.product_id left join category c on c.id = p.category_id where r.region = :region and r.price >= :minPrice  ");
        if(!CollectionUtils.isEmpty(categoryId)){
            builder.append("and c.id in :category ");
        }
        if(!CollectionUtils.isEmpty(productId)){
            builder.append("and p.id in :product ");
        }
        if(!CollectionUtils.isEmpty(productTypeId)){
            builder.append("and pt.id in :productType ");
        }
        if(Objects.nonNull(maxPrice)){
            builder.append("and r.price <= :maxPrice ");
        }
        if(Objects.nonNull(name) && !name.equals("")){
            builder.append("and lower(pt.name_kz) like lower(:name) or lower(pt.name_ru) like lower(:name) or lower(pt.name_en) like lower(:name) ");
        }
        if(sortingType.equals(SortingType.PRICE)){
            builder.append("order by r.price");
        } else if(sortingType.equals(SortingType.RATING)){
            builder.append("order by r.rating");
        }
        Query query = entityManager.createNativeQuery(builder.toString());
        query.setParameter("region", region);
        query.setParameter("minPrice", minPrice);

        if(!CollectionUtils.isEmpty(categoryId)){
            query.setParameter("category", categoryId);
        }
        if(!CollectionUtils.isEmpty(productId)){
            query.setParameter("product", productId);
        }
        if(!CollectionUtils.isEmpty(productTypeId)){
            query.setParameter("productType", productTypeId);
        }
        if(Objects.nonNull(maxPrice)){
            query.setParameter("maxPrice", maxPrice);
        }
        if(Objects.nonNull(name) && !name.equals("")){
            query.setParameter("name", "%"+name+"%");
        }

        List<ProductCardsDto> result = (List<ProductCardsDto>) query.getResultList().stream().map(o->{
            Object[] obj = (Object[]) o;
            ProductCardsDto productCardsDto = new ProductCardsDto();
            productCardsDto.setId(Long.valueOf(String.valueOf(obj[0])));
            productCardsDto.setName(String.valueOf(obj[1]));
            productCardsDto.setDescription(String.valueOf(obj[2]));
            productCardsDto.setRating(BigDecimal.valueOf(Double.valueOf(String.valueOf(obj[3]))));
            productCardsDto.setPrice(Long.valueOf(String.valueOf(obj[4])));
            return productCardsDto;
        }).collect(Collectors.toList());

        return result;
    }
}
