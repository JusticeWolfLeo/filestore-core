package maxima.ru.filemanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum S3BucketName {
    DEFAULT_BUCKET("maxima-filestore");

    private final String bucketName;
}
