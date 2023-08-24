package maxima.ru.filemanager.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class FileForm {
    Long userId = 0L;
    String description = "description";
}

