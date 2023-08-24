package maxima.ru.filemanager.impl.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import maxima.ru.filemanager.api.service.CompanyService;
import maxima.ru.filemanager.dto.CompanyDto;
import maxima.ru.filemanager.exception.ErrorCode;
import maxima.ru.filemanager.exception.RepositoryException;
import maxima.ru.filemanager.mapper.CompanyMapper;
import maxima.ru.filemanager.model.Company;
import maxima.ru.filemanager.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompanyServiceImpl implements CompanyService {
  private final CompanyRepository companyRepository;
  private final CompanyMapper companyMapper;
  @Override
  public List<CompanyDto> findAll() {
    List<Company> all = companyRepository.findAll();
    return all.stream()
        .map(companyMapper::toDTO)
        .collect(Collectors.toList());
  }

  @Override
  public CompanyDto findById(Long id) {
    return companyRepository.findById(id)
        .map(companyMapper::toDTO)
        .orElseThrow(() -> RepositoryException.builder()
            .errorCode(ErrorCode.NOT_FOUND)
            .message("Не удалось найти нужнеую компанию по id: " + id)
            .build());
  }
}
