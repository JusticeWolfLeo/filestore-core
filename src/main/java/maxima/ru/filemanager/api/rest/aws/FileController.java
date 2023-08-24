package maxima.ru.filemanager.api.rest.aws;

import maxima.ru.filemanager.dto.FileForm;
import maxima.ru.filemanager.dto.FileS3;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

public interface FileController {


    @GetMapping("/api/v1/file/{fileName}")
    @ResponseBody
    ResponseEntity<FileS3> getFileById(@PathVariable String fileName);

    @PostMapping(value ="api/v1/file/send", consumes = {"multipart/form-data"})
    @ResponseBody
    ResponseEntity<FileView> sendFile(@RequestPart("file") MultipartFile multipartFile);
}
