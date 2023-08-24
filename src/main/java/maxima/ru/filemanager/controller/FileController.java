package maxima.ru.filemanager.controller;

import maxima.ru.filemanager.model.File;
import maxima.ru.filemanager.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileRepository fileRepository;

    @GetMapping("/{id}")
    public ResponseEntity<File> getFileById(@PathVariable Long id) {
        Optional<File> file = fileRepository.findById(id);
        return file.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<File> createFile(@RequestBody File file) {
        File createdFile = fileRepository.save(file);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFile);
    }

    @PutMapping
    public ResponseEntity<File> updateFile(@RequestBody File file) {
        Optional<File> existingFile = fileRepository.findById(file.getId());
        if (existingFile.isPresent()) {
            File file1 = existingFile.get();
            file1.setTitle(file.getTitle());
            file1.setDescribe(file.getDescribe());
            file1.setAuthor(file.getAuthor());
            file1.setType(file.getType());
            file1.setDestiny(file.getDestiny());
            fileRepository.save(file1);
            return ResponseEntity.ok(file1);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFile(@PathVariable Long id) {
        Optional<File> existingFile = fileRepository.findById(id);
        if (existingFile.isPresent()) {
            fileRepository.delete(existingFile.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
