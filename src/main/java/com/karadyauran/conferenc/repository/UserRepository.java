package com.karadyauran.conferenc.repository;

import com.karadyauran.conferenc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>
{
    @Query("SELECT u FROM User u WHERE u.username = :username")
    Optional<User> findUserByUsername(String username);

    boolean existsByUsername(String username);


    @Modifying
    @Query("update User u set u.username = :username where u.id = :id")
    void changeUsername(UUID id, String username);
}
