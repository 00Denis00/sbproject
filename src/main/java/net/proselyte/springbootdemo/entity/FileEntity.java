package net.proselyte.springbootdemo.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "files")
public class FileEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
}
