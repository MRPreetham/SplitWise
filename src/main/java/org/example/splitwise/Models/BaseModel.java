package org.example.splitwise.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @CreatedDate
    @Temporal(TemporalType.TIME)
    private Date CreatedAt;
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date UpdatedAt;
    private boolean isDeleted;
}
