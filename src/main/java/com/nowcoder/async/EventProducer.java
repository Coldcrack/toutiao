package com.nowcoder.async;

import com.alibaba.fastjson.JSONObject;
import com.nowcoder.util.JedisAdapter;
import com.nowcoder.util.RedisKeyUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by zhan on 2017/7/14.
 */
@Service
public class EventProducer {
	//需要一个队列
    @Autowired
    JedisAdapter jedisAdapter;

    public boolean fireEvent(EventModel eventModel) {
        //发送一个事件，并且存进队列
    	try {
            String json = JSONObject.toJSONString(eventModel);//事件序列化
            String key = RedisKeyUtil.getEventQueueKey();
            jedisAdapter.lpush(key, json);//放入队列
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
