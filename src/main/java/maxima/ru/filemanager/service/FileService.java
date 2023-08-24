package maxima.ru.filemanager.service;

import lombok.RequiredArgsConstructor;
import maxima.ru.filemanager.model.File;
import maxima.ru.filemanager.repository.FileRepository;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class FileService {
    private final FileRepository fileRepository;
    public File findByTitle(String title) {
        return fileRepository.findByTitle(title);
    }
    public File save(File file) {
        return fileRepository.save(file);
    };
}
