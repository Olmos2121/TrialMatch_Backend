package com.example.uade.tpo.entity;

import com.example.uade.tpo.entity.enumeration.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "investigators")
public class Investigator implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "investigator_seq")
    @SequenceGenerator(name = "investigator_seq", sequenceName = "investigator_seq", allocationSize = 1)
    Long id;
    @Column (nullable = false)
    String companyName;
    @Column (nullable = false)
    String cuit;
    @Column (nullable = false, unique = true)
    String email;
    @Column (nullable = false)
    String phone;
    @Column (nullable = false)
    String address;
    @Column (nullable = false)
    String password;
    @Enumerated(EnumType.STRING)
    Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
