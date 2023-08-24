package maxima.ru.filemanager.impl.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import maxima.ru.filemanager.api.aws.S3FileRepository;
import maxima.ru.filemanager.exception.ErrorCode;
import maxima.ru.filemanager.exception.RepositoryException;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@Slf4j
public class S3FileRepositoryImpl implements S3FileRepository {
  private final AmazonS3 amazonS3Client;

  @Override
  public String saveContent(String bucketName, String fileName, InputStream content) {
    try {
      if (amazonS3Client.doesBucketExist(bucketName)) {
        String unigueFileName = generateFileName(fileName);
        amazonS3Client.putObject(bucketName, unigueFileName, content, createObjectMetadata());
        return unigueFileName;
      } else {
        throw RepositoryException.builder()
            .errorCode(ErrorCode.BUCKET_NOT_FOUND)
            .message("Bucket: " + bucketName + " not exist in Amazon S3")
            .build();
      }
    } catch (RepositoryException ex) {
      log.error("Failed to save file: {}. Bucket: {} not exist in Amazon S3", fileName, bucketName);
      throw ex;
    } catch (Exception e) {
      log.error("Connection to AWS S3 was failed", e);
      throw RepositoryException.builder()
          .errorCode(ErrorCode.SERVICE_UNAVAILABLE)
          .message(e.getMessage())
          .build();
    }
  }

  @Override
  public InputStream getContent(String bucketName, String fileName) {
    try {
      return amazonS3Client.getObject(bucketName, fileName).getObjectContent();
    } catch (Exception e) {
      log.error("Connection to AWS S3 was failed", e);
      throw RepositoryException.builder()
          .errorCode(ErrorCode.SERVICE_UNAVAILABLE)
          .message(e.getMessage())
          .build();
    }
  }

  private ObjectMetadata createObjectMetadata() {
    return new ObjectMetadata();
  }

  private String generateFileName(String fileName) {
    return UUID.randomUUID() + "-" + fileName;
  }
}

