package maxima.ru.filemanager.api.rest.aws;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class FileView {
    String fileName;
    String fileDescribe;
    Long fileOwner;
}
