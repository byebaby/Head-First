package com.cyb.chat.handler.cmd;

import com.cyb.chat.entity.User;

/**
 * @author Cyb
 * 空信息处理类
 */
public class EmptyCmdHandler extends BaseCmdHandler {

    public EmptyCmdHandler(BaseCmdHandler baseCmdHandler) {
        super(baseCmdHandler);
    }

    @Override
    public void handlerResult(User user, String msg) {
        if (!msg.replaceAll(" ", "").isEmpty()) {
            super.baseCmdHandler.handlerResult(user, msg);
        }
    }
}
