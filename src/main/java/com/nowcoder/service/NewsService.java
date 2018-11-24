package com.nowcoder.service;

import com.nowcoder.dao.NewsDAO;
import com.nowcoder.model.News;
import com.nowcoder.util.ToutiaoUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

/**
 * Created by zhan on 2017/7/2.
 */
@Service
public class NewsService {
    @Autowired
    private NewsDAO newsDAO;

    /**
     * 获取最新资讯
     * @param userId  用户ID  设为0，查询所有资讯；非零，查询某个用户资讯（NewsDAO.xml条件判断）
     * @param offset  偏移量
     * @param limit   显示数量
     * @return
     */
    public List<News> getLatestNews(int userId, int offset, int limit) {
        return newsDAO.selectByUserIdAndOffset(userId, offset, limit);
    }
    
    /**
     * 添加资讯
     * @param news
     * @return
     */
    public int addNews(News news) {
        newsDAO.addNews(news);
        return news.getId();
    }

    public News getById(int newsId) {
        return newsDAO.getById(newsId);
    }
    
    public int updateCommentCount(int id, int count) {
        return newsDAO.updateCommentCount(id, count);
    }
    
    public int updateLikeCount(int id, int count) {
        return newsDAO.updateLikeCount(id, count);
    }
    
    /**
     * 保存文件到服务器，返回图片链接地址
     * @param file
     * @return
     * @throws IOException
     */
    public String saveImage(MultipartFile file) throws IOException {
        int dotPos = file.getOriginalFilename().lastIndexOf(".");
        if (dotPos < 0) {
            return null;
        }
        //获取图片后缀名 jpg png 等
        String fileExt = file.getOriginalFilename().substring(dotPos + 1).toLowerCase();
        //扩展名格式不符合
        if (!ToutiaoUtil.isFileAllowed(fileExt)) {
            return null;
        }

        //将上传的文件名重新生成新的文件名:前缀+后缀
        String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + fileExt;
        //将上传文件的二进制流copy到目标地址
        Files.copy(file.getInputStream(), new File(ToutiaoUtil.IMAGE_DIR + fileName).toPath(),
                StandardCopyOption.REPLACE_EXISTING);
        //图片完整链接，给前端用
        return ToutiaoUtil.TOUTIAO_DOMAIN + "image?name=" + fileName;
    }
}
