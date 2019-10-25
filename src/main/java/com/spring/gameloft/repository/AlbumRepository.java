package com.spring.gameloft.repository;

import com.spring.gameloft.domain.Album;
import com.spring.gameloft.domain.AlbumSinger;

import java.util.List;

public interface AlbumRepository {
    List<Album> getAllAlbums();

    Album getAlbum(Long id);

    AlbumSinger getAlbumSinger(Long id, Long singerId);

    void updateAlbum(Long id, Album album);
}
