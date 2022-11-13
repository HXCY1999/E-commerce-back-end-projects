package com.jt.controller;

import com.jt.service.FileService;
import com.jt.vo.ImageVO;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.IOException;

@RestController
@RequestMapping("/file")
@CrossOrigin
public class FileController {

    @Autowired
    private FileService fileService;
    /**
     * /**
     * * - Request path: http://localhost:8091/file/upload
     * * - Request type: post
     * * - Request parameters: file: binary bytes of information
     * * - Return value: SysResult object (ImageVO)
     * * Core API:
     * * file.transferTo("full path to the image");
     */

    @PostMapping("/upload")
    public SysResult upload(MultipartFile file) throws IOException {
       ImageVO imageVO = fileService.upload(file);
       if (imageVO == null){
           return SysResult.fail();
       }
       return SysResult.success(imageVO);

    }
}
