package com.naturalgoods.backend.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfig {

    @Bean
    public AmazonS3 s3() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(
                "AKIA36EJSAFMO72ULCVF",
                "MenFwRqJqnJA4ahqNYKPOPMh+JCX7d+LeGPlteDY"
        );

        return AmazonS3ClientBuilder.standard()
                // To avoid SDKClientException-Unable to find a region via the region provider chain
                // https://stackoverflow.com/a/45081648/13673510
                .withRegion(Regions.EU_CENTRAL_1)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}
