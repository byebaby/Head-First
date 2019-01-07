package com.cyb.chat.handler.cmd;


import com.cyb.chat.entity.User;


/**
 * @author Cyb
 * 用户NAME处理类
 */
public class NameCmdHandler extends BaseCmdHandler {
    public NameCmdHandler(BaseCmdHandler baseCmdHandler) {
        super(baseCmdHandler);
    }

    @Override
    public void handlerResult(User user, String msg) {
        if (user.getName() == null) {
            if (user.getTempName() == null) {
                //设置临时名称 等待服务器确认
                user.setTempName(msg);
                user.tellName(msg);
            }
        } else {
            super.baseCmdHandler.handlerResult(user, msg);
        }
    }


}
