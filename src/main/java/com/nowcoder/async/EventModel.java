package com.nowcoder.async;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhan on 2017/7/14.
 */
public class EventModel {
    private EventType type;//事件
    private int actorId;//触发者
    private int entityId;//触发对象id
    private int entityType;//触发对象
    private int entityOwnerId;//触发对象拥有者
	//触发现场需要保存的数据
    private Map<String, String> exts = new HashMap<>();
    
    //构造函数
    public EventModel() {
    	
    }
    public EventModel(EventType type) {
    	this.type = type;
    }

    public String getExt(String key) {
        return exts.get(key);
    }

    public EventModel setExt(String key, String value) {
        exts.put(key, value);
        return this;
    }

    public EventType getType() {
        return type;
    }

    public EventModel setType(EventType type) {
        this.type = type;
        return this;
    }

    public int getActorId() {
        return actorId;
    }

    public EventModel setActorId(int actorId) {
        this.actorId = actorId;
        return this;
    }

    public int getEntityId() {
        return entityId;
    }

    public EventModel setEntityId(int entityId) {
        this.entityId = entityId;
        return this;
    }

    public int getEntityType() {
        return entityType;
    }

    public EventModel setEntityType(int entityType) {
        this.entityType = entityType;
        return this;
    }

    public int getEntityOwnerId() {
        return entityOwnerId;
    }

    public EventModel setEntityOwnerId(int entityOwnerId) {
        this.entityOwnerId = entityOwnerId;
        return this;
    }
    
    public Map<String, String> getExts() {
        return exts;
    }
    
    public void setExts(Map<String, String> exts) {
		this.exts = exts;
	}
}
