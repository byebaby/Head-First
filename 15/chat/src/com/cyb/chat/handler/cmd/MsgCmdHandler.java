package com.cyb.chat.handler.cmd;

import com.cyb.chat.entity.User;

/**
 * @author Cyb
 * 普通信息处理类
 */
public class MsgCmdHandler extends BaseCmdHandler {
    public MsgCmdHandler(BaseCmdHandler baseCmdHandler) {
        super(baseCmdHandler);
    }

    @Override
    public void handlerResult(User user, String msg) {
        user.tellMsg(msg);
}


}
