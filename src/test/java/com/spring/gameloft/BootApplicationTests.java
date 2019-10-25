package com.spring.gameloft;

import com.spring.gameloft.domain.Singer;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class BootApplicationTests {

    @Autowired
    TestRestTemplate testRestTemplate;

    private String url = "http://localhost:8000/singers";

    @Test
    void testCreateSinger() {
        Singer singer = new Singer();
//        singer.setId(10L);
        singer.setFirstName("Nicu");
        singer.setLastName("Zdreanta");
        singer.setBirthDate(LocalDate.of(2001, 1, 1));
        Singer singerResponse = testRestTemplate
                .withBasicAuth("admin", "admin")
                .postForObject(url, singer, Singer.class);
        assertThat(singerResponse.getId()).isNotNull();
        assertThat(singerResponse.getFirstName()).isEqualTo(singer.getFirstName());
        assertThat(singerResponse.getLastName()).isEqualTo(singer.getLastName());
        assertThat(singerResponse.getBirthDate()).isEqualTo(singer.getBirthDate());
    }

}
