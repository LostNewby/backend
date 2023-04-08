package com.naturalgoods.backend.record;

import com.naturalgoods.backend.auth.Language;
import com.naturalgoods.backend.dto.FilterDto;
import com.naturalgoods.backend.dto.ProductCardsDto;
import com.naturalgoods.backend.dto.RecordAddDto;
import com.naturalgoods.backend.image.ImageService;
import com.naturalgoods.backend.record.enums.SortingType;
import com.naturalgoods.backend.util.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RecordService {
    private final EntityManager entityManager;
    private final RecordRepository recordRepository;
    private final ImageService imageService;

    //TODO p->pt
    public Page<ProductCardsDto> filter(FilterDto filter, Integer page, Integer pageSize, Language lang) {
        page--;
        StringBuilder builder = new StringBuilder();
        builder.append("select r.id,");
        if(lang.equals(Language.RU)){
            builder.append(" pt.name_ru,");
        } else if(lang.equals(Language.KK)){
            builder.append(" pt.name_kz,");
        }  else if(lang.equals(Language.EN)){
            builder.append(" pt.name_en,");
        }

        builder.append(" r.description, r.rating, r.price from records r left join product_type pt on pt.id = r.product_type_id left join product p on p.id = pt.product_id left join category c on c.id = p.category_id where lower(r.region) = lower(:region) and r.price >= :minPrice  ");
        if (!CollectionUtils.isEmpty(filter.getCategoryId()) && filter.getCategoryId().get(0)!=0L) {
            builder.append("and c.id in :category ");
        }
        if (!CollectionUtils.isEmpty(filter.getProductId())  && filter.getProductId().get(0)!=0L) {
            builder.append("and p.id in :product ");
        }
        if (!CollectionUtils.isEmpty(filter.getProductTypeId())  && filter.getProductTypeId().get(0)!=0L) {
            builder.append("and pt.id in :productType ");
        }
        if (Objects.nonNull(filter.getMaxPrice())) {
            builder.append("and r.price <= :maxPrice ");
        }
        if (Objects.nonNull(filter.getName()) && !filter.getName().equals("")) {
            if(lang.equals(Language.RU)){
                builder.append("and lower(pt.name_ru) like lower(:name) ");
            } else if(lang.equals(Language.KK)){
                builder.append("and lower(pt.name_kz) like lower(:name) ");
            }  else if(lang.equals(Language.EN)){
                builder.append("and lower(pt.name_en) like lower(:name) ");
            }
        }
        if (filter.getSortingType().equals(SortingType.HIGH_PRICE)) {
            builder.append("order by r.price DESC");
        } else if (filter.getSortingType().equals(SortingType.LOW_PRICE)) {
            builder.append("order by r.price ASC");
        } else if (filter.getSortingType().equals(SortingType.RATING)) {
            builder.append("order by r.rating DESC");
        }
        Query query = entityManager.createNativeQuery(builder.toString());
        query.setParameter("region", filter.getRegion());
        query.setParameter("minPrice", filter.getMinPrice());

        if (!CollectionUtils.isEmpty(filter.getCategoryId()) && filter.getCategoryId().get(0)!=0L) {
            query.setParameter("category", filter.getCategoryId());
        }
        if (!CollectionUtils.isEmpty(filter.getProductId()) && filter.getProductId().get(0)!=0L) {
            query.setParameter("product", filter.getProductId());
        }
        if (!CollectionUtils.isEmpty(filter.getProductTypeId()) && filter.getProductTypeId().get(0)!=0L) {
            query.setParameter("productType", filter.getProductTypeId());
        }
        if (Objects.nonNull(filter.getMaxPrice())) {
            query.setParameter("maxPrice", filter.getMaxPrice());
        }
        if (Objects.nonNull(filter.getName()) && !filter.getName().equals("")) {
            query.setParameter("name", "%" + filter.getName() + "%");
        }

        List<ProductCardsDto> result = (List<ProductCardsDto>) query.getResultList().stream().map(o -> {
            Object[] obj = (Object[]) o;
            ProductCardsDto productCardsDto = new ProductCardsDto();
            productCardsDto.setId(Long.valueOf(String.valueOf(obj[0])));
            productCardsDto.setName(String.valueOf(obj[1]));
            productCardsDto.setDescription(String.valueOf(obj[2]));
            productCardsDto.setRating(BigDecimal.valueOf(Double.valueOf(String.valueOf(obj[3]))));
            productCardsDto.setPrice(Long.valueOf(String.valueOf(obj[4])));
            productCardsDto.setPhoto(imageService.getDownloadLink(Long.valueOf(String.valueOf(obj[0]))));
            return productCardsDto;
        }).collect(Collectors.toList());

        Page<ProductCardsDto> finalResult = convertToPage(result, PageRequest.of(page, pageSize));

        return finalResult;
    }

    public void recordAdd(RecordAddDto productAddDto) {
        RecordEntity record = new RecordEntity();
        record.setProductTypeId(productAddDto.getProductTypeId());
        record.setUserId(SecurityUtils.getCurrentId());
        record.setQuantity(productAddDto.getQuantity());
        record.setDescription(productAddDto.getDescription());
        record.setPrice(productAddDto.getPrice());
        record.setLimit(productAddDto.getLimit());
        record.setRegion(productAddDto.getRegion());
        record.setRating(BigDecimal.ZERO);

        record = recordRepository.save(record);


        if(productAddDto.getPhoto()!=null){
            imageService.upload(record.getId(), productAddDto.getPhoto());
        }
    }

    public void recordEdit(RecordAddDto productAddDto) {
        RecordEntity record = recordRepository.findById(productAddDto.getId()).get();
        record.setProductTypeId(productAddDto.getProductTypeId());
        record.setQuantity(productAddDto.getQuantity());
        record.setDescription(productAddDto.getDescription());
        record.setPrice(productAddDto.getPrice());
        record.setLimit(productAddDto.getLimit());
        record.setRegion(productAddDto.getRegion());

        record = recordRepository.save(record);

        if(productAddDto.getPhoto()!=null){
            imageService.upload(record.getId(), productAddDto.getPhoto());
        }
    }

    public static <T> Page<T> convertToPage(List<T> objectList, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), objectList.size());
        List<T> subList = start >= end ? new ArrayList<>() : objectList.subList(start, end);
        return new PageImpl<>(subList, pageable, objectList.size());
    }

}
