package com.cyb.chat.handler.client;

import com.cyb.chat.constant.MessageType;
import com.cyb.chat.entity.User;
import com.cyb.chat.model.Result;

import java.util.Vector;

/**
 * @author Cyb
 * 命令行处理类
 */
public class CmdClientHandler extends BaseClientHandler {

    public CmdClientHandler(BaseClientHandler baseClientHandler) {
        super(baseClientHandler);
    }

    @Override
    public void handlerResult(Vector<User> users, User user, Result result) {
        if (result.getMessageType() == MessageType.CMD) {
            chatTellSomeone(user, "正在开发中");
        } else {
            if (super.baseClientHandler != null) {
                super.baseClientHandler.handlerResult(users, user, result);
            }
        }
    }
}
