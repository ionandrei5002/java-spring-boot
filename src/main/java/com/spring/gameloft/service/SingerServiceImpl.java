package com.spring.gameloft.service;

import com.spring.gameloft.domain.Singer;
import com.spring.gameloft.repository.SingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SingerServiceImpl implements SingerService {
    @Autowired
    private SingerRepository singerRepository;
    @Override
    public List<Singer> getAllSingers() {
        return singerRepository.getAllSingers();
    }

    @Override
    public Singer getSinger(Long id) {
        return singerRepository.getSinger(id);
    }

    @Override
    public Singer getSinger(String lastName) {
        return singerRepository.getSinger(lastName);
    }

    @Override
    public Singer create(Singer singer) {
        return singerRepository.create(singer);
    }

    @Override
    public Singer update(Long id, Singer singer) {
        return singerRepository.update(id, singer);
    }

    @Override
    public void delete(Long id) {
        singerRepository.delete(id);
    }
}
