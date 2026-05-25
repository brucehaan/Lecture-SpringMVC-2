package hello.upload.domain;

import lombok.Data;

@Data
public class UploadFile {
    private String uploadFileName;
    private String storeFileName;

    public UploadFile(String originalFilename, String storeFilename) {
        this.uploadFileName = originalFilename;
        this.storeFileName = storeFilename;
    }
}
