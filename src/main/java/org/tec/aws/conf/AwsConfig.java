package org.tec.aws.conf;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfig {

    //TODO need to be externalized and profile driven
    private String endPoint = "http://localhost:4566";
    private String region = "us-west-2";

    @Bean
    public AmazonS3 s3Client(
            final AwsClientBuilder.EndpointConfiguration endPointConfig,
            final AWSCredentialsProvider credentialsProvider
    ) {
        return AmazonS3ClientBuilder.standard()
                .withCredentials(credentialsProvider)
                .withEndpointConfiguration(endPointConfig)
                .build();
    }

    @Bean
    public AmazonSQS sqsClient(
                    final AwsClientBuilder.EndpointConfiguration endPointConfig,
                    final AWSCredentialsProvider credentialsProvider
            ) {

        return AmazonSQSClientBuilder.standard()
                .withCredentials(credentialsProvider)
                .withEndpointConfiguration(endPointConfig)
                .build();
    }

    @Bean
    public AwsClientBuilder.EndpointConfiguration endpointConfiguration() {
        return new AwsClientBuilder.EndpointConfiguration(endPoint,region);
    }

    //TODO localstack centric
    @Bean
    public AWSCredentialsProvider credentialsProvider() {
        return new AWSStaticCredentialsProvider(
                new BasicAWSCredentials("key", "secret")
        );
    }
}