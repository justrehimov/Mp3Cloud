package com.mp3.cloud.controller;

import com.mp3.cloud.entity.Music;
import com.mp3.cloud.repo.MusicRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {
    private final MusicRepo musicRepo;

    @GetMapping
    public ModelAndView homePage(){
        List<Music> musics = musicRepo.findAll();
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("musics",musics);
        return modelAndView;
    }
}
