package com.demo.springboot.controller;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class UploadController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);

    @ApiOperation(value="测试jsp")
    @GetMapping("/upload")
    public ModelAndView upload() {
        ModelAndView mv = new ModelAndView("upload");
        return mv;
    }
    @ApiOperation(value = "测试单文件上传。。。")
    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file")MultipartFile file){
        if(file.isEmpty()){
            return "上传失败，请选择文件。。";
        }
        String fileName = file.getOriginalFilename();
        String filePath = "E:\\test\\upload\\";
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            LOGGER.info("上传成功。。。");
            return "上传成功。。。";

        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error(e.toString(), e);
        }
        return "上传失败。。。";
    }

    /**
     * 多文件上传
     * @return
     */
    @ApiOperation(value = "测试多文件上传")
    @GetMapping("/multiUpload")
    public ModelAndView multiUpload() {
        ModelAndView mv = new ModelAndView("multiUpload");
        return mv;
    }

    @PostMapping("/multiUpload")
    @ResponseBody
    public String multiUpload(HttpServletRequest request){
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).
                getFiles("file");
        String filePath = "E:\\test\\upload\\";
        for (int i = 0; i < files.size(); i++) {
            MultipartFile file = files.get(i);
            if(file.isEmpty()) {
                return "上传第 " + (i + 1) + "个文件失败。。。";
            }
            String fileName = file.getOriginalFilename();
            File dest = new File(filePath + fileName);
            try {
                file.transferTo(dest);
                LOGGER.info("第" + (i + 1) + "个文件上传成功。。。");
            } catch (IOException e) {
                LOGGER.error(e.toString(), e);
                e.printStackTrace();
                return "上传" + i++ + "个文件失败。。。";
            }
        }
        return "上传成功。。。";
    }
}
