package com.mp3.cloud.service;

import com.mp3.cloud.entity.Music;
import com.mp3.cloud.entity.User;
import com.mp3.cloud.repo.MusicRepo;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MusicService {

    private final MusicRepo musicRepo;

    @Value("${upload.path}")
    private String uploadPath;
    @Value("${domain}")
    private String domain;

    @Transactional
    public String upload(MultipartFile[] musics) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            for(MultipartFile multipartFile:musics){
                String path = uploadFile(multipartFile);
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

    @SneakyThrows
    private String uploadFile(MultipartFile multipartFile){
        String originalName = multipartFile.getOriginalFilename();
        System.out.println(originalName);
        String extension = originalName.split("\\.")[originalName.split("\\.").length-1];
        String uniqueName = UUID.randomUUID().toString() + "." + extension;
        String path = "/musics/" + uniqueName;
        File file = new File(uploadPath + uniqueName);
        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        byte[] bytes = new byte[multipartFile.getInputStream().available()];
        multipartFile.getInputStream().read(bytes);
        fos.write(bytes);
        fos.close();
        return domain + path;
    }
}
