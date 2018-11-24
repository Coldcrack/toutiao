package com.nowcoder.service;

import com.nowcoder.dao.MessageDAO;
import com.nowcoder.dao.NewsDAO;
import com.nowcoder.model.Message;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhan on 2017/7/7.
 */
@Service
public class MessageService {
    @Autowired
    private MessageDAO messageDAO;

    //发送消息
    public int addMessage(Message message) {
        return messageDAO.addMessage(message);
    }
    
    //站内信列表
    public List<Message> getConversationList(int userId, int offset, int limit) {
        // conversation的总条数存在messageDAO  id里
        return messageDAO.getConversationList(userId, offset, limit);
    }

    //获取双方对话所有内容
    public List<Message> getConversationDetail(String conversationId, int offset, int limit) {
        // conversation的总条数存在id里
        return messageDAO.getConversationDetail(conversationId, offset, limit);
    }

    //未读消息数量
    public int getConversationUnReadCount(int userId, String conversationId) {
        return messageDAO.getConversationUnReadCount(userId, conversationId);
    }
}
