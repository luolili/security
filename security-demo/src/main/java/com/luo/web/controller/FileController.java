package com.luo.web.controller;

import com.luo.dto.FileInfo;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

@RestController
@RequestMapping("/file")
public class FileController {

    private String folder = "F:\\githubpro\\security\\security-demo\\src\\main\\java\\com\\luo\\web\\controller";

    @PostMapping
    public FileInfo upload(MultipartFile file) throws Exception {
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());

        File localFile = new File(folder, new Date().getTime() + ".txt");
        file.transferTo(localFile);
        return new FileInfo(localFile.getAbsolutePath());
    }

    @GetMapping("/{id}")
    public void download(@PathVariable String id, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //java7 语法
        try (InputStream in = new FileInputStream(new File(folder, id + ".txt"));
             OutputStream out = resp.getOutputStream();

        ) {
            resp.setContentType("application/x-download");
            resp.setHeader("Content-Disposition", "attachment;fileame=test.txt");
            IOUtils.copy(in, out);
            out.flush();
        }
    }
}
