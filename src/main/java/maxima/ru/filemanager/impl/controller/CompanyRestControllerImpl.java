package maxima.ru.filemanager.impl.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import maxima.ru.filemanager.api.rest.CompanyRestController;
import maxima.ru.filemanager.api.service.CompanyService;
import maxima.ru.filemanager.dto.CompanyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
public class CompanyRestControllerImpl implements CompanyRestController {
  private final CompanyService companyService;

  @Override
  public ResponseEntity<List<CompanyDto>> getCompony() {
    return ResponseEntity.ok(companyService.findAll());
  }

  @Override
  public ResponseEntity<CompanyDto> getComponyById(Long id) {
    return ResponseEntity.ok(companyService.findById(id));
  }
}
