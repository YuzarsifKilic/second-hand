package com.example.secondhand.bucket;

public enum BucketName {

    PROFILE_IMAGE("yuzarsif-demo");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
