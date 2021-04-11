package com.exercise.testspringboot;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@PropertySource({"classpath:application.properties"})
public class SampleController {

    @RequestMapping("/test")
    @ResponseBody
    public Map<String,String> testMap() {
        Map<String,String> map = new HashMap<>();
        map.put("name", "aaa");
        return map;
    }


    @RequestMapping("/testjson/{name_id}")
    @ResponseBody
    public Object testMapjson(@PathVariable("name_id") String name) {
        Map<String,String> map = new HashMap<>();
        map.put("name", name);
        return map;
    }


    @PostMapping("/testpost")
    @ResponseBody
    public Map<String,String> testMappost(String id) {
        Map<String,String> map = new HashMap<>();
        map.put("name", id);
        return map;
    }


    //上传文件demo
    @Value("${web.file.path}")
    private String filePath;

    @PostMapping("/upload")
    @ResponseBody
    public ResponseData upload(@RequestParam("head_img")MultipartFile file,
                         HttpServletRequest request) {
        file.isEmpty();//图片是否为空
        file.getSize();//图片大小进行判断
        String name = request.getParameter("name");
        String fileName = file.getOriginalFilename();//文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath + fileName);

        try {
            file.transferTo(dest);
            return new ResponseData(0, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(-1, "fail to save file");
    }

    @PostMapping("/api/v1")
    @ResponseBody
    public ResponseData tetsapi() {
        return new ResponseData(0, "test api");
    }

}
