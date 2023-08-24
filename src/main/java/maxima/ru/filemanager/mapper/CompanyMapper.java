package maxima.ru.filemanager.mapper;

import maxima.ru.filemanager.dto.CompanyDto;
import maxima.ru.filemanager.model.Company;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyDto toDTO(Company model);
    Company toModel(CompanyDto dto);
}
