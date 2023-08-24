package maxima.ru.filemanager.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PersonDto {
    private String fullName;
    private String role;

}
