package com.yabo.yanness.photoz.clone.service;

import com.yabo.yanness.photoz.clone.model.Photo;
import com.yabo.yanness.photoz.clone.repository.PhotosRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

//@Component
    @Service
    public class PhotosService {
        //This is a database
        private final PhotosRepository photosRepository;

    public PhotosService (PhotosRepository photosRepository){
        this.photosRepository = photosRepository;
    }

    public Iterable<Photo> get() {
        return photosRepository.findAll();
    }

    public Photo get(Integer id) {
        return photosRepository.findById(id).orElse(null);
    }

    public void remove(Integer id) {
        photosRepository.deleteById(id);
    }

    public Photo save(String fileName, String contentType, byte[] data) {
        Photo p = new Photo ();

        p.setFileName(fileName);
        p.setContentType(contentType);
        p.setData(data);

        photosRepository.save(p);
        return p;
    }
}
