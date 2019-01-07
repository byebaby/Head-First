package com.cyb.chat.handler.server;

import com.cyb.chat.constant.MessageType;
import com.cyb.chat.entity.User;
import com.cyb.chat.model.Result;

/**
 * @author Cyb
 * 心跳包处理类
 */
public class HeartServerHandler extends BaseServerHandler {

    public HeartServerHandler(BaseServerHandler baseServerHandler) {
        super(baseServerHandler);
    }

    @Override
    public void handlerResult(User user, Result result) {
        if (result.getMessageType() != MessageType.HEART) {
            if (super.baseServerHandler != null) {
                super.baseServerHandler.handlerResult(user, result);
            }
        }
    }


}
