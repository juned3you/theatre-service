package com.xyz.theatreService.entity;

import com.xyz.theatreService.enums.TheatreStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Theatre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long ownerId;
    private String name;
    private short numberOfScreens;

    @ManyToOne
    @JoinColumn(name = "cityId", referencedColumnName = "id")
    private City city;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TheatreStatus status;

    @Version
    private Long version;

    @OneToMany(mappedBy = "theatre")
    private Set<Screen> screens;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Theatre theatre = (Theatre) o;
        return Objects.equals(id, theatre.id) && Objects.equals(name, theatre.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
