package com.naturalgoods.backend.image;

import com.naturalgoods.backend.bucket.BucketName;
import com.naturalgoods.backend.filestore.FileStore;
import com.naturalgoods.backend.record.RecordRepository;
import com.naturalgoods.backend.record.RecordService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@Service
@AllArgsConstructor
public class ImageService {
    private final ImageRepository repository;
    private final FileStore fileStore;
    private final RecordRepository recordRepository;

    public void upload(Long recordId, MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file [ " + file.getSize() + " ]\n" +
                    "name: " + file.getName());
        }

        if (!Arrays.asList(
                IMAGE_JPEG.getMimeType(),
                IMAGE_PNG.getMimeType(),
                IMAGE_GIF.getMimeType()).contains(file.getContentType())) {
            throw new IllegalStateException("File must be an image [ " + file.getContentType() + " ]");
        }

        if (!recordRepository.existsById(recordId)) {
            throw new IllegalStateException(String.format("Record with id %s not found", recordId));
        }

        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        String path = String.format("%s/%s", BucketName.IMAGE_STORAGE.getBucketName(), recordId);

        System.out.println();

        String contentType = file.getContentType();

        String extension = contentType.substring(contentType.lastIndexOf("/") + 1);

        String fileName = String.format("%s.%s", UUID.randomUUID(), extension);

        try {
            fileStore.save(path, fileName, Optional.of(metadata), file.getInputStream());
            repository.save(new ImageEntity(recordId, fileName));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public String getDownloadLink(Long recordId) {
        String relative = repository.findById(recordId).map(ImageEntity::getLink).isEmpty()? "":repository.findById(recordId).map(ImageEntity::getLink).get();
        return relative.equals("")?"":String.format("https://%s.s3.amazonaws.com/%s/%s",
                BucketName.IMAGE_STORAGE.getBucketName(), recordId, relative);
    }
}
