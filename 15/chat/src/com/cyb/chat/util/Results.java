package com.cyb.chat.util;

import com.cyb.chat.constant.CodeType;
import com.cyb.chat.constant.MessageType;
import com.cyb.chat.model.Result;

/**
 * @author Cyb
 */
public class Results {
    public static Result success(MessageType type, String msg) {
        return new Result(CodeType.SUCCESS, type, msg);
    }

    public static Result error(int code, MessageType type, String msg) {
        return new Result(code, type, msg);
    }
}
