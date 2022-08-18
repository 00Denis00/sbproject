package net.proselyte.springbootdemo.service;

import lombok.RequiredArgsConstructor;
import net.proselyte.springbootdemo.entity.HistoryEntity;
import net.proselyte.springbootdemo.repository.HistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class HistoryService
{
    private HistoryRepository historyRepository;

    @Transactional
    public HistoryEntity save(HistoryEntity historyEntity)
    {
        return historyRepository.saveAndFlush(historyEntity);
    }

    @Transactional
    public void delete(Integer id)
    {
        historyRepository.deleteById(id);
    }

    public List<HistoryEntity> getAll()
    {
        return historyRepository.findAll();
    }

    public HistoryEntity getById(Integer id)
    {
        return historyRepository.getById(id);
    }
}
