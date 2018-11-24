package com.nowcoder.service;

import com.alibaba.fastjson.JSONObject;
import com.nowcoder.util.ToutiaoUtil;
import com.qiniu.common.Zone;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;
/**
 * Created by snowWave.
 */
@Service
public class QiniuService {

    private static final Logger logger = LoggerFactory.getLogger(QiniuService.class);
    //换成自己申请的
    private static String QINIU_IMAGE_DOMAIN = "http://pd4ixkkzp.bkt.clouddn.com/";

    //Configuration cfg = new Configuration(Zone.zone0());//华东区是zone0
    //设置好账号的ACCESS_KEY和SECRET_KEY
    static String ACCESS_KEY = "kWwi1lEMrcqXWU7pMprZtDYeUgkLttSTJoQf-C5d";
    static String SECRET_KEY = "ZxRMsASuLvzKKWfin8DdSjAOrxlAM2yg8mPsg9Mu";
    //要上传的空间
    static String bucketname = "toutiao";
    
    //密钥配置
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    //创建上传对象
    UploadManager uploadManager = new UploadManager();//cfg


    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    public String getUpToken() {
        return auth.uploadToken(bucketname);
    }


    /**
     * 保存图片到七牛云
     * @param file
     * @return
     * @throws IOException
     */
    public String saveImage(MultipartFile file) throws IOException {
        try {
            //判断文件名合法
            int dotPos = file.getOriginalFilename().lastIndexOf(".");
            if (dotPos < 0) {
                return null;
            }
            String fileExt = file.getOriginalFilename().substring(dotPos + 1).toLowerCase();//记得toLowerCase()
            if (!ToutiaoUtil.isFileAllowed(fileExt)) {
                return null;
            }

            //改文件名  和传本地方法一样
            String fileName = UUID.randomUUID().toString().replaceAll("-", "0") + "." + fileExt;

            System.out.println(fileName);
            System.out.println(getUpToken());
            System.out.println(uploadManager);

            //调用put方法上传
            Response res = uploadManager.put(file.getBytes(), fileName, getUpToken());
            //打印返回的信息,在七牛的存储路径: {"hash":"Fiidifssfsfsgfsgsgsgsyh","key":"4786esfagh2314hk3f1.jpg"}
            //System.out.println(res.bodyString());
            //从res返回的字符串中解析key，返回一个图片访问地址
            if (res.isOK() && res.isJson()) {
                return QINIU_IMAGE_DOMAIN + JSONObject.parseObject(res.bodyString()).get("key");
            } else {
                logger.error("七牛异常:" + res.bodyString());
                return null;
            }  
        } catch (QiniuException e) {
        	
            // 请求失败时打印的异常的信息
            logger.error("七牛异常:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
