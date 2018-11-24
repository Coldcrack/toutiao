package com.nowcoder.util;

/**
 * Created by zhan on 2017/7/13.
 */
//规范key，防止重复
public class RedisKeyUtil {
    private static String SPLIT = ":";//分隔符
    private static String BIZ_LIKE = "LIKE";
    private static String BIZ_DISLIKE = "DISLIKE";
    private static String BIZ_EVENT = "EVENT";//队列名

    public static String getEventQueueKey() {
        return BIZ_EVENT;
    }

    //某人喜欢某元素，获取组合key，将某人存放set
    public static String getLikeKey(int entityId, int entityType) {
        return BIZ_LIKE + SPLIT + String.valueOf(entityType) + SPLIT + String.valueOf(entityId);
    }
    //取消喜欢
    public static String getDisLikeKey(int entityId, int entityType) {
        return BIZ_DISLIKE + SPLIT + String.valueOf(entityType) + SPLIT + String.valueOf(entityId);
    }
}
