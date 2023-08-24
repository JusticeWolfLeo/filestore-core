package maxima.ru.filemanager.config.aws;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value = "maxima.repository.aws-s3.enabled", havingValue = "true")
public class AwsS3Config {

    @Value("${maxima.repository.aws-s3.credentials.access-key}")
    private String accessKey;

    @Value("${maxima.repository.aws-s3.credentials.secret-key}")
    private String secretKey;

    @Value("${maxima.repository.aws-s3.signing-region}")
    private String region;

    @Value("${maxima.repository.aws-s3.endpoint}")
    private String endpoint;

    @Bean
    public AmazonS3 amazonS3Client()  {
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        AwsClientBuilder.EndpointConfiguration endpointConfig =
                new AwsClientBuilder.EndpointConfiguration(endpoint, region);

        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withEndpointConfiguration(endpointConfig)
                .build();
    }
}
