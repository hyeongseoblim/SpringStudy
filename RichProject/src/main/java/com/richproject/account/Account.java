package com.richproject.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {
    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String username;
    private String password;
    private String sex;
    private LocalDateTime birth;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<AccountRole> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Account account = (Account) o;

        return Objects.equals(id, account.id);
    }

    @Override
    public int hashCode() {
        return 2083479647;
    }
}
