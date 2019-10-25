package com.spring.gameloft.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;

@ToString
@Data
@NoArgsConstructor
@Setter
@AllArgsConstructor
@JsonDeserialize
@JsonSerialize
public class AlbumSinger {
    private Album album;
    private Singer singer;
}
