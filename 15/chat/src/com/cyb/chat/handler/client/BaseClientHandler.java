package com.cyb.chat.handler.client;

import com.cyb.chat.entity.User;
import com.cyb.chat.model.Result;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

/**
 * @author Cyb
 */
public abstract class BaseClientHandler {
    BaseClientHandler baseClientHandler;

    BaseClientHandler(BaseClientHandler baseClientHandler) {
        this.baseClientHandler = baseClientHandler;
    }

    /**
     * 解析客户端封装信息并处理回复
     *
     * @param users  用户集合
     * @param user   当前用户
     * @param result 客户端信息封装类
     */
    public abstract void handlerResult(Vector<User> users, User user, Result result);

    public static void chatTellNotify(Vector<User> users, String msg) {
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        for (User u : users) {
            u.tellNotify(time + "  通知: " + msg);
        }
    }

    public static void chatTellEveryone(Vector<User> users, User user, String msg) {
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        for (User u : users) {
            u.tellMsg(time + "  " + user.getName() + ": " + msg);
        }
    }

    public static void chatTellSomeone(User user, String msg) {
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        user.tellMsg(time + ": " + msg);
    }
}
