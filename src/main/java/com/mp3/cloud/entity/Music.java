package com.mp3.cloud.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String sharer;
    private String path;
    @ManyToOne
    private User user;
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date dataDate;

}
