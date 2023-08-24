package maxima.ru.filemanager.mapper;

import maxima.ru.filemanager.dto.PersonDto;
import maxima.ru.filemanager.model.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    PersonDto toDTO(Person model);
    Person toModel(PersonDto dto);
}
