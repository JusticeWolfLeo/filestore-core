package maxima.ru.filemanager.mapper;

import maxima.ru.filemanager.dto.FileDto;
import maxima.ru.filemanager.model.File;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CompanyMapper.class, PersonMapper.class})
public interface FileMapper {
    FileDto toDTO(File model);
    File toModel(FileDto dto);
}
