package com.xyz.theatreService.repository;

import com.xyz.theatreService.entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheatreRepository extends JpaRepository<Theatre, Long> {
}
