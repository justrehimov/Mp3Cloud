package com.mp3.cloud.controller;

import com.mp3.cloud.repo.MusicRepo;
import com.mp3.cloud.service.MusicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/upload")
@RequiredArgsConstructor
public class MusicController {

    private final MusicService musicService;
    private final MusicRepo musicRepo;

    @GetMapping
    public String music(){
        return "music";
    }

    @PostMapping
    public ModelAndView music(@RequestPart("music") MultipartFile[] musics){
        String response = musicService.upload(musics);
        ModelAndView modelAndView = new ModelAndView(response);
        modelAndView.addObject("musics",musicRepo.findAll());
        return modelAndView;
    }


}
