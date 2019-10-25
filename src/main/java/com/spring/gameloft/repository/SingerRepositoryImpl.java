package com.spring.gameloft.repository;

import com.spring.gameloft.domain.Singer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.Comparator;
import java.util.List;

@Repository
public class SingerRepositoryImpl implements SingerRepository {
    String getSingersSql = "SELECT * FROM `singer`";
    String getSingerSql = "SELECT * FROM `singer` WHERE id = ?";
    String getSingerByLastNameSql = "SELECT * FROM `singer` WHERE last_name = '?'";
    String insertSingerSql = "INSERT INTO `singer` VALUES(NULL,?,?,?)";
    String updateSingerSql = "UPDATE `singer` SET `first_name` = ?, `last_name` = ?, `birth_date` = ? WHERE `id` = ?";
    String deleteSingerSql = "DELETE FROM `singer` WHERE `id` = ?";
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<Singer> getAllSingers() {
        RowMapper<Singer> rowMapper = BuildRowMapper();
        List<Singer> singerList = jdbcTemplate.query(getSingersSql, rowMapper);
        return singerList;
    }

    @Override
    public Singer getSinger(Long id) {
        RowMapper<Singer> rowMapper = BuildRowMapper();
        return jdbcTemplate.queryForObject(getSingerSql, rowMapper, id);
    }

    @Override
    public Singer getSinger(String lastName) {
        RowMapper<Singer> rowMapper = BuildRowMapper();
        return jdbcTemplate.queryForObject(getSingerByLastNameSql, rowMapper, lastName);
    }

    @Override
    public Singer create(Singer singer) {
        int update_rows = jdbcTemplate.update(insertSingerSql, singer.getFirstName(), singer.getLastName(), singer.getBirthDate());
        return getAllSingers().stream().max(Comparator.comparing(Singer::getId)).get();
    }

    @Override
    public Singer update(Long id, Singer singer) {
        int update_rows = jdbcTemplate.update(updateSingerSql, singer.getFirstName(), singer.getLastName(), singer.getBirthDate(), id);
        if(update_rows == 0) {
            return null;
        }
        return getAllSingers().stream().max(Comparator.comparing(Singer::getId)).get();
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(deleteSingerSql, id);
    }

    private RowMapper<Singer> BuildRowMapper() {
        RowMapper<Singer> rowMapper = (ResultSet rs, int rowNum) -> {
            Singer singer = new Singer();
            singer.setId(rs.getLong("id"));
            singer.setFirstName(rs.getString("first_name"));
            singer.setLastName(rs.getString("last_name"));
            singer.setBirthDate(rs.getDate("birth_date").toLocalDate());
            return singer;
        };

        return rowMapper;
    }
}
