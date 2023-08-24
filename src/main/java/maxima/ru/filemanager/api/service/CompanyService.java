package maxima.ru.filemanager.api.service;

import maxima.ru.filemanager.dto.CompanyDto;

import java.util.List;

public interface CompanyService {

  List<CompanyDto> findAll();

  CompanyDto findById(Long id);
}
