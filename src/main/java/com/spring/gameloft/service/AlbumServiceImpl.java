package com.spring.gameloft.service;

import com.spring.gameloft.domain.Album;
import com.spring.gameloft.domain.AlbumSinger;
import com.spring.gameloft.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumRepository albumRepository;
    @Override
    public List<Album> getAllAlbums() {
        return albumRepository.getAllAlbums();
    }

    @Override
    public Album getAlbum(Long id) {
        return albumRepository.getAlbum(id);
    }

    @Override
    public AlbumSinger getAlbumSinger(Long id, Long singerId) {
        return albumRepository.getAlbumSinger(id, singerId);
    }

    @Override
    public void updateAlbum(Long id, Album album) {
        albumRepository.updateAlbum(id, album);
    }
}
