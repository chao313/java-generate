package demo.spring.boot.demospringboot.service;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


@Slf4j
@Lazy
@Service
public class ResourceService {


    public final static String locationResourcePath = "locationResourcePath/";

    //系统属性注入 #{}
    @Value(value = "#{systemProperties['os.name']}")
    private String system;

    /**
     * 根据文件名称判断文件是否存在
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    public boolean existFileByName(String fileName) {
        File file = new File(ResourceService.locationResourcePath + fileName);
        return file.exists();
    }


    /**
     * 根据文件名称删除文件 -> 文件夹
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    public boolean deleteFileByName(String fileName) {
        File file = new File(ResourceService.locationResourcePath + fileName);
        if (!file.exists()) {
            return true;
        }
        if (file.exists()) {
            file.delete();
        }
        return true;
    }


    /**
     * 添加文件
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    public boolean addFile(byte[] bytes, String fileName) throws IOException {
        File file = new File(ResourceService.locationResourcePath + fileName);
        FileUtils.write(file, String.valueOf(bytes), "UTF-8");
        FileUtils.writeByteArrayToFile(file, bytes);
        return true;
    }

    /**
     * 获取所有的文件
     *
     * @throws IOException
     */
    public List<File> getFiles() {
        File file = new File(ResourceService.locationResourcePath);
        File[] list = null;
        if (!file.exists()) {
            file.mkdirs();
        }
        if (file.isDirectory()) {
            list = file.listFiles();
        }
        return Arrays.asList(list);
    }

    /**
     * 获取所有的文件
     *
     * @throws IOException
     */
    public File getFilesByFileName(String fileName) {
        File file = new File(ResourceService.locationResourcePath + fileName);
        return file;
    }

    /**
     * 获取指定文件 -> 二进制
     *
     * @throws IOException
     */
    public byte[] getFileBytesByName(String fileName) throws IOException {
        File file = new File(ResourceService.locationResourcePath + fileName);
        return FileUtils.readFileToByteArray(file);
    }

    /**
     * 获取指定文件 的 contentType
     *
     * @throws IOException
     */
    public String getFileContentTypeBytesByName(String fileName) {
        String filePath = ResourceService.locationResourcePath + fileName;
        String contentType = "";
        try {
            contentType = new MimetypesFileTypeMap().getContentType(new File(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contentType;
    }
}


