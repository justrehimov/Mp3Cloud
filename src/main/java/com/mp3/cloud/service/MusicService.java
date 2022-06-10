package com.mp3.cloud.service;

import com.mp3.cloud.entity.Music;
import com.mp3.cloud.entity.User;
import com.mp3.cloud.repo.MusicRepo;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.nio.file.Files;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MusicService {

    private final MusicRepo musicRepo;
    private final UploadService uploadService;

    @Value("${upload.url}")
    private String uploadPath;

    @Transactional
    public String upload(MultipartFile[] musics) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            for(MultipartFile multipartFile:musics){
                ResponseEntity<String> response =
                        uploadService.uploadFile(uploadPath,UUID.randomUUID().toString(),multipartFile);
                if(response.getBody().toString().equalsIgnoreCase("error"))
                    return "upload?error";
                String path = response.getBody();
                Music music = new Music();
                music.setName(multipartFile.getOriginalFilename());
                music.setPath(path);
                music.setSharer(user.getUsername());
                music.setUser(user);
                music.setDataDate(new Date());
                musicRepo.save(music);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return "index";
    }


    public Music getMusic(Long id) {
        return musicRepo.findById(id).orElse(null);
    }
}
