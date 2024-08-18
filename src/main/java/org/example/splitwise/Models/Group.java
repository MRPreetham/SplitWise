package org.example.splitwise.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Group extends BaseModel{
    private String groupName;
    private String description;
    private Integer amount;
    @ManyToOne
    private User createdBy;
    @ManyToMany(mappedBy = "groupList")
    private List<User> members;
}
