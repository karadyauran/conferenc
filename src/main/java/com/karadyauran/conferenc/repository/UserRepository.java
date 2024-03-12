package com.karadyauran.conferenc.repository;

import com.karadyauran.conferenc.model.User;
import com.karadyauran.conferenc.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>
{
    Optional<User> findUserByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    @Query("SELECT u.role FROM User u WHERE u.id = :id")
    Role getUserById(UUID id);

    @Modifying
    @Query("update User u set u.username = :username where u.id = :id")
    void changeUsername(UUID id, String username);

    @Modifying
    @Query("update User u set u.email = :email where u.id = :id")
    void changeEmail(UUID id, String email);
}
