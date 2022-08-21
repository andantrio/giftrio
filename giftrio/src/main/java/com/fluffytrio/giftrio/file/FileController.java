package com.fluffytrio.giftrio.file;

import java.io.IOException;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fluffytrio.giftrio.utils.file.FileUpload;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/file")
public class FileController {

    @PostMapping()
    public String addFile(@RequestBody MultipartFile multipartFile) throws IOException {
        return FileUpload.saveFile(multipartFile);
    }

}
