package com.yabo.yanness.photoz.clone.controllers;

import com.yabo.yanness.photoz.clone.model.Photo;
import com.yabo.yanness.photoz.clone.service.PhotosService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
    public class DownloadController {
    private final PhotosService ps;

    public DownloadController (PhotosService ps){
        this.ps = ps;
    }

        @GetMapping("download/{id}")
        public ResponseEntity<byte[]> download(@PathVariable Integer id){

            Photo p = ps.get(id);
            if (p==null)throw new ResponseStatusException(HttpStatus.NOT_FOUND);

            byte[] data = p.getData();
            HttpHeaders hs = new HttpHeaders();
            hs.setContentType(MediaType.valueOf(p.getContentType()));
            ContentDisposition build = ContentDisposition.builder("attachment").filename(p.getFileName()).build();
            hs.setContentDisposition(build);
            return new ResponseEntity<>(data, hs, HttpStatus.OK);
        }
    }
