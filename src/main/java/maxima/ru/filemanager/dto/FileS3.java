package maxima.ru.filemanager.dto;

import lombok.Builder;
import lombok.Value;

import java.io.File;

@Value
@Builder
public class FileS3 {
    String fileName;
    Long size;
}

