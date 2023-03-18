package com.naturalgoods.backend.rating;

import com.naturalgoods.backend.dto.RatingRequestDto;
import com.naturalgoods.backend.dto.RatingResponseDto;
import com.naturalgoods.backend.record.RecordEntity;
import com.naturalgoods.backend.record.RecordRepository;
import com.naturalgoods.backend.util.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RatingService {
    private final RatingRepository ratingRepository;
    private final RecordRepository recordRepository;

    public void postRating(RatingRequestDto requestDto) throws Exception {
        RecordEntity record = recordRepository.findById(requestDto.getRecordId()).orElseThrow(() -> new Exception("No such record"));
        RatingEntity rating = new RatingEntity();
        rating.setRecordId(requestDto.getRecordId());
        rating.setRating(requestDto.getRating());
        rating.setDescription(requestDto.getDescription());
//        rating.setUserId(SecurityUtils.getCurrentId());
        rating.setUserId(3L);
        ratingRepository.save(rating);

        List<RatingEntity> ratingList = ratingRepository.findAllByRecordId(requestDto.getRecordId());
        record.setRating(BigDecimal.valueOf(ratingList.stream().mapToInt(o -> o.getRating()).sum()/ratingList.size()));
        recordRepository.save(record);
    }

    public List<RatingResponseDto> getRating(Long recordId) throws Exception {
        recordRepository.findById(recordId).orElseThrow(() -> new Exception("No such record"));
        List<Object[]> ratingList = ratingRepository.findAllObjByRecordId(recordId);

        List<RatingResponseDto> resultList = ratingList.stream().map(o->{
            RatingResponseDto ratingResponseDto=new RatingResponseDto();
            ratingResponseDto.setId(Objects.nonNull(o[0]) ? Long.valueOf(o[0].toString()) : null);
            ratingResponseDto.setRating(Objects.nonNull(o[3]) ? Integer.valueOf(o[3].toString()) : null);
            ratingResponseDto.setDescription(Objects.nonNull(o[4]) ? o[4].toString() : null);
            ratingResponseDto.setUserName(Objects.nonNull(o[5]) ? o[5].toString() : null );
            return ratingResponseDto;
        }).collect(Collectors.toList());

        return resultList;
    }
}
