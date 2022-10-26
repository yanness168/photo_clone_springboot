package com.yabo.yanness.photoz.clone;

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
    public Collection<Photo> get(){
        return ps.get();
    };

    @GetMapping("/photos/{id}")
    public Photo get(@PathVariable String id){
        Photo aPhoto = ps.get(id);
        if (aPhoto == null)throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return aPhoto;
    };

    @DeleteMapping("/photos/{id}")
    public void delete(@PathVariable String id){
        Photo aPhoto = ps.remove(id);
        if (aPhoto == null)throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    };

//    @PostMapping("/photos")
//    public Photo create(@RequestBody@Valid Photo p){
//        p.setId(UUID.randomUUID().toString());
//        db.put(p.getId(),p);
//        return p;
//    };

    @PostMapping("/photos")
    public Photo create(@RequestPart("data") MultipartFile file)throws IOException {
        Photo p = ps.save(file.getOriginalFilename(), file.getContentType(), file.getBytes());
        return p;
    };
}
