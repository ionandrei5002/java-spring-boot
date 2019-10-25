package com.spring.gameloft.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;

import java.time.LocalDate;

@ToString
@Data
@NoArgsConstructor
@Setter
@AllArgsConstructor
@JsonDeserialize
@JsonSerialize
public class Album {
    private Long id;
    private Long singerId;
    private String title;
    @JsonDeserialize(using= LocalDateDeserializer.class)
    @JsonSerialize(using= LocalDateSerializer.class)
    private LocalDate releaseDate;
}
