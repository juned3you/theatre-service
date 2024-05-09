CREATE TABLE city (
    id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(60)
);

CREATE TABLE theatre (
    id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    owner_id bigint NOT NULL,
    name varchar(150) NOT NULL,
    number_of_screens smallint NOT NULL,
    city_id bigint NOT NULL,
    status varchar(8) NOT NULL DEFAULT 'ACTIVE',
    version bigint NULL
);

CREATE TABLE screen (
    id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    theatre_id bigint NOT NULL,
    name varchar(150) NOT NULL,
    capacity smallint NOT NULL,
    version bigint NULL,
    FOREIGN KEY (theatre_id) REFERENCES theatre(id)
);

CREATE TABLE seats (
    id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    screen_id bigint NOT NULL,
    name varchar(150) NOT NULL,
    version bigint NULL,
    FOREIGN KEY (screen_id) REFERENCES screen(id)
);

CREATE TABLE timings (
    id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(150) NOT NULL,
    start_time time NOT NULL,
    end_time time NOT NULL
);

CREATE TABLE genre (
    id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(150) NOT NULL
);

CREATE TABLE languages (
    id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(150) NOT NULL
);

CREATE TABLE movie (
    id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    genre_id bigint NOT NULL,
    language_id bigint NOT NULL,
    name varchar(150) NOT NULL,
    description varchar(256) NOT NULL,
    release_date date NULL,
    duration varchar(50) NOT NULL,
    FOREIGN KEY (genre_id) REFERENCES genre(id),
    FOREIGN KEY (language_id) REFERENCES languages(id)
);

CREATE TABLE show_time (
    id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    movie_id bigint NOT NULL,
    screen_id bigint NOT NULL,
    timings_id bigint NOT NULL,
    show_date date NULL,
    ticket_price float NOT NULL,
    FOREIGN KEY (movie_id) REFERENCES movie(id),
    FOREIGN KEY (screen_id) REFERENCES screen(id),
    FOREIGN KEY (timings_id) REFERENCES timings(id)
);

CREATE TABLE bookings (
    id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id bigint NOT NULL,
    show_time_id bigint NOT NULL,
    date_time datetime NOT NULL,
    status varchar(50) NOT NULL,
    version bigint NULL,
    FOREIGN KEY (show_time_id) REFERENCES show_time(id)
);

CREATE TABLE bookings_seat (
    id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    booking_id bigint NOT NULL,
    seat_id bigint NOT NULL,
    FOREIGN KEY (booking_id) REFERENCES bookings(id),
    FOREIGN KEY (seat_id) REFERENCES seats(id)
);
