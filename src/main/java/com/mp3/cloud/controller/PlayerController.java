package com.mp3.cloud.controller;

import com.mp3.cloud.entity.Music;
import com.mp3.cloud.service.MusicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/music")
@RequiredArgsConstructor
public class PlayerController {

    private final MusicService musicService;

    @GetMapping("/{id}")
    public ModelAndView playMusic(@PathVariable("id") Long id){
        Music music = musicService.getMusic(id);
        if(music==null)
            return null;
        ModelAndView modelAndView = new ModelAndView("play");
        modelAndView.addObject("music",music);
        return modelAndView;
    }
}
