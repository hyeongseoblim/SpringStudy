package com.richproject.event;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Event {

    @Id @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private String location;
    private int basePrice;
    private int maxPrice;
    private boolean offline;
    private boolean free;
    private LocalDateTime beginEnrollDateTime;
    private LocalDateTime endEnrollDateTime;
    private LocalDateTime beginEventDateTime;
    private LocalDateTime endEventDateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Event event = (Event) o;

        return Objects.equals(id, event.id);
    }

    @Override
    public int hashCode() {
        return 1491041522;
    }
}
