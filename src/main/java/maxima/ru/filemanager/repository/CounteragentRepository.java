package maxima.ru.filemanager.repository;

import maxima.ru.filemanager.model.Counteragent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface CounteragentRepository extends CrudRepository<Counteragent, Long> {

    List<Counteragent> findByTypeCounteragent(String typeCounteragent);
}

