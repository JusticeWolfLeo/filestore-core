package maxima.ru.filemanager.repository;

import maxima.ru.filemanager.model.Company;
import maxima.ru.filemanager.model.Counteragent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}

