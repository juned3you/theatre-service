package com.xyz.theatreService;

import com.xyz.theatreService.repository.CityRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase
@ActiveProfiles("test")
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=validate"
})
public class CityRepositoryTest {

    private static final String CITY_NAME = "Hyderabad";
    @Autowired
    private CityRepository cityRepository;

    //@Test
    void whenSaved_thenFindsByName() {
        Assertions.assertThat(cityRepository.findByName(CITY_NAME)).isNotNull();
    }
}
