package maxima.ru.filemanager.api.aws;

import maxima.ru.filemanager.dto.FileS3;

import java.io.InputStream;

public interface S3FileRepository {

    String saveContent(String bucketName, String fileName, InputStream content);

    InputStream getContent(String bucketName, String fileName);
}

