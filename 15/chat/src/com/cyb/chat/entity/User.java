package com.cyb.chat.entity;

import com.alibaba.fastjson.JSON;
import com.cyb.chat.constant.MessageType;
import com.cyb.chat.model.Result;
import com.cyb.chat.util.EasySocket;
import com.cyb.chat.util.Results;

import java.io.IOException;

/**
 * @author Cyb
 */
public class User {
    /**
     * The name must be set manually
     */
    private String name = null;
    private String tempName = null;
    private EasySocket easySocket;

    public User(EasySocket easySocket) {
        this.easySocket = easySocket;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTempName() {
        return tempName;
    }

    public void setTempName(String tempName) {
        this.tempName = tempName;
    }

    public String getHostAddress() {
        return easySocket.getSocket().getInetAddress().getHostAddress();
    }

    public void close() throws IOException {
        easySocket.getSocket().close();
    }

    public void tellMsg(String msg) {
        Result result = Results.success(MessageType.MESSAGE, msg);
        easySocket.getWriter().println(JSON.toJSONString(result));
    }

    public void tellError(int code, MessageType messageType, String msg) {
        Result result = Results.error(code, messageType, msg);
        easySocket.getWriter().println(JSON.toJSONString(result));
    }

    public void tellCmd(String msg) {
        Result result = Results.success(MessageType.CMD, msg);
        easySocket.getWriter().println(JSON.toJSONString(result));
    }

    public void tellNotify(String msg) {
        Result result = Results.success(MessageType.NOTIFY, msg);
        easySocket.getWriter().println(JSON.toJSONString(result));
    }

    public void tellName(String msg) {
        Result result = Results.success(MessageType.NAME, msg);
        easySocket.getWriter().println(JSON.toJSONString(result));
    }

    public void tellHeart() {
        Result result = Results.success(MessageType.HEART, "heart");
        easySocket.getWriter().println(JSON.toJSONString(result));
    }

    public Result readMsg() throws IOException {
        return JSON.parseObject(easySocket.getReader().readLine(), Result.class);
    }

    public Boolean readReady() throws IOException {
        return easySocket.getReader().ready();
    }
}
