package com.highqi.demo.controller;

import com.highqi.demo.mapper.UserMapper;
import com.highqi.demo.utils.CookieUtil;
import com.highqi.demo.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 描述: 文件相关的Controller
 *
 * @author C
 * Date: 2018-07-25
 * Time: 21:14
 */

@Slf4j
@Controller
@RequestMapping("/file")
public class ImgUploadController {

    @Autowired
    private UserMapper mapper;

    @Value("${web.upload-path}")
    private String path;

    private final ResourceLoader resourceLoader;
    @Autowired
    public ImgUploadController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    /** 跳转到文件上传模板页面的方法. */
    @RequestMapping("/showUpload")
    public String toUpload(@RequestParam("userid")String userid,
                           Map<String,Object> map,
                           HttpServletResponse response,
                           HttpServletRequest request){

        CookieUtil.setCookie(request,response,"userid",userid);
        return "/demo/file";
    }

    /**
     * 文件上传方法
     * @param file 要上传的文件
     */
    @RequestMapping("/fileUpload")
    public ModelAndView upload(@RequestParam("fileName") MultipartFile file,
                               HttpServletRequest request,
                               Map<String, Object> map){

        String userid = CookieUtil.getCookieValue(request, "userid");
        // 要上传的目标文件存放路径
        String localPath = "D:/test";
        if (!file.isEmpty()) {
            String imgname = file.getOriginalFilename();
            FileUtil.upload(file, localPath, file.getOriginalFilename());
            //携带文件名
            map.put("fileName",imgname );
            mapper.updateForumheadimgurl(imgname,userid);
            return new ModelAndView("/demo/file");
        }
        return new ModelAndView("/demo/file");
    }

    /** 显示单张图片. */
    @RequestMapping("/show")
    public ResponseEntity showPhotos(String fileName){
        try {
            // 由于是读取本机的文件，file是一定要加上的， path是在application配置文件中的路径
            return ResponseEntity.ok(resourceLoader.getResource("file:" + path + fileName));
        } catch (Exception e) {
            log.error("【显示图片】 读取本机文件失败 {}",e);
            return ResponseEntity.notFound().build();
        }
    }
}