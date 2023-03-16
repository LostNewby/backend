package com.naturalgoods.backend.bucket;

public enum BucketName {
    POSTPICTURE("aulfood-image");

    private final String bucketName;


    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
