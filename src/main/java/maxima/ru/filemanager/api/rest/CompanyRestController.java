package maxima.ru.filemanager.api.rest;

import maxima.ru.filemanager.dto.CompanyDto;
import maxima.ru.filemanager.dto.FileS3;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public interface CompanyRestController {

  @GetMapping("/api/v1/company")
  @ResponseBody
  ResponseEntity<List<CompanyDto>> getCompony();

  @GetMapping("/api/v1/company/{id}")
  @ResponseBody
  ResponseEntity<CompanyDto> getComponyById(@PathVariable Long id);
}
