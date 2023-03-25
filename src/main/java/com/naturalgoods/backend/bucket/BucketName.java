package com.naturalgoods.backend.bucket;

public enum BucketName {
    IMAGE_STORAGE("natural-goods-images");

    private final String bucketName;


    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
