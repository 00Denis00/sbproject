package net.proselyte.springbootdemo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "histories")
public class HistoryEntity
{
    @Id
    @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity userEntity;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<FileEntity> files;
}
