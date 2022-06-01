package com.richproject.content;


import com.richproject.account.Account;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Comment {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Account author;
    private String comment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Comment comment = (Comment) o;

        return Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return 860659860;
    }
}
