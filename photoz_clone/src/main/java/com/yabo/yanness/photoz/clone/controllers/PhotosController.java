package com.yabo.yanness.photoz.clone.controllers;

import com.yabo.yanness.photoz.clone.model.Photo;
import com.yabo.yanness.photoz.clone.service.PhotosService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.*;

@RestController
public class PhotosController {

    //private List<Photo> pt =  List.of(new Photo("1","hi.jpg"));

    //Dependency injection
    private final PhotosService ps;

    public PhotosController (PhotosService ps){
        this.ps = ps;
    }

    @GetMapping(path = "/")
    public String greeting(){
        return "Welcome to my website!";
    }

    @GetMapping("/photos")
    public Iterable<Photo> get(){
        return ps.get();
    };

    @GetMapping("/photos/{id}")
    public Photo get(@PathVariable Integer id){
        Photo p = ps.get(id);
        if (p == null)throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return p;
    };

    @DeleteMapping("/photos/{id}")
    public void delete(@PathVariable Integer id){
        ps.remove(id);
    };

//    @PostMapping("/photos")
//    public Photo create(@RequestBody@Valid Photo p){
//        p.setId(UUID.randomUUID().toString());
//        db.put(p.getId(),p);
//        return p;
//    };

    @PostMapping("/photos")
    public Photo create(@RequestPart("data") MultipartFile file)throws IOException {
        return ps.save(file.getOriginalFilename(), file.getContentType(), file.getBytes());
    };
}
