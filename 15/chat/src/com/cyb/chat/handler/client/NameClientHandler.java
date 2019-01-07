package com.cyb.chat.handler.client;

import com.cyb.chat.constant.CodeType;
import com.cyb.chat.constant.MessageType;
import com.cyb.chat.entity.User;
import com.cyb.chat.model.Result;

import java.util.Vector;

/**
 * @author Cyb
 * 用户NAME处理类
 */
public class NameClientHandler extends BaseClientHandler {
    public NameClientHandler(BaseClientHandler baseClientHandler) {
        super(baseClientHandler);
    }

    @Override
    public void handlerResult(Vector<User> users, User user, Result result) {
        if (result.getMessageType() == MessageType.NAME) {
            for (User u : users) {
                if (u.getName() != null && u.getName().equals(result.getMessage())) {
                    user.tellError(CodeType.ERROR, MessageType.NAME, "名字已存在,请重新输入:");
                    return;
                }
            }
            user.setName(result.getMessage());
            user.tellName("您的昵称是: " + result.getMessage());
            chatTellNotify(users, "欢迎 \"" + user.getName() + "\" 来到Cyb聊天室" + ", 当前在线人数:" + users.size());
        } else {
            if (super.baseClientHandler != null) {
                super.baseClientHandler.handlerResult(users, user, result);
            }

        }
    }


}
