package com.richproject.content;


import com.richproject.account.Account;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Content {

    @Id @GeneratedValue
    private Long id;
    private String title;
    @JoinColumn
    @ManyToOne
    private Account author;
    private String content;
    private String imageUrl;
    private String linkUrl;
    private LocalDateTime enrollArticleTime;
    @Enumerated(EnumType.STRING)
    private ContentCategory category;

    @ManyToOne
    private Comment comment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Content content = (Content) o;

        return Objects.equals(id, content.id);
    }

    @Override
    public int hashCode() {
        return 135805491;
    }
}
