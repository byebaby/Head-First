package com.cyb.chat.handler.server;

import com.cyb.chat.constant.CodeType;
import com.cyb.chat.constant.MessageType;
import com.cyb.chat.entity.User;
import com.cyb.chat.model.Result;

/**
 * @author Cyb
 * 用户NAME处理类
 */
public class NameServerHandler extends BaseServerHandler {
    public NameServerHandler(BaseServerHandler baseServerHandler) {
        super(baseServerHandler);
    }

    @Override
    public void handlerResult(User user, Result result) {
        if (result.getMessageType() == MessageType.NAME) {
            if (result.getCode() == CodeType.SUCCESS) {
                user.setName(user.getTempName());
            } else {
                user.setTempName(null);
            }
            System.out.println(result.getMessage());
        } else {
            if (super.baseServerHandler != null) {
                super.baseServerHandler.handlerResult(user, result);
            }
        }
    }


}
