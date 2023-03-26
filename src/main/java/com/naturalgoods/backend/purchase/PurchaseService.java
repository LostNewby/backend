package com.naturalgoods.backend.purchase;

import com.amazonaws.transform.MapEntry;
import com.naturalgoods.backend.account.UserRepository;
import com.naturalgoods.backend.auth.Language;
import com.naturalgoods.backend.dto.CostumerListResponse;
import com.naturalgoods.backend.dto.PurchaseListDto;
import com.naturalgoods.backend.dto.PurchaseRequestDto;
import com.naturalgoods.backend.dto.SellerListResponse;
import com.naturalgoods.backend.record.RecordEntity;
import com.naturalgoods.backend.record.RecordRepository;
import com.naturalgoods.backend.util.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
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
            purchase.setCostumerId(SecurityUtils.getCurrentId());
            purchase.setQuantity(requestDto.getQuantity());
            purchase.setPurchaseStatus(PurchaseStatus.PROCESSING);
            purchase.setCostumerPhone(requestDto.getPhoneNumber());
            purchase.setRecordId(requestDto.getRecordId());
            RecordEntity record=recordRepository.findById(requestDto.getRecordId()).get();
            record.setQuantity(record.getQuantity() - requestDto.getQuantity());
            recordRepository.save(record);
            purchaseRepository.save(purchase);
        }
    }

    public List<PurchaseListDto> getCostumerList(PurchaseStatus purchaseStatus) {
        Map<String,List<CostumerListResponse>> result;
        List<PurchaseListDto> purchaseListResult= new ArrayList<>();
        result = purchaseRepository.findBySellerId(SecurityUtils.getCurrentId(), purchaseStatus.toString()).stream().map(o->{
            CostumerListResponse costumerListResponse=new CostumerListResponse();
            costumerListResponse.setCostumer(userRepository.findById(Long.valueOf(o[0].toString())).isEmpty()?"":userRepository.findById(Long.valueOf(o[0].toString())).get().getEmail());
            costumerListResponse.setPurchaseNameKz(o[1]==null? "":o[1].toString());
            costumerListResponse.setPurchaseNameRu(o[2]==null? "":o[2].toString());
            costumerListResponse.setPurchaseNameEn(o[3]==null? "":o[3].toString());
            costumerListResponse.setQuantity(Integer.valueOf(o[4].toString()));
            costumerListResponse.setPrice(Long.valueOf(o[5].toString()));
            costumerListResponse.setCostumerPhone(o[6].toString());
            costumerListResponse.setPurchaseDate((Date) o[7]);
            costumerListResponse.setUnit(o[8]==null? "":o[3].toString());
            costumerListResponse.setPurchaseId(Long.valueOf(o[9].toString()));
            return costumerListResponse;
        }).collect(Collectors.groupingBy(CostumerListResponse::getCostumer));

        for(Map.Entry<String, List<CostumerListResponse>> items: result.entrySet()){
            PurchaseListDto purchaseListDto=new PurchaseListDto();
            purchaseListDto.setEmail(items.getKey());
            Long price = 0L;
            Map<Language, String> names = new HashMap<>();
            for(CostumerListResponse listItem: items.getValue()){
                names.put(Language.EN, listItem.getQuantity() + listItem.getUnit() + listItem.getPurchaseNameEn());
                names.put(Language.RU, listItem.getQuantity() + listItem.getUnit() + listItem.getPurchaseNameRu());
                names.put(Language.KK, listItem.getQuantity() + listItem.getUnit() + listItem.getPurchaseNameKz());
                price+=listItem.getPrice();
                purchaseListDto.setCostumerPhone(listItem.getCostumerPhone());
                purchaseListDto.setPurchaseDate(listItem.getPurchaseDate());
            }
            purchaseListDto.setPrice(price);
            purchaseListDto.setPurchaseName(names);
            purchaseListResult.add(purchaseListDto);
        }
        return purchaseListResult;
    }

    public List<PurchaseListDto> getPurchaseList(PurchaseStatus purchaseStatus) {
        Map<String,List<SellerListResponse>> result;
        List<PurchaseListDto> purchaseListResult= new ArrayList<>();
        result = purchaseRepository.findByCustomerId(SecurityUtils.getCurrentId(), purchaseStatus.toString()).stream().map(o->{
            SellerListResponse sellerListResponse=new SellerListResponse();
            sellerListResponse.setSeller(userRepository.findById(Long.valueOf(o[0].toString())).isEmpty()?"":userRepository.findById(Long.valueOf(o[0].toString())).get().getEmail());
            sellerListResponse.setPurchaseNameKz(o[1]==null? "":o[1].toString());
            sellerListResponse.setPurchaseNameRu(o[2]==null? "":o[2].toString());
            sellerListResponse.setPurchaseNameEn(o[3]==null? "":o[3].toString());
            sellerListResponse.setQuantity(Integer.valueOf(o[4].toString()));
            sellerListResponse.setPrice(Long.valueOf(o[5].toString()));
            sellerListResponse.setCostumerPhone(o[6].toString());
            sellerListResponse.setPurchaseDate((Date) o[7]);
            sellerListResponse.setUnit(o[8]==null? "":o[3].toString());
            sellerListResponse.setPurchaseId(Long.valueOf(o[9].toString()));
            return sellerListResponse;
        }).collect(Collectors.groupingBy(SellerListResponse::getSeller));

        for(Map.Entry<String, List<SellerListResponse>> items: result.entrySet()){
            PurchaseListDto purchaseListDto=new PurchaseListDto();
            purchaseListDto.setEmail(items.getKey());
            Long price = 0L;
            Map<Language, String> names = new HashMap<>();
            for(SellerListResponse listItem: items.getValue()){
                names.put(Language.EN, listItem.getQuantity() + listItem.getUnit() + listItem.getPurchaseNameEn());
                names.put(Language.RU, listItem.getQuantity() + listItem.getUnit() + listItem.getPurchaseNameRu());
                names.put(Language.KK, listItem.getQuantity() + listItem.getUnit() + listItem.getPurchaseNameKz());
                price+=listItem.getPrice();
                purchaseListDto.setCostumerPhone(listItem.getCostumerPhone());
                purchaseListDto.setPurchaseDate(listItem.getPurchaseDate());
            }
            purchaseListDto.setPrice(price);
            purchaseListDto.setPurchaseName(names);
            purchaseListResult.add(purchaseListDto);
        }
        return purchaseListResult;
    }

    public void changePurchaseStatus(Long purchaseId, PurchaseStatus purchaseStatus) throws Exception {
        PurchaseEntity purchase = purchaseRepository.findById(purchaseId).orElseThrow(() -> new Exception("No such purchase"));
        RecordEntity record = recordRepository.findById(purchase.getRecordId()).get();
        if(!record.getUserId().equals(SecurityUtils.getCurrentId())){
            throw new Exception("You are not owner of the item");
        }
        if(purchaseStatus.equals(PurchaseStatus.REJECTED)){
            record.setQuantity(record.getQuantity()+purchase.getQuantity());
            recordRepository.save(record);
            purchase.setPurchaseStatus(PurchaseStatus.REJECTED);
            purchaseRepository.save(purchase);
        }else if(purchaseStatus.equals(PurchaseStatus.CONFIRMED)){
            purchase.setPurchaseStatus(PurchaseStatus.CONFIRMED);
            purchaseRepository.save(purchase);
        }
    }
}
