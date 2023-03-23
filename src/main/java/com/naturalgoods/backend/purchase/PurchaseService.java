package com.naturalgoods.backend.purchase;

import com.naturalgoods.backend.account.UserRepository;
import com.naturalgoods.backend.dto.CostumerListResponse;
import com.naturalgoods.backend.dto.PurchaseRequestDto;
import com.naturalgoods.backend.record.RecordEntity;
import com.naturalgoods.backend.record.RecordRepository;
import com.naturalgoods.backend.util.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final RecordRepository recordRepository;
    private final UserRepository userRepository;

    @Transactional
    public void purchaseRequest(List<PurchaseRequestDto> requestDtoList) {
        for (PurchaseRequestDto requestDto : requestDtoList) {
            PurchaseEntity purchase = new PurchaseEntity();
            purchase.setCostumerId(1L);
//            purchase.setCostumerId(SecurityUtils.getCurrentId());
            purchase.setQuantity(requestDto.getQuantity());
            purchase.setPurchaseStatus(PurchaseStatus.Processing);
            purchase.setCostumerPhone(requestDto.getPhoneNumber());
            purchase.setRecordId(requestDto.getRecordId());
            RecordEntity record=recordRepository.findById(requestDto.getRecordId()).get();
            record.setQuantity(record.getQuantity() - requestDto.getQuantity());
            recordRepository.save(record);
            purchaseRepository.save(purchase);
        }
    }

    public Map<String,List<CostumerListResponse>> getCostumerList() {
        Map<String,List<CostumerListResponse>> result;
        result = purchaseRepository.findBySellerId(1L).stream().map(o->{
//        result = purchaseRepository.findBySellerId(SecurityUtils.getCurrentId()).stream().map(o->{
            CostumerListResponse costumerListResponse=new CostumerListResponse();
            costumerListResponse.setCostumer(userRepository.findById(Long.valueOf(o[0].toString())).isEmpty()?"":userRepository.findById(Long.valueOf(o[0].toString())).get().getEmail());
            costumerListResponse.setPurchaseNameKz(o[1]==null? "":o[1].toString());
            costumerListResponse.setPurchaseNameRu(o[2]==null? "":o[2].toString());
            costumerListResponse.setPurchaseNameEn(o[3]==null? "":o[3].toString());
            costumerListResponse.setQuantity(Integer.valueOf(o[4].toString()));
            costumerListResponse.setPrice(Long.valueOf(o[5].toString()));
            costumerListResponse.setCostumerPhone(o[6].toString());
            costumerListResponse.setPurchaseDate((Date) o[7]);
            return costumerListResponse;
        }).collect(Collectors.groupingBy(CostumerListResponse::getCostumer));
        return result;
    }
}
