package net.proselyte.springbootdemo.repository;

import net.proselyte.springbootdemo.entity.HistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<HistoryEntity, Integer>
{

}
