package com.naturalgoods.backend.record;

import com.naturalgoods.backend.api.ApiDataResponse;
import com.naturalgoods.backend.dto.FilterDto;
import com.naturalgoods.backend.dto.ProductCardsDto;
import com.naturalgoods.backend.dto.RecordAddDto;
import com.naturalgoods.backend.image.ImageService;
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

    @GetMapping("/filter")
    public ResponseEntity<ApiDataResponse<Page<ProductCardsDto>>> filter(@RequestBody FilterDto filter,
                                                                         @RequestParam Integer page,
                                                                         @RequestParam Integer pageSize) {

        return ResponseEntity.ok(new ApiDataResponse<>(recordService.filter(filter, page, pageSize)));
    }

    @PostMapping(
            path = "add",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void addRecord(@RequestBody RecordAddDto recordAddDto,
                            @RequestParam MultipartFile file) {
        recordAddDto.setPhoto(file);
        recordService.recordAdd(recordAddDto);
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
