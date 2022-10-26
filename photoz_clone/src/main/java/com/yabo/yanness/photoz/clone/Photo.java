package com.yabo.yanness.photoz.clone;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.support.StringMultipartFileEditor;

import javax.validation.constraints.NotEmpty;

public class Photo {
    private String id;

    private String contentType;

    @NotEmpty
    private String fileName;

    @JsonIgnore//Not including the data bytes info in the JSON object bc it's too long
    private byte[] data;

    public Photo(String id, String fileName){
        this.id=id;
        this.fileName=fileName;
    }

    public String getId(){
        return id;
    };

    public String getFileName(){
        return fileName;
    };

    public byte[] getData(){
        return data;
    }

    public String getContentType() {
        return contentType;
    }

    public void setId(String id){
        this.id = id;
    };

    public void setFileName(String fileName){
        this.fileName=fileName;
    };

    public void setData (byte[] data){
        this.data = data;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
