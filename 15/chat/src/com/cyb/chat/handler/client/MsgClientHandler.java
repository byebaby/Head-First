package com.cyb.chat.handler.client;

import com.cyb.chat.constant.MessageType;
import com.cyb.chat.entity.User;
import com.cyb.chat.model.Result;

import java.util.Vector;

/**
 * @author Cyb
 * 普通信息处理类
 */
public class MsgClientHandler extends BaseClientHandler {
    public MsgClientHandler(BaseClientHandler baseClientHandler) {
        super(baseClientHandler);
    }

    @Override
    public void handlerResult(Vector<User> users, User user, Result result) {
        if (result.getMessageType() == MessageType.MESSAGE) {
            chatTellEveryone(users, user, result.getMessage());
        } else {
            if (super.baseClientHandler != null) {
                super.baseClientHandler.handlerResult(users, user, result);
            }
        }
    }
}
