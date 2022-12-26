package uz.mohirdev.MohirdeV.service;

import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.mohirdev.MohirdeV.Entity.FileStorage;
import uz.mohirdev.MohirdeV.Entity.enummration.FileStorageStatus;
import uz.mohirdev.MohirdeV.repository.FileRepository;

import java.io.File;
import java.io.IOException;
import java.util.Date;


@Service
public class FileService {

    private final FileRepository fileRepository;


    @Value("${upload.server.folder}")
    private String serverFolderPath;

    private final Hashids hashids;

    public FileService(FileRepository fileRepository) {

        this.fileRepository = fileRepository;
        this.hashids = new Hashids(getClass().getName(), 6);
    }

    public FileStorage save(MultipartFile multipartFile){

        FileStorage fileStorage = new FileStorage();
        fileStorage.setName(multipartFile.getOriginalFilename());
        fileStorage.setFileSize(multipartFile.getSize());
        fileStorage.setExtension(getExtension(multipartFile.getOriginalFilename()));
        fileStorage.setContentType(multipartFile.getContentType());
        fileStorage.setFileStorageStatus(FileStorageStatus.DRAFT);

        fileStorage = fileRepository.save(fileStorage);

        //  /serverFolderPath/upload_folder/2022/02/20/hashsdsws.pdf

        Date now = new Date();

//        File uploadFolder = new File(this.serverFolderPath+"/upload_files"+
//                1900+now.getYear()+"/"+1+now.getMonth()+ "/"+1+now.getDate());

        String path = String.format("%s/upload_files/%d/%d/%d", this.serverFolderPath, 1900+now.getYear(), 1+now.getMonth(), now.getDate());

        File uploadFolder = new File(path);

        if (!uploadFolder.exists()&&uploadFolder.mkdirs()){
            System.out.println("Folder created!");
        }

        fileStorage.setHashId(hashids.encode(fileStorage.getId()));
        String pathServer = String.format("upload_files/%d/%d/%d/%s.%s",
                1900+now.getYear(), 1+now.getMonth(), now.getDate(),
                fileStorage.getHashId(),
                fileStorage.getExtension());
        fileStorage.setUploadFolder(pathServer);


        uploadFolder = uploadFolder.getAbsoluteFile();
        fileStorage = fileRepository.save(fileStorage);
        File file = new File(uploadFolder, String.format("%s.%s",
                fileStorage.getHashId(), fileStorage.getExtension()));

        try{
            multipartFile.transferTo(file);
        }catch(Exception e){
            e.printStackTrace();
        }
        return fileStorage;

    }

    private String getExtension(String fileName){
        String ext = null;
        if (fileName != null && !fileName.isEmpty()){
            int dot = fileName.lastIndexOf('.');
            if (dot>0 && dot <= fileName.length()-2){
                ext = fileName.substring(dot+1);
            }
        }
        return ext;
    }

    // for get method
    public FileStorage findByHashId(String hashId){
        FileStorage fileStorage = fileRepository.findByHashId(hashId);
        return fileStorage;
    }

    // delete method
    public void deleteByHashId(String hashId){
        FileStorage fileStorage = findByHashId(hashId);
        File file = new File(String.format("%s/%s", this.serverFolderPath,fileStorage.getUploadFolder()));
        if (file.delete()){
            fileRepository.delete(fileStorage);
        }
    }

}
