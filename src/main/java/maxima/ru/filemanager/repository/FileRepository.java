package maxima.ru.filemanager.repository;

import maxima.ru.filemanager.model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface FileRepository extends CrudRepository<File, Long> {

    File findByTitle(String title);
    File findByDescribeContaining(String describe);
    File findByCounteragent(Long id);
    List<File> findByDestiny(String destiny);
    List<File> findByType (String type);
}

