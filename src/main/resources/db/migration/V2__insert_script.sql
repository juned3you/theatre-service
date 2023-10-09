-- Cities
INSERT INTO city (id, name) values (1, 'Indore');
INSERT INTO city (id, name) values (2, 'Hyderabad');

-- Theatre 
INSERT INTO theatre (id, owner_id, name, number_of_screens, city_id, status) values (1, 1, 'INOX Nexus', 2,  1, 'ACTIVE');
INSERT INTO theatre (id, owner_id, name, number_of_screens, city_id, status) values (2, 1, 'PVR RK Cineplex', 2,  2, 'ACTIVE');

-- Screens 
INSERT INTO screen (id, theatre_id, name, capacity) values (1, 1, 'Multiplex-1', 4);
INSERT INTO screen (id, theatre_id, name, capacity) values (2, 1, 'Multiplex-2', 4);
INSERT INTO screen (id, theatre_id, name, capacity) values (3, 2, 'Screen-1', 4);
INSERT INTO screen (id, theatre_id, name, capacity) values (4, 2, 'Screen-2', 4);

-- Seats INOX Multiplex-1 Indore
INSERT INTO seats (id, name, screen_id) values (1, 'MTX1_A1', 1);
INSERT INTO seats (id, name, screen_id) values (2, 'MTX1_A2', 1);
INSERT INTO seats (id, name, screen_id) values (3, 'MTX1_B1', 1);
INSERT INTO seats (id, name, screen_id) values (4, 'MTX1_B2', 1);

-- Seats INOX Multiplex-2 Indore
INSERT INTO seats (id, name, screen_id) values (5, 'MTX2_A1', 2);
INSERT INTO seats (id, name, screen_id) values (6, 'MTX2_A2', 2);
INSERT INTO seats (id, name, screen_id) values (7, 'MTX2_B1', 2);
INSERT INTO seats (id, name, screen_id) values (8, 'MTX2_B2', 2);

-- Seats PVR Screen-1 Hyderabad
INSERT INTO seats (id, name, screen_id) values (9, 'SCR1_A1', 3);
INSERT INTO seats (id, name, screen_id) values (10, 'SCR1_A2', 3);
INSERT INTO seats (id, name, screen_id) values (11, 'SCR1_B1', 3);
INSERT INTO seats (id, name, screen_id) values (12, 'SCR1_B2', 3);

-- Seats PVR Screen-2 Hyderabad
INSERT INTO seats (id, name, screen_id) values (13, 'SCR2_A1', 4);
INSERT INTO seats (id, name, screen_id) values (14, 'SCR2_A2', 4);
INSERT INTO seats (id, name, screen_id) values (15, 'SCR2_B1', 4);
INSERT INTO seats (id, name, screen_id) values (16, 'SCR2_B2', 4);

-- Timings 
INSERT INTO timings (id, name, start_time, end_time) values (1, 'Morning', '09:00:00', '11:45:00');
INSERT INTO timings (id, name, start_time, end_time) values (2, 'Matinee', '14:00:00', '16:45:00');
INSERT INTO timings (id, name, start_time, end_time) values (3, 'Evening', '18:00:00', '20:45:00');
INSERT INTO timings (id, name, start_time, end_time) values (4, 'Night', '21:00:00', '23:45:00');

-- Genres 
INSERT INTO genre (id, name) values (1, 'Action');
INSERT INTO genre (id, name) values (2, 'Comedy');
INSERT INTO genre (id, name) values (3, 'Romantic');
INSERT INTO genre (id, name) values (4, 'Horror');
INSERT INTO genre (id, name) values (5, 'Thriller');
INSERT INTO genre (id, name) values (6, 'Suspense');

-- Languages 
INSERT INTO languages (id, name) values (1, 'Hindi');
INSERT INTO languages (id, name) values (2, 'English');
INSERT INTO languages (id, name) values (3, 'Telugu');
INSERT INTO languages (id, name) values (4, 'Russian');

-- Movies 
INSERT INTO movie (id, name, description, release_date, duration, genre_id, language_id) values (1, 'The Karate Kid', 'English Version Cast by Jackie Chan & Jaden Smith', '2010-09-01', '2 hours 45 minutes',1, 2);
INSERT INTO movie (id, name, description, release_date, duration, genre_id, language_id) values (2, 'The Karate Kid', 'Telugu Version Cast by Jackie Chan & Jaden Smith', '2010-09-01', '2 hours 45 minutes',1, 3);
INSERT INTO movie (id, name, description, release_date, duration, genre_id, language_id) values (3, 'Boyka Undisputed', 'Scott Adkins, Teodora Duhovnikova', '2002-01-15', '2 hours 05 minutes',1, 4);
INSERT INTO movie (id, name, description, release_date, duration, genre_id, language_id) values (4, 'Jawan', 'Shahrukh Khan, Nayanthara', '2023-08-15', '2 hours 25 minutes',5, 1);
INSERT INTO movie (id, name, description, release_date, duration, genre_id, language_id) values (5, 'Mission Impossible 7', 'Tom Cruise, Hayley Atwell', '2023-05-09', '2 hours 05 minutes',1, 2);

-- Show Times Date 2023-10-15
-- Indore Inox - Multipex-1 KarateKid English at all timings
INSERT INTO show_time (id, movie_id, screen_id, timings_id, show_date, ticket_price) values (1, 1, 1, 1, '2023-10-15', 515.23);
INSERT INTO show_time (id, movie_id, screen_id, timings_id, show_date, ticket_price) values (2, 1, 1, 2, '2023-10-15', 515.23);
INSERT INTO show_time (id, movie_id, screen_id, timings_id, show_date, ticket_price) values (3, 1, 1, 3, '2023-10-15', 515.23);
INSERT INTO show_time (id, movie_id, screen_id, timings_id, show_date, ticket_price) values (4, 1, 1, 4, '2023-10-15', 515.23);

