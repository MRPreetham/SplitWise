package org.example.splitwise.Repositories;

import org.example.splitwise.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findUserByPhoneNumber(String phoneNumber);
    User save(User user);
    boolean existsUserById(Long Id);
}
