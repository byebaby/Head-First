package com.cyb.chat.handler.client;

import com.cyb.chat.entity.User;
import com.cyb.chat.model.Result;

import java.util.Vector;

/**
 * @author Cyb
 * 空信息处理类
 */
public class EmptyClientHandler extends BaseClientHandler {

    public EmptyClientHandler(BaseClientHandler baseClientHandler) {
        super(baseClientHandler);
    }

    @Override
    public void handlerResult(Vector<User> users, User user, Result result) {
        if (!result.getMessage().replaceAll(" ", "").isEmpty()) {
            super.baseClientHandler.handlerResult(users, user, result);
        }
    }
}
