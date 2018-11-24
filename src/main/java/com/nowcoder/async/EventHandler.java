package com.nowcoder.async;

import java.util.List;

/**
 * Created by zhan on 2017/7/14.
 */
public interface EventHandler {
    void doHandle(EventModel model);
    //处理依赖的类型
    List<EventType> getSupportEventTypes();
}
