package com.cyb.chat.handler.server;

import com.cyb.chat.constant.MessageType;
import com.cyb.chat.entity.User;
import com.cyb.chat.model.Result;

/**
 * @author Cyb
 */
public class NotifyServerHandler extends BaseServerHandler {
    public NotifyServerHandler(BaseServerHandler baseServerHandler) {
        super(baseServerHandler);
    }

    @Override
    public void handlerResult(User user, Result result) {
        if (result.getMessageType() == MessageType.NOTIFY) {
            System.out.println(result.getMessage());
        } else {
            super.baseServerHandler.handlerResult(user, result);
        }
    }
}