-- Indore Inox - Multipex-2 Jawan Hindi at all timings
INSERT INTO show_time (id, movie_id, screen_id, timings_id, show_date, ticket_price) values (5, 4, 2, 1, '2023-10-15', 414.02);
INSERT INTO show_time (id, movie_id, screen_id, timings_id, show_date, ticket_price) values (6, 4, 2, 2, '2023-10-15', 414.02);
INSERT INTO show_time (id, movie_id, screen_id, timings_id, show_date, ticket_price) values (7, 4, 2, 3, '2023-10-15', 414.02);
INSERT INTO show_time (id, movie_id, screen_id, timings_id, show_date, ticket_price) values (8, 4, 2, 4, '2023-10-15', 414.02);

-- Hyderabad PVR - Screen-1 Mission Impossible English at all timings
INSERT INTO show_time (id, movie_id, screen_id, timings_id, show_date, ticket_price) values (9, 5, 3, 1, '2023-10-15', 1002.16);
INSERT INTO show_time (id, movie_id, screen_id, timings_id, show_date, ticket_price) values (10, 5, 3, 2, '2023-10-15', 1002.16);
INSERT INTO show_time (id, movie_id, screen_id, timings_id, show_date, ticket_price) values (11, 5, 3, 3, '2023-10-15', 1002.16);
INSERT INTO show_time (id, movie_id, screen_id, timings_id, show_date, ticket_price) values (12, 5, 3, 4, '2023-10-15', 1002.16);

-- Hyderabad PVR - Screen-2 KarateKid Telugu at all timings
INSERT INTO show_time (id, movie_id, screen_id, timings_id, show_date, ticket_price) values (13, 2, 4, 1, '2023-10-15', 316.00);
INSERT INTO show_time (id, movie_id, screen_id, timings_id, show_date, ticket_price) values (14, 2, 4, 2, '2023-10-15', 316.00);
INSERT INTO show_time (id, movie_id, screen_id, timings_id, show_date, ticket_price) values (15, 2, 4, 3, '2023-10-15', 316.00);
INSERT INTO show_time (id, movie_id, screen_id, timings_id, show_date, ticket_price) values (16, 2, 4, 4, '2023-10-15', 316.00);

-- Show Times Date 2023-10-16
-- Indore Inox - Multipex-1 KarateKid English at all timings
INSERT INTO show_time (id, movie_id, screen_id, timings_id, show_date, ticket_price) values (17, 5, 1, 1, '2023-10-16', 515.23);
INSERT INTO show_time (id, movie_id, screen_id, timings_id, show_date, ticket_price) values (18, 5, 1, 2, '2023-10-16', 515.23);
INSERT INTO show_time (id, movie_id, screen_id, timings_id, show_date, ticket_price) values (19, 5, 1, 3, '2023-10-16', 515.23);
INSERT INTO show_time (id, movie_id, screen_id, timings_id, show_date, ticket_price) values (20, 5, 1, 4, '2023-10-16', 515.23);

-- Indore Inox - Multipex-2 KarateKid Telugu at all timings
INSERT INTO show_time (id, movie_id, screen_id, timings_id, show_date, ticket_price) values (21, 2, 2, 1, '2023-10-16', 414.02);
INSERT INTO show_time (id, movie_id, screen_id, timings_id, show_date, ticket_price) values (22, 2, 2, 2, '2023-10-16', 414.02);
INSERT INTO show_time (id, movie_id, screen_id, timings_id, show_date, ticket_price) values (23, 2, 2, 3, '2023-10-16', 414.02);
INSERT INTO show_time (id, movie_id, screen_id, timings_id, show_date, ticket_price) values (24, 2, 2, 4, '2023-10-16', 414.02);

-- Hyderabad PVR - Screen-1 Mission Impossible English at all timings
INSERT INTO show_time (id, movie_id, screen_id, timings_id, show_date, ticket_price) values (25, 1, 3, 1, '2023-10-16', 1002.16);
INSERT INTO show_time (id, movie_id, screen_id, timings_id, show_date, ticket_price) values (26, 1, 3, 2, '2023-10-16', 1002.16);
INSERT INTO show_time (id, movie_id, screen_id, timings_id, show_date, ticket_price) values (27, 1, 3, 3, '2023-10-16', 1002.16);
INSERT INTO show_time (id, movie_id, screen_id, timings_id, show_date, ticket_price) values (28, 1, 3, 4, '2023-10-16', 1002.16);

-- Hyderabad PVR - Screen-2 Jawan Hindi at all timings
INSERT INTO show_time (id, movie_id, screen_id, timings_id, show_date, ticket_price) values (29, 4, 4, 1, '2023-10-16', 316.00);
INSERT INTO show_time (id, movie_id, screen_id, timings_id, show_date, ticket_price) values (30, 4, 4, 2, '2023-10-16', 316.00);
INSERT INTO show_time (id, movie_id, screen_id, timings_id, show_date, ticket_price) values (31, 4, 4, 3, '2023-10-16', 316.00);
INSERT INTO show_time (id, movie_id, screen_id, timings_id, show_date, ticket_price) values (32, 4, 4, 4, '2023-10-16', 316.00);


