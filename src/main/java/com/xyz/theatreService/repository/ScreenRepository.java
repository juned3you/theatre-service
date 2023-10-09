package com.xyz.theatreService.repository;

import com.xyz.theatreService.entity.Screen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScreenRepository extends JpaRepository<Screen, Long> {
}
