package com.naturalgoods.backend.controller;

import com.naturalgoods.backend.api.*;
import com.naturalgoods.backend.auth.Language;
import com.naturalgoods.backend.dto.FilterDto;
import com.naturalgoods.backend.dto.ProductCardsDto;
import com.naturalgoods.backend.dto.RecordAddDto;
import com.naturalgoods.backend.dto.RecordDto;
import com.naturalgoods.backend.image.ImageService;
import com.naturalgoods.backend.record.RecordService;
import com.naturalgoods.backend.record.RecordStatus;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;

@RestController
@RequestMapping(path = "api/record")
@AllArgsConstructor
public class RecordController {
    private final RecordService recordService;
    private final ImageService imageService;

    @PostMapping("/filter")
    public ResponseEntity<ApiDataResponse<Page<ProductCardsDto>>> filter(@RequestBody FilterDto filter,
                                                                         @RequestParam Integer page,
                                                                         @RequestParam Integer pageSize,
                                                                         @RequestParam Language lang) {

        return ResponseEntity.ok(new ApiDataResponse<>(recordService.filter(filter, page, pageSize, lang)));
    }

    @GetMapping("/getByStatus")
    public ResponseEntity<ApiListResponse<RecordDto>> getByStatus(@RequestParam RecordStatus status){
        return ResponseEntity.ok(new ApiListResponse<>(recordService.getItemList(status)));
    }

    @PostMapping(
            path = "add",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void addRecord(@RequestPart RecordAddDto recordAddDto,
                            @RequestPart MultipartFile file) {
        recordAddDto.setPhoto(file);
        recordService.recordAdd(recordAddDto);
    }

    @PostMapping(
            path = "edit",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void editRecord(@RequestPart RecordAddDto recordAddDto,
                          @RequestPart(required = false) MultipartFile file) {
        recordAddDto.setPhoto(file);
        recordService.recordEdit(recordAddDto);
    }

    @PostMapping("/delete")
    public ResponseEntity<ApiResponse> delete(@RequestParam Long recordId) {
        try {
            recordService.deleteRecord(recordId);
            return ResponseEntity.ok(ApiEmptyResponse.create());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiErrorResponse.create(e.getMessage()));
        }
    }

    @PostMapping(
            path = "{recordId}/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void uploadImage(@PathVariable("recordId") Long recordId,
                            @RequestParam("file") MultipartFile file) {
        imageService.upload(recordId, file);
    }

    @GetMapping(path = "{recordId}/image/link")
    public String getDownloadLink(@PathVariable("recordId") Long recordId) {
        return imageService.getDownloadLink(recordId);
    }
}
