package net.proselyte.springbootdemo.controller;

import net.proselyte.springbootdemo.config.StorageConfig;
import net.proselyte.springbootdemo.entity.FileEntity;
import net.proselyte.springbootdemo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/files")
public class FileController
{
    private FileService fileService;

    @Autowired
    public void setFileService(FileService fileService)
    {
        this.fileService = fileService;
    }

    private String bucketName = "sprexamplebucket08";



    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<FileEntity>> getAllFiles()
    {
        List<FileEntity> files = fileService.getAll();

        if(files.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else
        {
            ResponseEntity<List<FileEntity>> result = new ResponseEntity(files, HttpStatus.OK);
            return result;
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<FileEntity> getFile(@PathVariable("id") Integer id)
    {
        FileEntity fileEntity = fileService.getById(id);

        if(fileEntity == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else
        {
            ResponseEntity<FileEntity> result = new ResponseEntity(fileEntity, HttpStatus.OK);
            return result;
        }
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<FileEntity> createFile(File file) throws IOException
    {
        AmazonS3 s3Commander = initCommander();

        PutObjectRequest request = new PutObjectRequest(bucketName, file.getName(), file);
        s3Commander.putObject(request);

        FileEntity fileEntity = new FileEntity();
        fileEntity.setName(file.getName());
        fileService.save(fileEntity);

        ResponseEntity<FileEntity> result = new ResponseEntity(fileEntity, HttpStatus.CREATED);
        return result;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<FileEntity> deleteFile(@PathVariable("id") Integer id)
    {
        AmazonS3 s3Commander = initCommander();

        FileEntity fileEntity = fileService.getById(id);
        String fileName = fileEntity.getName();
        s3Commander.deleteObject(bucketName, fileName);

        fileService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    private AmazonS3 initCommander()
    {
        StorageConfig storageConfig = new StorageConfig();
        return storageConfig.s3Client();
    }
}