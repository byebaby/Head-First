package com.cyb.chat.handler.cmd;

import com.cyb.chat.entity.User;

/**
 * @author Cyb
 */
public abstract class BaseCmdHandler {
    BaseCmdHandler baseCmdHandler;

    BaseCmdHandler(BaseCmdHandler baseCmdHandler) {
        this.baseCmdHandler = baseCmdHandler;
    }

    /**
     * 命令行消息分类包装发送给Server
     *
     * @param user 客户端用户实体
     * @param msg  命令行消息
     */
    public abstract void handlerResult(User user, String msg);

}
