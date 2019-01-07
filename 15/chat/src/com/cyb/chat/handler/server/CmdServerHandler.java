package com.cyb.chat.handler.server;

import com.cyb.chat.constant.MessageType;
import com.cyb.chat.entity.User;
import com.cyb.chat.model.Result;

/**
 * @author Cyb
 * 命令行处理类
 */
public class CmdServerHandler extends BaseServerHandler {
    public CmdServerHandler(BaseServerHandler baseServerHandler) {
        super(baseServerHandler);
    }

    @Override
    public void handlerResult(User user, Result result) {
        if (result.getMessageType() == MessageType.CMD) {
            System.out.println(result.getMessage());
        } else {
            if (super.baseServerHandler != null) {
                super.baseServerHandler.handlerResult(user, result);
            }
        }
    }

}
