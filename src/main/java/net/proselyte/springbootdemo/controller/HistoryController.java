package net.proselyte.springbootdemo.controller;

import net.proselyte.springbootdemo.entity.FileEntity;
import net.proselyte.springbootdemo.entity.HistoryEntity;
import net.proselyte.springbootdemo.entity.UserEntity;
import net.proselyte.springbootdemo.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/histories")
public class HistoryController
{
    private HistoryService historyService;

    @Autowired
    public void setHistoryService(HistoryService historyService)
    {
        this.historyService = historyService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<HistoryEntity>> getAllHistories()
    {
        List<HistoryEntity> histories = historyService.getAll();

        if(histories.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else
        {
            ResponseEntity<List<HistoryEntity>> result = new ResponseEntity(histories, HttpStatus.OK);
            return result;
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<HistoryEntity> getHistory(@PathVariable("id") Integer id)
    {
        HistoryEntity historyEntity = historyService.getById(id);

        if(historyEntity == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else
        {
            ResponseEntity<HistoryEntity> result = new ResponseEntity(historyEntity, HttpStatus.OK);
            return result;
        }
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<HistoryEntity> createHistory(HistoryEntity historyEntity)
    {
        int id = historyEntity.getId();

        UserEntity userEntity = new UserEntity();
        userEntity.setId(id);
        historyEntity.setUserEntity(userEntity);

        historyService.save(historyEntity);

        ResponseEntity<HistoryEntity> result = new ResponseEntity(historyEntity, HttpStatus.CREATED);
        return result;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<HistoryEntity> addFileToHistory(@PathVariable("id") Integer id, FileEntity fileEntity)
    {
        HistoryEntity historyEntity = historyService.getById(id);
        List<FileEntity> files = historyEntity.getFiles();
        files.add(fileEntity);
        historyEntity.setFiles(files);

        historyService.save(historyEntity);

        ResponseEntity<HistoryEntity> result = new ResponseEntity(historyEntity, HttpStatus.CREATED);
        return result;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<HistoryEntity> deleteHistory(@PathVariable("id") Integer id)
    {
        historyService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}