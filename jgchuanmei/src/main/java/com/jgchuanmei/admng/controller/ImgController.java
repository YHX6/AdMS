package com.jgchuanmei.admng.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
public class ImgController {
    Logger logger = LoggerFactory.getLogger(ImgController.class);

    public static String uploadDirectory = System.getProperty("user.dir") + "/img";


//    @RequestMapping("/img/{fileName}")
//    public String seeImage(@PathVariable("fileName") String fileName, Model theModel) {
//        theModel.addAttribute("file", fileName);
//        return "img";
//    }

    @RequestMapping("/img/{fileName}")
    public ResponseEntity<byte[]> getImage(@PathVariable("fileName") String fileName) throws IOException {
        // Read the image file into a byte array
//        System.out.println(Paths.get(uploadDirectory , fileName).toString());
        logger.error(Paths.get(uploadDirectory , fileName).toString());
        File imageFile = new File(Paths.get(uploadDirectory , fileName).toString());
        byte[] imageBytes = Files.readAllBytes(imageFile.toPath());

        // Create the HttpHeaders object and set the MIME type
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG); // Set the correct MIME type for your image

        // Create and return the ResponseEntity with the image bytes and headers
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }
}