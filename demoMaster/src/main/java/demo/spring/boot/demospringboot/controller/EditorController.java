package demo.spring.boot.demospringboot.controller;

import demo.spring.boot.demospringboot.config.AwareUtil;
import demo.spring.boot.demospringboot.framework.Response;
import demo.spring.boot.demospringboot.service.ResourceService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 配合富文本而存在的Controller
 */
@Slf4j
@RequestMapping(value = "/EditorController")
@RestController
public class EditorController {

    private FastDateFormat fastDateFormat = FastDateFormat.getInstance("yyyyMMdd_HHmmss_");

    @Autowired
    private ResourceService resourceService;

    /**
     */
    @ApiOperation(value = "为富文本编辑器存在的")
    @PostMapping(value = "/uploadFile")
    public Response uploadFile(
            @RequestPart(value = "files")
                    MultipartFile[] files
    ) {
        Response response = new Response();
        try {
            List<String> urls = new ArrayList<>();
            Resource resource = AwareUtil.resourceLoader.getResource("classpath:/static/upload/");
            if (!resource.getFile().exists()) {
                resource.getFile().mkdirs();
            }
            String rootPath = resource.getFile().getAbsolutePath();
            for (MultipartFile file : files) {
                String format = fastDateFormat.format(new Date());
                String fileName = format + file.getOriginalFilename();
                boolean b = resourceService.addFile(file.getBytes(), fileName, rootPath);
                if (b == true) {
                    urls.add("upload/" + fileName);
                }
            }
            response = Response.OK(urls);
        } catch (Exception e) {
            response = Response.Fail(e.getMessage());
        }
        return response;
    }


}

















