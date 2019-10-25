package com.spring.gameloft.service;

import com.spring.gameloft.domain.Album;
import com.spring.gameloft.domain.AlbumSinger;

import java.util.List;

public interface AlbumService {
    List<Album> getAllAlbums();

    Album getAlbum(Long id);

    AlbumSinger getAlbumSinger(Long id, Long singerId);

    void updateAlbum(Long id, Album album);
}
