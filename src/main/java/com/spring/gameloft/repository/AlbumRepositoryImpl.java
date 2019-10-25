package com.spring.gameloft.repository;

import com.spring.gameloft.domain.Album;
import com.spring.gameloft.domain.AlbumSinger;
import com.spring.gameloft.domain.Singer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class AlbumRepositoryImpl implements AlbumRepository {
    private String getAlbumsSql = "SELECT * FROM `album`;";

    private String getAlbumSql = "SELECT * FROM `album` WHERE `id` = ?;";

    private String getAlbumSinger = "SELECT `album`.`id`, `album`.`singer_id`, `album`.`title`, `album`.`release_date`, "
        + "`singer`.`first_name`, `singer`.`last_name`, `singer`.`birth_date` FROM `album` "
        + "JOIN `singer` ON `singer`.`id` = `album`.`singer_id` AND `album`.`id` = ? AND `album`.`singer_id` = ?;";

    private String updateAlbumSql = "UPDATE `album` SET `release_date` = ? WHERE `id` = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<Album> getAllAlbums() {
        List<Album> albums = jdbcTemplate.query(getAlbumsSql, BuildRowMapperAlbum());
        return albums;
    }

    @Override
    public Album getAlbum(Long id) {
        Album album = jdbcTemplate.queryForObject(getAlbumSql, BuildRowMapperAlbum(), id);
        return album;
    }

    @Override
    public AlbumSinger getAlbumSinger(Long id, Long singerId) {
        AlbumSinger albumSinger = jdbcTemplate.queryForObject(getAlbumSinger, BuildRowMapperAlbumSinger(), id, singerId);
        return albumSinger;
    }

    @Override
    public void updateAlbum(Long id, Album album) {
        jdbcTemplate.update(updateAlbumSql, album.getReleaseDate(), id);
    }

    private RowMapper<AlbumSinger> BuildRowMapperAlbumSinger() {
        RowMapper<AlbumSinger> rowMapper = (ResultSet rs, int rowNum) -> {
            AlbumSinger albumSinger = new AlbumSinger();
            Album album = new Album();
            Singer singer = new Singer();
            album.setId(rs.getLong("id"));
            album.setSingerId(rs.getLong("singer_id"));
            album.setTitle(rs.getString("title"));
            album.setReleaseDate(rs.getDate("release_date").toLocalDate());
            singer.setId(rs.getLong("singer_id"));
            singer.setFirstName(rs.getString("first_name"));
            singer.setLastName(rs.getString("last_name"));
            singer.setBirthDate(rs.getDate("birth_date").toLocalDate());
            albumSinger.setAlbum(album);
            albumSinger.setSinger(singer);
            return albumSinger;
        };

        return rowMapper;
    }

    private RowMapper<Album> BuildRowMapperAlbum() {
        RowMapper<Album> rowMapper = (ResultSet rs, int rowNum) -> {
            Album album = new Album();
            album.setId(rs.getLong("id"));
            album.setSingerId(rs.getLong("singer_id"));
            album.setTitle(rs.getString("title"));
            album.setReleaseDate(rs.getDate("release_date").toLocalDate());
            return album;
        };

        return rowMapper;
    }
}
