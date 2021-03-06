package com.szabicycle.szabicycle.bucket;

public enum BucketName {

    PROFILE_IMAGE("szabicycle-bucket");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
