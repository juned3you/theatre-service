package com.xyz.theatreService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShowTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float ticketPrice;
    private Date showDate;

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;

    @OneToOne
    @JoinColumn(name = "screen_id", referencedColumnName = "id")
    private Screen screen;

    @OneToOne
    @JoinColumn(name = "timings_id", referencedColumnName = "id")
    private Timings timings;
}
