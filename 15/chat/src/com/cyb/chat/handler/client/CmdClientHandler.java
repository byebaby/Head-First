package com.cyb.chat.handler.client;

import com.cyb.chat.constant.CmdType;
import com.cyb.chat.constant.CodeType;
import com.cyb.chat.constant.MessageType;
import com.cyb.chat.entity.User;
import com.cyb.chat.model.Result;
import com.cyb.chat.util.IPUtil;

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
            int flag = result.getMessage().indexOf(" ");
            String type = result.getMessage().substring(0, flag > 0 ? flag : result.getMessage().length());
            String args = result.getMessage().substring(flag > 0 ? flag : 0).trim();
            switch (type) {
                case CmdType.USERS:
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("当前在线总人数：").append(users.size()).append("\n");
                    for (User u : users) {
                        stringBuilder.append(u.getHostAddress()).append("  ").append(u.getName()).append("\n");
                    }
                    user.tellCmd(stringBuilder.toString());
                    break;
                case CmdType.ADD:
                    if (IPUtil.isIP(args)) {
                        for (String s : whiteSet) {
                            if (s.equals(user.getHostAddress())) {
                                whiteSet.add(args);
                                user.tellCmd("添加成功，该IP可以使用所有命令");
                            } else {
                                user.tellError(CodeType.ERROR, MessageType.CMD, "添加失败，请联系管理员");
                            }
                        }
                    } else {
                        user.tellCmd("IP格式不正确");
                    }
                    break;
                case CmdType.NOTIFY:
                    for (String s : whiteSet) {
                        if (s.equals(user.getHostAddress())) {
                            chatTellNotify(users, args);
                            break;
                        }
                    }
                    user.tellError(CodeType.ERROR, MessageType.CMD, "发送失败，您不是管理员");
                    break;
                default:
                    user.tellError(CodeType.ERROR, MessageType.CMD, "命令不存在，请检查");
            }

        } else {
            if (super.baseClientHandler != null) {
                super.baseClientHandler.handlerResult(users, user, result);
            }
        }
    }
}
