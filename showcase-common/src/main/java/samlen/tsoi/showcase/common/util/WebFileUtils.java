package samlen.tsoi.showcase.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.UUID;

/**
 * web端的文件工具类，命名加web为了区别与已有的同名工具类
 *
 * @author samlen_tsoi
 * @date 2017/10/16
 */
@Slf4j
public class WebFileUtils {

    private final static String FILE_PATH = System.getProperty("user.home") + "/data/tmp/";

    /**
     * 保存前端传过来的文件到本地
     * @param multipartFile
     * @return
     * @throws IOException
     */
    public static File download2Local(MultipartFile multipartFile) throws IOException {
        InputStream inputStream = multipartFile.getInputStream();
        //获取文件格式
        String fileType = StringUtils.substringAfterLast(multipartFile.getOriginalFilename(), ".").toLowerCase();
        //生成文件名
        String filename = UUID.randomUUID().toString().replace("-", "") + "." + fileType;
        //文件下载到本地
        File file = new File(FILE_PATH + filename);
        //如果存在，则删除
        if(file.exists()) {
            file.delete();
        }
        FileUtils.copyInputStreamToFile(inputStream, file);
        return file;
    }

    /**
     * 从url下载文件
     *
     * @param url 文件下载url
     * @param dirPath 文件保存路径
     * @return 文件
     */
    public static File downFromUrl(String url, String dirPath) throws IOException {
        //获取文件名称
        String fileName = StringUtils.substringAfterLast(url, "/");
        log.info("downFromUrl:fileName-"+fileName);
        File file = new File(dirPath + fileName);
        log.info("downFromUrl:filePathName-"+file.getPath());
        URL source = new URL(url);
        //文件下载到本地
        FileUtils.copyURLToFile(source, file);
        return file;
    }
}
