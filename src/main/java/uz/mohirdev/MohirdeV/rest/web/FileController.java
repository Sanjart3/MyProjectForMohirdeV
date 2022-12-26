package uz.mohirdev.MohirdeV.rest.web;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileUrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.util.UriEncoder;
import uz.mohirdev.MohirdeV.Entity.FileStorage;
import uz.mohirdev.MohirdeV.service.FileService;

import java.net.MalformedURLException;

@RestController
@RequestMapping("/api")
public class FileController{

    private final FileService fileService;

    @Value("${upload.server.folder}")
    private String serverFolderPath;

    public FileController(FileService fileService) {

        this.fileService = fileService;
    }

    @PostMapping("/uploads")
    public ResponseEntity fileUpload(@RequestParam("file") MultipartFile multipartFile){
        FileStorage result = fileService.save(multipartFile);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/file-preview/{hashId}")
    public ResponseEntity preview(@PathVariable String hashId) throws MalformedURLException {
        FileStorage result = fileService.findByHashId(hashId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; fileName=\""+ UriEncoder.encode(result.getName()))
                .contentType(MediaType.parseMediaType(result.getContentType()))
                .contentLength(result.getFileSize())
                .body(new FileUrlResource(String.format("%s/%s",this.serverFolderPath, result.getUploadFolder())));
    }

    @GetMapping("/download/{hashId}")
    public ResponseEntity download(@PathVariable String hashId) throws MalformedURLException {
        FileStorage fileStorage = fileService.findByHashId(hashId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; fileName=\""+UriEncoder.encode(fileStorage.getName()))
                .contentType(MediaType.parseMediaType(fileStorage.getContentType()))
                .contentLength(fileStorage.getFileSize())
                .body(new FileUrlResource(String.format("%s/%s", this.serverFolderPath,fileStorage.getUploadFolder())));
    }

    // Delete Mapping

    @DeleteMapping("delete/{hashId}")
    public ResponseEntity delete(@PathVariable String hashId){
        fileService.deleteByHashId(hashId);
        return ResponseEntity.ok(" File is deleted");
    }
}
