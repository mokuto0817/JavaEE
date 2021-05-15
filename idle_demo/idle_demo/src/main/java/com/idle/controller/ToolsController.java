package com.idle.controller;

import com.idle.entity.vo.ZydResult;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("tools")
public class ToolsController {

    private static final String filePath = "D:\\images\\";

    @ResponseBody
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public ZydResult uploadFile(@RequestParam("file") MultipartFile file) {
        // 上传的文件名
        String filename = file.getOriginalFilename();
        String suffixName = filename.substring(filename.lastIndexOf("."));

        // 随机生成
        filename = UUID.randomUUID() + suffixName;
        File dest = new File(filePath + filename);

        try {
            File fileDir = new File(filePath);
            if (!fileDir.exists()) {
                fileDir.mkdir();
            }
            dest.createNewFile();
            file.transferTo(dest);
            return ZydResult.SUCCESS(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ZydResult.FAIL();
    }
}
