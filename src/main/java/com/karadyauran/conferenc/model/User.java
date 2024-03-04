package com.karadyauran.conferenc.model;

import com.karadyauran.conferenc.model.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User
{
    @Id
    @UuidGenerator
    @Column(name = "id")
    UUID id;

    @Column(name = "username")
    String username;

    @Column(name = "email")
    String email;

    @Column(name = "email")
    String password_hash;

    @Column(name = "role")
    Role role;

    @OneToMany(mappedBy = "users")
    List<Booking> bookings;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, username);
    }

    @Override
    public String toString()
    {
        return username + " " + role;
    }
}
