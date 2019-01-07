package com.cyb.chat.handler.server;

import com.cyb.chat.entity.User;
import com.cyb.chat.model.Result;

/**
 * @author Cyb
 */
public abstract class BaseServerHandler {
    BaseServerHandler baseServerHandler;

    BaseServerHandler(BaseServerHandler baseServerHandler) {
        this.baseServerHandler = baseServerHandler;
    }

    /**
     * 接收Server返回的信息包装 显示给cmd
     *
     * @param user   当前用户
     * @param result 信息封装
     */
    public abstract void handlerResult(User user, Result result);


}
