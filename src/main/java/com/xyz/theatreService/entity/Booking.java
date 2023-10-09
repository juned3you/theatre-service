package com.xyz.theatreService.entity;

import com.xyz.theatreService.enums.BookingStatus;
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
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Date dateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BookingStatus status;

    @ManyToOne
    @JoinColumn(name = "show_time_id", referencedColumnName = "id")
    private ShowTime showTime;


    @OneToOne
    @JoinTable(
            name = "bookings_seat",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "seat_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"booking_id", "seat_id"})
    )
    private Seat seat;

    @Version
    private Long version;
}
