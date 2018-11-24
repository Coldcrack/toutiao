package com.nowcoder.model;

import org.springframework.stereotype.Component;

/**
 * Created by zhan on 2017/7/3.
 */

/**
 * 建一个HostHolder类，用来保存当前用户，set存，get取；
 * 服务器有很多人在用，线程本地变量ThreadLocal
 * @author Administrator
 *
 */
@Component
public class HostHolder {
    //本地线程，每条线程只能存自己的信息
	private static ThreadLocal<User> users = new ThreadLocal<User>();

    public User getUser() {
        return users.get();
    }

    public void setUser(User user) {
        users.set(user);
    }

    public void clear() {
        users.remove();;
    }
}
