package com.spring.gameloft.service;

import com.spring.gameloft.domain.Singer;

import java.util.List;

public interface SingerService {
    List<Singer> getAllSingers();

    Singer getSinger(Long id);
    Singer getSinger(String lastName);

    Singer create(Singer singer);

    Singer update(Long id, Singer singer);

    void delete(Long id);
}
