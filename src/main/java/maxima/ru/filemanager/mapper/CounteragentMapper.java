package maxima.ru.filemanager.mapper;

import maxima.ru.filemanager.dto.CounteragentDto;
import maxima.ru.filemanager.dto.PersonDto;
import maxima.ru.filemanager.model.Counteragent;
import maxima.ru.filemanager.model.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CounteragentMapper {
    CounteragentDto toDTO(Counteragent model);
    Counteragent toModel(CounteragentDto dto);
}
