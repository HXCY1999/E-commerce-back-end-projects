package com.jt.service;

import com.jt.vo.ImageVO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService{

    private  String preFilePath = "D:/WorkSpace/Image";
    private  String preURLPath = "http://image.jt.com";
    @Override
    public ImageVO upload(MultipartFile file) throws IOException {
        //Step 1: Verify the image type
        //1. Get the file name abc.JPG
        //2. bug: file name case problem all converted to lowercase
        String filename = file.getOriginalFilename().toLowerCase(Locale.ROOT);
        if (!(filename.matches("^.+\\.(jpg||png||gif)$"))){
                return null;
        }
        // Step 2: Verify if it is a malicious program
        try {
            BufferedImage image = ImageIO.read(file.getInputStream());
            int height = image.getHeight();
            int width = image.getWidth();
            if (height == 0 || width == 0){
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Step 3:
        // Store in separate directories for more efficient retrieval Divide directories
        // by time /yyyy/MM/dd/
            String datePath =
                    new SimpleDateFormat("/yyyy/MM/dd/").format(new Date());
            String fileDir = preFilePath + datePath;
            File dirFile = new File(fileDir);
            if(!dirFile.exists()){
                // /If there is no directory, create directory
                dirFile.mkdirs();
            }
             // Step 4: Rename to prevent file renaming
            String uuid = UUID.randomUUID().toString().replace("-","");
            int index = filename.lastIndexOf(".");
            //Get data .jpg
            String fileType = filename.substring(index);
           //file name
            filename = uuid + fileType;
            //file upload
            String filepath =  fileDir + filename;
            file.transferTo(new File(filepath));
            //Step 6: Encapsulate ImageVO return data /2022/01/10/uuid.jpg
            String virtualPath = datePath + filename; //Remove the address of the disk
            // Step 7: Encapsulate the network address http://image.jt.com/2022/01/10/uuid.jpg
            String urlPath =  preURLPath + virtualPath;
            System.out.println(urlPath);
            return new ImageVO(virtualPath,urlPath,filename);
    }
}
