package com.spring.gameloft.controller;

import com.spring.gameloft.domain.Singer;
import com.spring.gameloft.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/singers")
public class SingerController {
    @Autowired
    private SingerService singerService;

    @GetMapping
    public List<Singer> getAllSingers() {
        List<Singer> allSingers = singerService.getAllSingers();
        System.out.println("Getting all singers");
//        throw new IllegalArgumentException("Not Found");
        return allSingers;
    }

    @GetMapping("/id")
    public Singer getSinger(@PathVariable("id") Long id) {
        Singer singer = singerService.getSinger(id);
        System.out.println("Getting singer: " + singer);
        return singer;
    }

    @GetMapping("/lastName")
//    @RequestMapping("/lastName")
    public Singer getSingerByLastName(@PathVariable("lastName") String lastName) {
        Singer singer = singerService.getSinger(lastName);
        System.out.println("Getting singer: " + singer);
        return singer;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Singer create(@RequestBody Singer singer) {
        System.out.println("Creating singer " + singer);
        return singerService.create(singer);
    }

    @PutMapping("/id")
    @ResponseStatus(HttpStatus.OK)
    public Singer update(@PathVariable("id") Long id, @RequestBody Singer singer)
    {
        System.out.println("updating singer " + singer);
        return singerService.update(id, singer);
    }

    @DeleteMapping("/id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id)
    {
        Singer singer = singerService.getSinger(id);
        singerService.delete(id);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public void notFound(HttpServletResponse response) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
    }
}
