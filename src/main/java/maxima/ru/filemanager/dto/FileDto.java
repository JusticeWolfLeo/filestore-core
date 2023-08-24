package maxima.ru.filemanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileDto {
    private Long id;
    private String title;
    private String describe;
    private String type;
    private String destiny;
    private CounteragentDto counteragentDto;
}

