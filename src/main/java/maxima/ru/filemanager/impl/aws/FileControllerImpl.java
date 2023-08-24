package maxima.ru.filemanager.impl.aws;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import maxima.ru.filemanager.api.aws.S3FileRepository;
import maxima.ru.filemanager.api.rest.aws.FileController;
import maxima.ru.filemanager.api.rest.aws.FileView;
import maxima.ru.filemanager.dto.FileForm;
import maxima.ru.filemanager.dto.FileS3;
import maxima.ru.filemanager.dto.S3BucketName;
import maxima.ru.filemanager.model.File;
import maxima.ru.filemanager.service.FileService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.LocalDate;

@RequiredArgsConstructor
@Slf4j
@RestController
public class FileControllerImpl implements FileController {
    private final S3FileRepository S3fileRepository;
    private final FileService fileService;
    @Override
    @SneakyThrows
    public ResponseEntity<FileS3> getFileById(String fileName) {
        Resource file = getFile(fileName);
        return ResponseEntity.ok(FileS3.builder()
                .fileName(fileName)
                .size(file.contentLength())
                .build());
    }

    @Override
    public ResponseEntity<FileView> sendFile(MultipartFile multipartFile) {
        return ResponseEntity.ok(saveFile(multipartFile, FileForm.builder().build()));
    }

    @SneakyThrows
    private Resource getFile(String fileName) {
        try (InputStream content = S3fileRepository.getContent(S3BucketName.DEFAULT_BUCKET.getBucketName(), fileName)) {
            return new ByteArrayResource(content.readAllBytes());
        } catch (Exception e) {
            log.error("Error while get a file - {} Amazon s3 repository", fileName, e);
            throw new FileNotFoundException(fileName);
        }
    }


    @SneakyThrows
    private FileView saveFile(MultipartFile multipartFile, FileForm fileForm){
        String fileName = S3fileRepository.saveContent(
                S3BucketName.DEFAULT_BUCKET.getBucketName(),
                multipartFile.getOriginalFilename(),
                multipartFile.getInputStream());

        long userId = fileForm.getUserId();
        String description = fileForm.getDescription();

        File newFile = new File();
        newFile.setType(multipartFile.getContentType());
        newFile.setDescribe(description);
        newFile.setTitle(fileName);
        newFile.setAuthor(userId);

        fileService.save(newFile);

        log.debug("Success save file with name: {}", fileForm);
        return FileView.builder()
                .fileOwner(userId)
                .fileName(fileName)
                .fileDescribe(description)
                .build();
    }
}
