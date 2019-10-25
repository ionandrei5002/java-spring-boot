package com.spring.gameloft.controller;

import com.spring.gameloft.Config;
import com.spring.gameloft.domain.Album;
import com.spring.gameloft.domain.AlbumSinger;
import com.spring.gameloft.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumController {
    @Autowired
    private Config config;
    @Autowired
    private AlbumService albumService;

    @GetMapping
    public List<Album> getAlbums() {
        List<Album> albums = albumService.getAllAlbums();
        return albums;
    }
    @GetMapping("/{id}")
    public Album getAlbum(@PathVariable("id") Long id) {
        Album album = albumService.getAlbum(id);
        return album;
    }
    @GetMapping("/{id}/{singerId}")
    public AlbumSinger getAlbumSinger(@PathVariable("id") Long id, @PathVariable("singerId") Long singerId) {
        AlbumSinger albumSinger = albumService.getAlbumSinger(id, singerId);
        return albumSinger;
    }
    @PostMapping("/{id}")
    public Album updateAlbum(@PathVariable("id") Long id, @RequestBody Album album) {
        albumService.updateAlbum(id, album);
        return albumService.getAlbum(id);
    }
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<String> notFound() {
        System.out.println(config.getDefaultMessage());
        ResponseEntity<String> responseEntity = new ResponseEntity(config.getDefaultMessage(), HttpStatus.NOT_FOUND);
        return responseEntity;
    }
}
