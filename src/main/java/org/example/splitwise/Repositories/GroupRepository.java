package org.example.splitwise.Repositories;

import org.example.splitwise.Models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {
    boolean existsGroupById(Long Id);
}
