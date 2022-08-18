package net.proselyte.springbootdemo.service;

import lombok.RequiredArgsConstructor;
import net.proselyte.springbootdemo.entity.FileEntity;
import net.proselyte.springbootdemo.repository.FileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class FileService
{
    private FileRepository fileRepository;

    @Transactional
    public FileEntity save(FileEntity fileEntity)
    {
        return fileRepository.saveAndFlush(fileEntity);
    }

    @Transactional
    public void delete(Integer id)
    {
        fileRepository.deleteById(id);
    }

    public List<FileEntity> getAll()
    {
        return fileRepository.findAll();
    }

    public FileEntity getById(Integer id)
    {
        return fileRepository.getById(id);
    }
}
